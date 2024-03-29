import { WarningRequest } from '../Models/WarningRequest.ts';
import { AdminAddCourseRequest } from '../Models/AdminAddCourseRequest.ts';
import { AdminAddSemesterCourseRequest } from '../Models/AdminAddSemesterCourseRequest.ts';
import { AdminAddSessionRequest } from '../Models/AdminAddSessionRequest.ts';
import { AdminCourseResponse } from '../Models/AdminCourseResponse.ts';
import { SemesterCourseResponse } from '../Models/SemesterCourseResponse.ts';
import { StudentProfileInfo } from '../Models/StudentProfileInfo.ts';
import { TeachingStaffProfileInfo } from '../Models/TeachingStaffProfileInfo.ts';
import { Location } from '../Models/Location.ts';
import { AddCourseGradeRequest } from '../Models/AddCourseGradeRequest.ts';
import { ExamRequest } from '../AdminComponent/AddExam.tsx';

export class AdminApi {

    async addUser(ssn: string): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "post", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/auth/addUser?ssn=${ssn}`, requestOptions);
        return await response.json();
    }

    async addCourse(request: AdminAddCourseRequest): Promise<number> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(request), method: "post", credentials: "include" };
        const response: Response = await fetch("http://localhost:8082/Course/addCourse", requestOptions);
        return await response.json();
    }

    async addCoursePrerequisite(courseId: number, prerequisiteId: number): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "post", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Course/addCoursePrerequisite?courseId=${courseId}&prerequisiteId=${prerequisiteId}`, requestOptions);
        return await response.json();
    }

    async getAllCourses(): Promise<AdminCourseResponse[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Course/getAllCourses`, requestOptions);
        return await response.json();
    }

    async addSemesterCourse(request: AdminAddSemesterCourseRequest): Promise<number> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(request), method: "post", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/SemesterCourse/addSemesterCourse`, requestOptions);
        return await response.json();
    }

    async getAllTeachingStaff(): Promise<TeachingStaffProfileInfo[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Profile/teachingStaff`, requestOptions);
        return await response.json();
    }

    async getAllStudents(): Promise<StudentProfileInfo[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Profile/students`, requestOptions);
        return await response.json();
    }

    async addSession(request: AdminAddSessionRequest): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(request), method: "post", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/SemesterCourse/addSession`, requestOptions);
        return await response.json();
    }

    async setDeadline(date: Date): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(date), method: "post", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Register/setPaymentDeadline`, requestOptions);
        return await response.json();
    }

    async addWarning(request: WarningRequest): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(request), method: "post", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Profile/addWarning`, requestOptions);
        return await response.json();
    }

    async getAllSemesterCourses(): Promise<SemesterCourseResponse[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/SemesterCourse/getSemesterCourses`, requestOptions);
        return await response.json();
    }

    async deleteSemesterCourse(semCourseId: number): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "delete", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/SemesterCourse/deleteSemesterCourse/${semCourseId}`, requestOptions);
        return await response.json();
    }

    async deleteAllSemesterCourses(): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "delete", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/SemesterCourse/deleteAllSemesterCourses`, requestOptions);
        return await response.json();
    }

    async deleteCourse(courseId: number): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "delete", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Course/deleteCourse/${courseId}`, requestOptions);
        return await response.json();
    }

    async deleteAllCourses(): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "delete", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Course/deleteAllCourses`, requestOptions);
        return await response.json();
    }

    async getAllLocations(): Promise<Location[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/SemesterCourse/location`, requestOptions);
        return await response.json();
    }

    async updateStudentProfile(profile: StudentProfileInfo): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(profile), method: "put", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Profile/updateStudentProfile/${profile.id}`, requestOptions);
        return await response.json();
    }

    async getStudentsBySemesterCourse(semCourseId: number): Promise<StudentProfileInfo[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Register/getStudentsBySemesterCourse/${semCourseId}`, requestOptions);
        return await response.json();
    }

    async updateStudentGrades(request: AddCourseGradeRequest): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(request), method: "post", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/`, requestOptions);
        return response.json();
    }  

    async addLocation(request: Location): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(request), method: "post", credentials: "include" };
        const response: Response = await fetch('http://localhost:8082/SemesterCourse/addLocation', requestOptions);
        return await response.json();
    }

    async distributeExams(request: ExamRequest): Promise<boolean> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(request), method: "post", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Exams/distribute`, requestOptions);
        return await response.json();
    }
}
