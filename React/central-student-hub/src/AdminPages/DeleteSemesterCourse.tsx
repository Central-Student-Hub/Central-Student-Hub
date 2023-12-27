import { FormControl, FormLabel, Button, Select, useToast } from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import { AdminApi } from "../Services/AdminApi.ts";
import { SemesterCourseResponse } from "../Models/SemesterCourseResponse.ts";
import './Admin.css'

export function DeleteSemesterCourse() {

    const [allSemesterCourses, setAllSemesterCourses] = useState<SemesterCourseResponse[]>([]);
    const [selected, setSelected] = useState(-1);
    const toast = useToast();

    const api = new AdminApi();

    useEffect(() => {
        const getSemesterCourses = async () => await api.getAllSemesterCourses();

        getSemesterCourses()
            .then((semesterCourse) => setAllSemesterCourses(semesterCourse))
            .catch((err) => console.log(err));
    }, []);

    useEffect(() => {
        if (allSemesterCourses.length > 0)
            setSelected(allSemesterCourses[0].semCourseId);
    }, [allSemesterCourses]);

    async function handleSubmit() {
        if (selected == -1) {
            toast({
                title: "Invalid Input!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
            return;
        }

        const response = await api.deleteSemesterCourse(selected);
        if (response) {
            toast({
                title: "Semester Course Deleted Successfully!",
                status: "success",
                duration: 3000,
                isClosable: true,
            });
            return;
        }

        toast({
            title: "Semester Course Deletion Failed!",
            status: "error",
            duration: 3000,
            isClosable: true,
        });
    }

    async function handleDeleteAll() {
        const response = await api.deleteAllSemesterCourses();
        if (response) {
            toast({
                title: "All Semester Courses Deleted Successfully!",
                status: "success",
                duration: 3000,
                isClosable: true,
            });
        } else {
            toast({
                title: "Deletion Failed!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
        }
    }

    return (
        <div id="admin-container">
            <h1 style={{fontSize: 42}}>Delete Semester Course</h1>
            <FormControl style={{ width: 400 }} >
                <FormLabel>Semester Course</FormLabel>
                <Select onChange={(e) => setSelected(parseInt(e.target.value))}>
                    {
                        allSemesterCourses.length > 0 &&
                        allSemesterCourses.map((semesterCourse) => <option value={semesterCourse.semCourseId}>{semesterCourse.code} - {semesterCourse.name} - {semesterCourse.semester}</option>)
                    }
                </Select>

                <br />

                <Button onClick={handleSubmit}>Delete Selected Semester Course</Button>

                <br />
                <br />

                <Button onClick={handleDeleteAll}>Delete All Semester Courses</Button>
            </FormControl>
        </div>
    )
}
