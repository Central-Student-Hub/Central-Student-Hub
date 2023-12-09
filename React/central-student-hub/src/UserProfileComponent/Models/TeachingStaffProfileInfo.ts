import { OfficeHours } from "./OfficeHours"

export type TeachingStaffProfileInfo = {
    teacherId: number,
    firstName: string,
    lastName: string,
    biography: string,
    profilePictureUrl: string,
    department: string
    contactData: ContactInfo[],
    officeHours: OfficeHours[]
}