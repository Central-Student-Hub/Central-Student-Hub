import { FormControl, FormLabel, Input, Button, Textarea, Select, useToast } from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import './Admin.css'
import { StudentProfileInfo } from "../Models/StudentProfileInfo";
import { AdminApi } from "../Services/AdminApi.ts";
import { WarningRequest } from "../Models/WarningRequest.ts";

export function AddWarning() {

    const [allStudents, setAllStudents] = useState<StudentProfileInfo[]>([]);
    const [dateString, setDateString] = useState('');
    const toast = useToast();

    const [warning, setWarning] = useState<WarningRequest>({
        studentId: -1,
        reason: "",
        date: new Date()
    });

    const api = new AdminApi();

    useEffect(() => {
        const getStudents = async () => await api.getAllStudents();

        getStudents()
            .then((students) => setAllStudents(students))
            .catch((err) => console.log(err));
    }, [])

    useEffect(() => {
        if (allStudents.length > 0)
            setWarning({...warning, studentId: allStudents[0].id});
    }, [allStudents])

    async function handleSubmit() {
        if (warning.studentId == -1 || warning.reason == "" || dateString == "") {
            toast({
                title: "Invalid Input!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
            return;
        }

        const d = new Date();
        dateString.split('-').forEach((item, index) => {
            if (index == 0) {
                d.setFullYear(parseInt(item));
            } else if (index == 1) {
                d.setMonth(parseInt(item) - 1);
            } else if (index == 2) {
                d.setDate(parseInt(item));
            }
        });

        if (d < new Date()) {
            toast({
                title: "Invalid Date!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
            return;
        }

        setWarning((old) => { return {...old, date: d}; });
        const response = await api.addWarning(warning);
        if (response) {
            toast({
                title: "Warning Issued Successfully!",
                status: "success",
                duration: 3000,
                isClosable: true,
            });
        } else {
            toast({
                title: "Warning Issuing Failed!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
        }
    }

    return (
        <div id="admin-container">
            <h1 style={{fontSize: 42}}>Issue Warning</h1>
            <FormControl style={{ width: 400 }} >
                <FormLabel>Student</FormLabel>
                <Select onChange={(e) => setWarning({...warning, studentId: parseInt(e.target.value)})}>
                    {
                        allStudents.map((student) => <option value={student.id}>{student.firstName} {student.lastName}</option>)
                    }
                </Select>

                <br />

                <FormLabel>Reason</FormLabel>
                <Textarea onChange={(e) => setWarning({...warning, reason: e.target.value})}></Textarea>

                <br />
                <br />

                <Input type="date" onChange={(e) => {setDateString(e.target.value)}}></Input>

                <br />
                <br />

                <Button onClick={handleSubmit}>Issue Warning</Button>
            </FormControl>
        </div>
    )
}
export { WarningRequest };

