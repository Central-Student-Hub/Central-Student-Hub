import TeachingStaffProfile from "../UserProfileComponent/TeachingStaffProfile"
import { ContactInfo } from "./ContactInfo"
import { Grade } from "./Grade"
import { Warning } from "./Warning"

export type StudentProfileInfo = {
    firstName: string,
    lastName: string,
    biography: string,
    profilePictureUrl: string,
    major: string,
    minor: string,
    noOfHours: number,
    gpa: number,
    level: number,
    contacts: ContactInfo[],
    warnings: Warning[],
    grades: Grade[],
}
