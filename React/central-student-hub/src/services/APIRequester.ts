import { LoginResponse, LoginRequest } from '../UserSessionComponent/Models/LoginModels.ts'
import { SignupResponse, SignupRequest } from '../UserSessionComponent/Models/SignupModels.ts'

export class APIRequester {
    async login(request: LoginRequest): Promise<boolean> {
        try {
            const headers: HeadersInit = { "Content-Type": "application/json" };
            const requestOptions: RequestInit = { mode: 'cors', body: JSON.stringify(request), method: "post", headers: headers };
            const response: Response = await fetch("http://localhost:8082/auth/login", requestOptions);
            const loginResponse: LoginResponse = await response.json();
            if (!loginResponse.accept)
                return false;
            document.cookie = `token=${loginResponse.token}}`
            return true;
        } catch (error) {
            console.error(error);
            return false;
        }
    }

    async signup(request: SignupRequest): Promise<SignupResponse> {
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
        try {
            const token = document.cookie.split("=")[1];
            const headers: HeadersInit = { "Authorization": `Bearer +${token}` };
            const requestOptions: RequestInit = { mode: 'cors', method: "get", headers: headers };
            const response: Response = await fetch("http://localhost:8082", requestOptions);
            return await response.text();
        } catch (error) {
            console.error(error);
            return "";
        }
    }
}