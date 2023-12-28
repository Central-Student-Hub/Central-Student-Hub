import { TeachingStaffProfileInfo } from '../Models/TeachingStaffProfileInfo.ts';
import { CourseReturn } from '../StudentInterface/Course.tsx';

export class CourseApi {
    
    async getSemCourseByStudentId(): Promise<CourseReturn[]> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch("http://localhost:8082/SemesterCourse/semCourseByStudentId", requestOptions);
        return await response.json();
    }
}
