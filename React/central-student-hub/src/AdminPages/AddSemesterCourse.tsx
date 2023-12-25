import React, { useEffect, useState } from "react";
import { FormControl, FormLabel, Input, Button, Select, Toast, useToast } from '@chakra-ui/react'
import { AdminCourseResponse } from "../Models/AdminCourseResponse";
import { AdminApi } from "../Services/AdminApi.ts";
import { AdminAddSemesterCourseRequest, Semester } from "../Models/AdminAddSemesterCourseRequest.ts";
import './Admin.css'
import { TeachingStaffProfileInfo } from "../Models/TeachingStaffProfileInfo.ts";
import { AdminAddSessionRequest } from "../Models/AdminAddSessionRequest.ts";

export default function AddSemesterCourse() {
    const [semesterCourse, setSemesterCourse] = useState<AdminAddSemesterCourseRequest>({
        courseId: -1,
        semester: "FALL",
        maxSeats: 0
    });

    const [allCourses, setAllCourses] = useState<AdminCourseResponse[]>([]);
    const [allTeachingStaff, setAllTeachingStaff] = useState<TeachingStaffProfileInfo[]>([]);

    const [lectureSession, setLectureSession] = useState<AdminAddSessionRequest>({
        semCourseId: -1,
        teacherId: -1,
        period: -1,
        weekDay: "Saturday",
        sessionType: "LECTURE"
    });

    const [tutorialSession, setTutorialSession] = useState<AdminAddSessionRequest>({
        semCourseId: -1,
        teacherId: -1,
        period: 1,
        weekDay: "Saturday",
        sessionType: "TUTORIAL"
    });

    const [labSession, setLabSession] = useState<AdminAddSessionRequest>({
        semCourseId: -1,
        teacherId: -1,
        period: 1,
        weekDay: "Saturday",
        sessionType: "LAB"
    });
    
    const invalidSessions = lectureSession.period == tutorialSession.period && lectureSession.weekDay == tutorialSession.weekDay
        || lectureSession.period == labSession.period && lectureSession.weekDay == labSession.weekDay
        || tutorialSession.period == labSession.period && tutorialSession.weekDay == labSession.weekDay;

    const api = new AdminApi();
    const toast = useToast();

    useEffect(() => {
        const getAllCourses = async () => await api.getAllCourses();
        const getAllTeachingStaff = async () => await api.getAllTeachingStaff();

        getAllCourses()
            .then((courses) => setAllCourses(courses.sort((a, b) => a.courseId - b.courseId)))
            .catch((error) => console.error(error));

        getAllTeachingStaff()
            .then((teachingStaff) => setAllTeachingStaff(teachingStaff.sort((a, b) => a.firstName.localeCompare(b.firstName))))
            .catch((error) => console.error(error));
    }, [])

    useEffect(() => {
        if (allCourses.length > 0) {
            setSemesterCourse({...semesterCourse, courseId: allCourses[0].courseId});
        }
    }, [allCourses]);

    useEffect(() => {
        if (allTeachingStaff.length > 0) {
            setLectureSession({...lectureSession, teacherId: allTeachingStaff[0].id});
            setTutorialSession({...tutorialSession, teacherId: allTeachingStaff[0].id});
            setLabSession({...labSession, teacherId: allTeachingStaff[0].id});
        }
    }, [allTeachingStaff]);

    async function handleSubmit() {
        console.log(semesterCourse);
        if (invalidSessions) {
            toast({
                title: "Conflicting Sessions!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
            return;
        }
        
        const id = await api.addSemesterCourse(semesterCourse);
        if (id !== -1) {
            setLectureSession((session) => { return {...session, semCourseId: id }});
            setTutorialSession((session) => { return {...session, semCourseId: id }});
            setLabSession((session) => { return {...session, semCourseId: id }});

            const addedLecture = await api.addSession(lectureSession);
            const addedTutorial = await api.addSession(tutorialSession);
            const addedLab = await api.addSession(labSession);

            if (addedLecture && addedTutorial && addedLab) {
                toast({
                    title: "Added Semester Course Successfully!",
                    status: "success",
                    duration: 3000,
                    isClosable: true,
                });
            } else {
                toast({
                    title: "Failed to Add Semester Course!",
                    status: "error",
                    duration: 3000,
                    isClosable: true,
                });
            }
        } else {
            toast({
                title: "Failed to Add Semester Course!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
        }
    }

    return (
        <div id="admin-container">
            <h1 style={{fontSize: 42}}>Add Semester Course</h1>
            <div id="admin-container-horizontal">
                <FormControl style={{ width: 400 }}>

                    <FormLabel>Course</FormLabel>
                    <Select onChange={(e) => setSemesterCourse({...semesterCourse, courseId: parseInt(e.target.value)})}>
                        {
                            allCourses.map((course) => (
                                <option value={course.courseId}>{course.code} - {course.name}</option>
                            ))
                        }
                    </Select>

                    <br />

                    <FormLabel>Semester</FormLabel>
                    <Select onChange={(e) => setSemesterCourse({...semesterCourse, semester: e.target.value as Semester})}>
                        <option value='FALL'>Fall</option>
                        <option value='SPRING'>Spring</option>
                        <option value='SUMMER'>Summer</option>
                    </Select>

                    <br />

                    <FormLabel>Max seats</FormLabel>
                    <Input type='number' value={semesterCourse.maxSeats} onChange={(e) => setSemesterCourse({...semesterCourse, maxSeats: parseInt(e.target.value)})} />

                    <br />
                    <br />
                </FormControl>
                <FormControl style={{ width: 400 }} isInvalid={invalidSessions}>

                    <FormLabel>Lecture Day</FormLabel>
                    <Select onChange={(e) => setLectureSession({...lectureSession, weekDay: e.target.value})}>
                        <option value='Saturday'>Saturday</option>
                        <option value='Sunday'>Sunday</option>
                        <option value='Monday'>Monday</option>
                        <option value='Tuesday'>Tuesday</option>
                        <option value='Wednesday'>Wednesday</option>
                        <option value='Thursday'>Thursday</option>
                    </Select>

                    <br />

                    <FormLabel>Lecture Period</FormLabel>
                    <Select onChange={(e) => setLectureSession({...lectureSession, period: parseInt(e.target.value)})}>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                        <option value='6'>6</option>
                    </Select>

                    <br />

                    <FormLabel>Lecture Teacher</FormLabel>
                    <Select onChange={(e) => setLectureSession({...lectureSession, teacherId: parseInt(e.target.value)})}>
                        {
                            allTeachingStaff.map((teacher) => (
                                <option value={teacher.id}>{teacher.firstName} {teacher.lastName}</option>
                            ))
                        }
                    </Select>

                </FormControl>
                <FormControl style={{ width: 400 }} isInvalid={invalidSessions}>

                    <FormLabel>Tutorial Day</FormLabel>
                    <Select onChange={(e) => setTutorialSession({...tutorialSession, weekDay: e.target.value})}>
                        <option value='Saturday'>Saturday</option>
                        <option value='Sunday'>Sunday</option>
                        <option value='Monday'>Monday</option>
                        <option value='Tuesday'>Tuesday</option>
                        <option value='Wednesday'>Wednesday</option>
                        <option value='Thursday'>Thursday</option>
                    </Select>

                    <br />

                    <FormLabel>Tutorial Period</FormLabel>
                    <Select onChange={(e) => setTutorialSession({...tutorialSession, period: parseInt(e.target.value)})}>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                        <option value='6'>6</option>
                    </Select>

                    <br />

                    <FormLabel>Tutorial Teacher</FormLabel>
                    <Select onChange={(e) => setTutorialSession({...tutorialSession, teacherId: parseInt(e.target.value)})}>
                        {
                            allTeachingStaff.map((teacher) => (
                                <option value={teacher.id}>{teacher.firstName} {teacher.lastName}</option>
                            ))
                        }
                    </Select>

                </FormControl>
                <FormControl style={{ width: 400 }} isInvalid={invalidSessions}>

                    <FormLabel>Lab Day</FormLabel>
                    <Select onChange={(e) => setLabSession({...labSession, weekDay: e.target.value})}>
                        <option value='Saturday'>Saturday</option>
                        <option value='Sunday'>Sunday</option>
                        <option value='Monday'>Monday</option>
                        <option value='Tuesday'>Tuesday</option>
                        <option value='Wednesday'>Wednesday</option>
                        <option value='Thursday'>Thursday</option>
                    </Select>

                    <br />
                    
                    <FormLabel>Lab Period</FormLabel>
                    <Select onChange={(e) => setLabSession({...labSession, period: parseInt(e.target.value)})}>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                        <option value='6'>6</option>
                    </Select>

                    <br />

                    <FormLabel>Lab Teacher</FormLabel>
                    <Select onChange={(e) => setLabSession({...labSession, teacherId: parseInt(e.target.value)})}>
                        {
                            allTeachingStaff.map((teacher) => (
                                <option value={teacher.id}>{teacher.firstName} {teacher.lastName}</option>
                            ))
                        }
                    </Select>

                </FormControl>
            </div>
            <Button onClick={handleSubmit}>Add Semester Course</Button>
        </div>
    )
}