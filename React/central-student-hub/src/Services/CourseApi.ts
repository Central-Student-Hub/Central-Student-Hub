import { AnnouncementsReturn, AssignmentsReturn, CourseReturn } from '../StudentInterface/Course.tsx';

export class CourseApi {

    async getSemCourseByStudentId(): Promise<CourseReturn[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch("http://localhost:8082/SemesterCourse/semCourseByStudentId", requestOptions);
        return await response.json();
    }

    async getMaterialPathByCourseId(id): Promise<string[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/SemesterCourse/getMaterialPaths/${id}`, requestOptions);
        return await response.json();
    }

    async getAssignmentsByCourseId(id): Promise<AssignmentsReturn[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Assignment/getAllAssignmentByCourseId?courseId=${id}`, requestOptions);
        return await response.json();
    }

    async getAnnouncementsByCourseId(id): Promise<AnnouncementsReturn[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch(`http://localhost:8082/Announcement/getAnnouncementBySemCourseId?semCourseId=${id}`, requestOptions);
        return await response.json();
    }

}
