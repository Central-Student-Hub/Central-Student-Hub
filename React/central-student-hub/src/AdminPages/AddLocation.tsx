import React, { useState } from "react";

import { FormControl, FormErrorMessage, FormHelperText, FormLabel, Input, Button, useToast } from '@chakra-ui/react'

import './Admin.css'
import { AdminApi } from "../Services/AdminApi.ts";
import { Location } from "../Models/Location.ts";

export default function AddUsers() {
    const [location, setLocation] = useState<Location>({
        building: 0,
        room: 0,
        capacity: 0
    });
    const toast = useToast();
    const api = new AdminApi();

    async function handleSubmit() {
        if (location.building == 0 || location.room == 0 || location.capacity == 0) {
            toast({
                title: "Invalid Input!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
            return;
        }

        const response = await api.addLocation(location);
        if (response) {
            toast({
                title: "Location Added Successfully!",
                status: "success",
                duration: 3000,
                isClosable: true,
            });
        } else {
            toast({
                title: "Location Addition Failed!",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
        }
    }
  
    return (
        <div id="admin-container">
            <h1 style={{fontSize: 42}}>Add Location</h1>
            <FormControl style={{ width: 400 }}>
                <FormLabel>Building No.</FormLabel>
                <Input type='number' value={location.building} onChange={(e) => setLocation({...location, building: parseInt(e.target.value)})} />

                <br />
                <br />
                

                <FormLabel>Room No.</FormLabel>
                <Input type='number' value={location.room} onChange={(e) => setLocation({...location, room: parseInt(e.target.value)})} />

                <br />
                <br />

                <FormLabel>Capacity</FormLabel>
                <Input type='number' value={location.capacity} onChange={(e) => setLocation({...location, capacity: parseInt(e.target.value)})} />


                <br />
                <br />

                <Button onClick={handleSubmit}>Add Location</Button>
            </FormControl>
        </div>
    )
}