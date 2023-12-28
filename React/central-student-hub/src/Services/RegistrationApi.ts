import { AddCourseToCartRequest } from '../Models/AddCourseToCartRequest.ts';
import { Course } from '../Models/Course.ts';

export class RegistrationApi {
    async retrieveSemesterCourses(searchPhrase: string): Promise<Course[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Register/getAvailableCourses?searchPhrase=${searchPhrase}`, requestOptions);
        return await response.json();
    }

    async verifyCourse(request: AddCourseToCartRequest): Promise<boolean> {
        console.log(request);
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(request), method: "post", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Register/addCourseToCart`, requestOptions);
        console.log(response);
        return await response.json();
    }

    async checkout(semCourseIds: number[]): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(semCourseIds), method: "post", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Register/checkOut`, requestOptions);
        return await response.json();
    }

    async retrieveAvailableCreditHours(): Promise<number> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Register/creditHours`, requestOptions);
        return await response.json();
    }

    async getFees(): Promise<number> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Register/showFees`, requestOptions);
        return await response.json();
    }

    async getDeadline(): Promise<Date> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Register/getPaymentDeadLine`, requestOptions);
        return await response.json();
    }
}
