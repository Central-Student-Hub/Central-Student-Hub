import React, { useEffect, useState } from "react";
import { ApiRequester } from "./Services/ApiRequester";
import { useNavigate } from "react-router";

export default function Home() {
    const [email, setEmail] = useState('');
    const apiRequester = new ApiRequester();
    const navigate = useNavigate();

    useEffect(() => {
        if (document.cookie == "" || document.cookie.split("=")[0] != "token") {
            navigate("/login");
            return;
        }

        const fetchEmail = async () => await apiRequester.home();
        fetchEmail()
            .then((email) => setEmail(email))
            .catch((error) => console.error(error))
    }, []);

    return (
        email == "" ? <></> :
        <h1>
            Hello {email}
        </h1>
    );
}