import { SignupRequest , SignupResponse } from './../UserSessionComponent/Models/SignupModels';
import { LoginRequest, LoginResponse } from '../UserSessionComponent/Models/LoginModels.ts'
import { FormState } from '../UserSessionComponent/Signup/Signup.tsx';

export class APIRequester {
    async login(request: LoginRequest): Promise<boolean> {
        try {
            const headers: HeadersInit = { "Content-Type": "application/json" };
            const requestOptions: RequestInit = { mode: 'cors', body: JSON.stringify(request), method: "post", headers: headers };
            const response: Response = await fetch("http://localhost:8082/auth/login", requestOptions);
            const loginResponse: LoginResponse = await response.json();
            if (!loginResponse.accept)
                return false;
            document.cookie = `token=${loginResponse.token}`
            return true;
        } catch (error) {
            console.error(error);
            return false;
        }
    }

    async signup(request: FormState): Promise<SignupResponse> {
        try {
            const headers: HeadersInit = { "Content-Type": "application/json" };
            const requestOptions: RequestInit = { mode: 'cors', body: JSON.stringify(request), method: "post", headers: headers };
            const response: Response = await fetch("http://localhost:8082/auth/signUp", requestOptions);
            return await response.json();
        } catch (error) {
            console.error(error);
            return { 
                message: "Network Error!",
                accept: false
            };
        }
    }

    async home(): Promise<string> {  
        const token = document.cookie.split("=")[1];
        console.log(token);
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`};
        const requestOptions: RequestInit = { mode: 'cors',headers:headers,method: "get",credentials:"include"};
        console.log(requestOptions);
        const response: Response = await fetch("http://localhost:8082/Hello", requestOptions);
        return await response.text();
    }
}