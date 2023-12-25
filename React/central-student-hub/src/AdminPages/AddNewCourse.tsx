import React, { useEffect, useState } from "react";
import { FormControl, FormLabel, Input, Button, Textarea, Select, Checkbox, Toast, useToast } from '@chakra-ui/react'
import { AdminAddCourseRequest } from "../Models/AdminAddCourseRequest";
import { AdminCourseResponse } from "../Models/AdminCourseResponse";
import './Admin.css'
import { AdminApi } from "../Services/AdminApi.ts";

export default function AddNewCourse() {
    const [course, setCourse] = useState<AdminAddCourseRequest>({
        code: "",
        name: "",
        description: "",
        creditHours: 1
    });

    const [otherCourses, setOtherCourses] = useState<AdminCourseResponse[]>([]);
    const [prerequisites, setPrerequisites] = useState<number[]>([]);
    const api = new AdminApi();
    const toast = useToast();

    useEffect(() => {
        const getAllCourses = async () => await api.getAllCourses();

        getAllCourses()
            .then((courses) => setOtherCourses(courses.sort((a, b) => a.courseId - b.courseId)))
            .catch((error) => console.error(error));
        
    }, [])

    function handleCheck(e: React.ChangeEvent<HTMLInputElement>, courseId: number) {
        if (e.target.checked) {
            setPrerequisites([...prerequisites, courseId]);
        } else {
            setPrerequisites(prerequisites.filter((id) => id !== courseId));
        }
    }
  
    async function handleSubmit() {
        console.log(course);
        console.log(prerequisites);
        const courseId = await api.addCourse(course);

        if (courseId === -1) {
            toast({
                title: "Error",
                description: "Failed to add course",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
            return;
        }

        let success = true;
        prerequisites.forEach(async (prerequisiteId) => {
            success &&= await api.addCoursePrerequisite(courseId, prerequisiteId);
            if (!success) {
                toast({
                    title: "Error",
                    description: "Failed to add prerequisite - " + prerequisiteId + " to course",
                    status: "error",
                    duration: 1000,
                    isClosable: true,
                });
            }
        });

        if (!success) return;

        toast({
            title: "Added Course Successfully",
            status: "success",
            duration: 3000,
            isClosable: true,
        });
    }
  
    return (
        <div id="admin-container">
            <h1 style={{fontSize: 42}}>Add New Course To System</h1>
            <FormControl style={{ width: 400 }}>
                <FormLabel>Code</FormLabel>
                <Input type='text' value={course.code} onChange={(e) => setCourse({...course, code: e.target.value})} />

                <br />
                <br />

                <FormLabel>Name</FormLabel>
                <Input type='text' value={course.name} onChange={(e) => setCourse({...course, name: e.target.value})} />

                <br />
                <br />

                <FormLabel>Description</FormLabel>
                <Textarea value={course.description} onChange={(e) => setCourse({...course, description: e.target.value})}></Textarea>

                <br />
                <br />

                <FormLabel>Credit Hours</FormLabel>
                <Select onChange={(e) => setCourse({...course, creditHours: parseInt(e.target.value)})}>
                    <option value='1'>1</option>
                    <option value='2'>2</option>
                    <option value='3'>3</option>
                </Select>

                <br />
                <br />

                {
                    otherCourses.length === 0 ? <></> : <FormLabel>Prerequisites</FormLabel>
                }
                {
                    otherCourses.map((course) => (
                        <>
                            <Checkbox onChange={(e) => handleCheck(e, course.courseId)}>{course.code} - {course.name}</Checkbox>
                            <br />
                        </>
                    ))
                }
                
                <br />
                <br />

                <Button onClick={handleSubmit}>Add Course</Button>
            </FormControl>
        </div>
    )
}