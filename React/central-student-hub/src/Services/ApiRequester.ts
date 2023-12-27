import { SignupResponse } from '../Models/SignupModels.ts';
import { LoginRequest, LoginResponse } from '../Models/LoginModels.ts'
import { FormState } from '../UserSessionComponent/Signup.tsx';
import { Assignment } from '../Models/Assignment.ts';
import { AssignmentAnswerRequest } from '../Models/AssignmentAnswerRequest.ts';

export class ApiRequester {
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
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch("http://localhost:8082/Hello", requestOptions);
        return await response.text();
    }

    async getRole(): Promise<string> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch("http://localhost:8082/role", requestOptions);
        return await response.text();
    }
}
