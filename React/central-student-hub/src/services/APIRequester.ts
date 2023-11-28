import { LoginResponse, LoginRequest } from '../UserSessionComponent/Models/LoginModels.ts'
import { SignupResponse, SignupRequest } from '../UserSessionComponent/Models/SignupModels.ts'

export class APIRequester {
    async login(request: LoginRequest): Promise<boolean> {
        try {
            const headers: HeadersInit = { "Content-Type": "application/json" };
            const response: Response = await fetch("http://localhost:8080/login", { mode: 'cors', body: JSON.stringify(request), method: "post", headers: headers });
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
}


// async function signup(request: LoginRequest): Promise<boolean> {
//     try {
//         const response: Response = await fetch("http://localhost:8080/login", { mode: 'cors', body: JSON.stringify(request) });
//         return await response.json();
//     } catch (error) {
//         console.error(error);
//         return false;
//     }
// }