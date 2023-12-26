import { SignupResponse } from '../Models/SignupModels.ts';
import { LoginRequest, LoginResponse } from '../Models/LoginModels.ts'
import { FormState } from '../UserSessionComponent/Signup.tsx';
import { TeachingStaffProfileInfo } from '../Models/TeachingStaffProfileInfo.ts';
import { Course } from '../StudentInterface/Registration.tsx';
import { AddCourseToCartRequest } from '../Models/AddCourseToCartRequest.ts';
import { CourseType } from '../StudentInterface/Course.tsx';
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

    async getCourses(searchKey: string): Promise<Course[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/getAvailableCourses?searchKey=${searchKey}`, requestOptions); // TODO: Change this to the correct endpoint
        return await response.json();
    }

    async verifyCourse(request: AddCourseToCartRequest): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(request), method: "post", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/sadsadsadsadsa`, requestOptions); // TODO: Change this to the correct endpoint
        return await response.json();
    }

    async retrieveSemesterCourses(): Promise<CourseType[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/getAllCourses`, requestOptions); // TODO: Change this to the correct endpoint
        return await response.json();
    }

    async getAllAssignments(): Promise<Assignment[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/sadsadsadsadsa`, requestOptions); // TODO: Change this to the correct endpoint
        return await response.json();
    }

    async getActiveAssignments(): Promise<Assignment[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/sadsadsadsadsa`, requestOptions); // TODO: Change this to the correct endpoint
        return await response.json();
    }

    async submitAssignmentAnswer(assignmentAnswerRequest: AssignmentAnswerRequest): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(assignmentAnswerRequest), method: "post", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/sadsadsadsadsa`, requestOptions); // TODO: Change this to the correct endpoint
        return await response.json();
    }
}
