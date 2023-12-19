import { OfficeHours } from "./OfficeHours"
import { ContactInfo } from "./ContactInfo"

export type TeachingStaffProfileInfo = {
    firstName: string,
    lastName: string,
    biography: string,
    profilePictureUrl: string,
    department: string
    contacts: ContactInfo[],
    officeHours: OfficeHours[]
}