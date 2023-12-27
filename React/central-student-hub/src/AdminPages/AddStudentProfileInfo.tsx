import { FormControl, FormLabel, Input, Button, Textarea, Select, useToast } from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import './Admin.css'
import { StudentProfileInfo } from "../Models/StudentProfileInfo";
import { AdminApi } from "../Services/AdminApi.ts";

export default function AddStudentProfileInfo() {

    const [allStudents, setAllStudents] = useState<StudentProfileInfo[]>([]);
    const toast = useToast();

    const [studentId, setStudentId] = useState(-1);
    const [major, setMajor] = useState("");
    const [minor, setMinor] = useState("");

    const api = new AdminApi();

    useEffect(() => {
        const getStudents = async () => await api.getAllStudents();

        getStudents()
            .then((students) => setAllStudents(students))
            .catch((err) => console.log(err));
    }, []);

    useEffect(() => {
        if (allStudents.length > 0)
            setStudentId(allStudents[0].id);
    }, [allStudents]);

    async function handleSubmit() {
        if (studentId == -1 || major == "" || minor == "") {
            toast({
                title: "Invalid Input!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
            return;
        }

        const student = allStudents.filter((s) => s.id == studentId)[0];
        const response = await api.updateStudentProfile({ ...student, major: major, minor: minor });

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
            <h1 style={{fontSize: 42}}>Set Student Major and Minor</h1>
            <FormControl style={{ width: 400 }} >
                <FormLabel>Student</FormLabel>
                <Select onChange={(e) => setStudentId(parseInt(e.target.value))}>
                    {
                        allStudents.map((student) => <option value={student.id}>{student.firstName} {student.lastName}</option>)
                    }
                </Select>

                <br />

                <FormLabel>Major</FormLabel>
                <Input onChange={(e) => setMajor(e.target.value)}></Input>

                <br />
                <br />

                <FormLabel>Minor</FormLabel>
                <Input onChange={(e) => setMinor(e.target.value)}></Input>

                <br />
                <br />

                <Button onClick={handleSubmit}>Update Student Profile</Button>
            </FormControl>
        </div>
    )
}
