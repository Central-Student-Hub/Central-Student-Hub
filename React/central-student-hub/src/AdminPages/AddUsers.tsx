import React, { useState } from "react";

import { FormControl, FormErrorMessage, FormHelperText, FormLabel, Input, Button, useToast } from '@chakra-ui/react'

import './Admin.css'
import { AdminApi } from "../Services/AdminApi.ts";

export default function AddUsers() {
    const [ssn, setSsn] = useState('');
  
    const handleInputChange = (e) => setSsn(e.target.value);
    const toast = useToast();
    const invalidSSN = ssn.length != 14;
    const api = new AdminApi();

    async function handleSubmit() {
        if (invalidSSN) {
            toast({
                title: "Invalid SSN!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
            return;
        }

        const response = await api.addUser(ssn);
        if (response) {
            toast({
                title: "User Added Successfully!",
                status: "success",
                duration: 3000,
                isClosable: true,
            });
        } else {
            toast({
                title: "SSN Already Exists!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
        }
    }
  
    return (
        <div id="admin-container">
            <h1 style={{fontSize: 42}}>Add User To System</h1>
            <FormControl style={{ width: 400 }} isInvalid={invalidSSN}>
                <FormLabel>SSN</FormLabel>
                <Input type='text' value={ssn} onChange={handleInputChange} />
                {!invalidSSN ? (
                    <FormHelperText>
                    Valid SSN
                </FormHelperText>
                ) : (
                    <FormErrorMessage>SSN must be of length 14</FormErrorMessage>
                    )}
                <br />
                <Button onClick={handleSubmit}>Add User</Button>
            </FormControl>
        </div>
    )
}
