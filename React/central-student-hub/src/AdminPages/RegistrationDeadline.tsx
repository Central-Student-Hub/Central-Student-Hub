import React, { useState } from "react";

import { FormControl, FormLabel, Input, Button, useToast } from '@chakra-ui/react'

import './Admin.css'
import { AdminApi } from "../Services/AdminApi.ts";

export default function RegistrationDeadline() {
    const [date, setDate] = useState('');
    const toast = useToast();
    const api = new AdminApi();

    async function handleSubmit() {
        const d = new Date();
        date.split('-').forEach((item, index) => {
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
        
        const response = await api.setDeadline(d);
        if (response) {
            toast({
                title: "Date Set Successfully!",
                status: "success",
                duration: 3000,
                isClosable: true,
            });
            return;
        }

        toast({
            title: "Date Set Failed!",
            status: "error",
            duration: 3000,
            isClosable: true,
        });
    }
  
    return (
        <div id="admin-container">
            <h1 style={{fontSize: 42}}>Set Registration Fees Deadline</h1>
            <FormControl style={{ width: 400 }}>
                <FormLabel>Deadline</FormLabel>
                <Input type='date' value={date} onChange={(e) => setDate(e.target.value)} />
                <br />
                <br />
                <Button onClick={handleSubmit}>Set Deadline</Button>
            </FormControl>
        </div>
    )
}
