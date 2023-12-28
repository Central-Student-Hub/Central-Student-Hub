import { ExamResponse } from "../StudentInterface/ShowExamTable";

export class ExamsApi {
    async getExams(): Promise<ExamResponse[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Exams`, requestOptions);
        return await response.json();
    }
}