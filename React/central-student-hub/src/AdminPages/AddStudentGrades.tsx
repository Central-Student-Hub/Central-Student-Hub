import { FormControl, FormLabel, Input, Button, useToast, Select } from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import './Admin.css'
import { StudentProfileInfo } from "../Models/StudentProfileInfo";
import { AdminApi } from "../Services/AdminApi.ts";
import { SemesterCourseResponse } from "../Models/SemesterCourseResponse.ts";

export default function AddStudentGrades() {

    const [selectedSemesterCourse, setSelectedSemesterCourse] = useState(-1);
    const [allStudents, setAllStudents] = useState<StudentProfileInfo[]>([]);
    const [allGrades, setAllGrades] = useState<number[]>([]);
    const [allSemesterCourses, setAllSemesterCourses] = useState<SemesterCourseResponse[]>([]);

    const toast = useToast();
    const api = new AdminApi();

    useEffect(() => {
        const getSemesterCourses = async () => await api.getAllSemesterCourses();

        getSemesterCourses()
            .then((semesterCourses) => setAllSemesterCourses(semesterCourses))
            .catch((err) => console.log(err));
    }, []);

    useEffect(() => {
        if (allSemesterCourses.length > 0)
            setSelectedSemesterCourse(allSemesterCourses[0].semCourseId);
    }, [allSemesterCourses]);

    useEffect(() => {
        if (selectedSemesterCourse != -1) {
            const getStudents = async () => await api.getStudentsBySemesterCourse(selectedSemesterCourse);
            
            getStudents()
                .then((students) => setAllStudents(students))
                .catch((err) => console.log(err));
    }}, [selectedSemesterCourse]);

    useEffect(() => {
        if (allStudents.length > 0)
            setAllGrades(allStudents.map((student) => -1));
    }, [allStudents]);

    async function handleSubmit() {

        const invalidGrades = allGrades.filter((grade) => grade == -1);
        if (invalidGrades.length > 0) {
            toast({
                title: "Invalid Input!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
            return;
        }

        const response = await api.updateStudentGrades({
            semCourseId: selectedSemesterCourse,
            studentIds: allStudents.map((students) => students.id),
            grades: allGrades
        });

        if (response) {
            toast({
                title: "Profile Updated Successfully!",
                status: "success",
                duration: 3000,
                isClosable: true,
            });
        } else {
            toast({
                title: "Profile Updating Failed!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
        }
    }

    return (
        <div id="admin-container">
            <h1 style={{fontSize: 42}}>Set Student Grades</h1>
            <FormControl style={{ width: 400 }}>
                <FormLabel>Semester Course</FormLabel>
                <Select onChange={(e) => setSelectedSemesterCourse(parseInt(e.target.value))}>
                    {
                        allSemesterCourses.length > 0 &&
                        allSemesterCourses.map((semesterCourse) => <option value={semesterCourse.semCourseId}>{ semesterCourse.name } - { semesterCourse.semester }</option>)
                    }
                </Select>

                <br />
                <br />

                {
                    allStudents.length > 0 &&
                    allStudents.map((student) =>
                        <>
                            <FormLabel>{ student.id } - { student.firstName } { student.lastName }</FormLabel>
                            <Input type="number" onChange={(e) =>
                                setAllGrades(allGrades.map((grade, index) => {
                                    if (index == allStudents.indexOf(student)) {
                                        return parseFloat(e.target.value);
                                    }
                                    return grade;
                                }))
                            }>
                            </Input>

                            <br />
                            <br />
                        </>
                    )
                }

                <br />
                <br />

                <Button onClick={handleSubmit}>Add Grades</Button>
            </FormControl>
        </div>
    )
}
