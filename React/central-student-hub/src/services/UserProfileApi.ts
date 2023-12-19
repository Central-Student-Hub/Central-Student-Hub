import { TeachingStaffProfileInfo } from '../UserProfileComponent/Models/TeachingStaffProfileInfo.ts';

export class UserProfileApi {
    async getTeachingStaffProfile(): Promise<TeachingStaffProfileInfo> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch("http://localhost:8082/Profile/getTeacherProfile", requestOptions);
        return await response.json();
    }

    async updateTeachingStaffProfile(profile: TeachingStaffProfileInfo): Promise<void> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(profile), method: "get", credentials: "include" };
        const response: Response = await fetch("http://localhost:8082/Profile/updateTeacherProfile", requestOptions);
        return await response.json();
    }
}
