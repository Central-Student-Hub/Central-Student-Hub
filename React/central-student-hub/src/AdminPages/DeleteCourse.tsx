import { FormControl, FormLabel, Button, Select, useToast } from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import { AdminApi } from "../Services/AdminApi.ts";
import { AdminCourseResponse } from "../Models/AdminCourseResponse.ts";
import './Admin.css'

export default function DeleteCourse() {

    const [allCourses, setAllCourses] = useState<AdminCourseResponse[]>([]);
    const [selected, setSelected] = useState(-1);
    const toast = useToast();

    const api = new AdminApi();

    useEffect(() => {
        const getCourses = async () => await api.getAllCourses();

        getCourses()
            .then((courses) => setAllCourses(courses))
            .catch((err) => console.log(err));
    }, []);

    useEffect(() => {
        if (allCourses.length > 0)
            setSelected(allCourses[0].courseId);
    }, [allCourses]);

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

        const response = await api.deleteCourse(selected);
        if (response) {
            toast({
                title: "Course Deleted Successfully!",
                status: "success",
                duration: 3000,
                isClosable: true,
            });
            return;
        }

        toast({
            title: "Course Deletion Failed!",
            status: "error",
            duration: 3000,
            isClosable: true,
        });
    }

    async function handleDeleteAll() {
        const response = await api.deleteAllCourses();
        if (response) {
            toast({
                title: "All Courses Deleted Successfully!",
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
            <h1 style={{fontSize: 42}}>Delete Course</h1>
            <FormControl style={{ width: 400 }} >
                <FormLabel>Course</FormLabel>
                <Select onChange={(e) => setSelected(parseInt(e.target.value))}>
                    {
                        allCourses.length > 0 &&
                        allCourses.map((course) => <option value={course.courseId}>{course.code} - {course.name}</option>)
                    }
                </Select>

                <br />

                <Button onClick={handleSubmit}>Delete Selected Course</Button>

                <br />
                <br />

                <Button onClick={handleDeleteAll}>Delete All Courses</Button>
            </FormControl>
        </div>
    )
}
