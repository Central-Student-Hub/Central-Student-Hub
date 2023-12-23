import { TeachingStaffProfileInfo } from '../Models/TeachingStaffProfileInfo.ts';

export class UserProfileApi {
    async getTeachingStaffProfile(): Promise<TeachingStaffProfileInfo> {
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}` };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, method: "get", credentials: "include" };
        const response: Response = await fetch("http://localhost:8082/Profile/getTeacherProfile", requestOptions);
        return await response.json();
    }

    async updateTeachingStaffProfile(profile: TeachingStaffProfileInfo): Promise<void> {
        profile = { ...profile, officeHours: profile.officeHours.map((officeHour) => {
            if (officeHour.fromTime.length === 5) return { ...officeHour, fromTime: officeHour.fromTime + ":00", toTime: officeHour.toTime + ":00" }
            return officeHour;
        })};
        console.log(profile);
        const token = document.cookie.split("=")[1];
        const headers: HeadersInit = { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" };
        const requestOptions: RequestInit = { mode: 'cors', headers: headers, body: JSON.stringify(profile), method: "put", credentials: "include" };
        const response: Response = await fetch("http://localhost:8082/Profile/updateTeacherProfile", requestOptions);
        return await response.json();
    }
}