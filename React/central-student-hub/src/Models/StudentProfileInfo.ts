import { ContactInfo } from "./ContactInfo.ts"
import { Warning } from "./Warning.ts"

export type StudentProfileInfo = {
    id: number,
    firstName: string,
    lastName: string,
    biography: string,
    major: string,
    minor: string,
    level: number,
    noOfHours: number,
    gpa: number,
    profilePictureUrl: string,
    contacts: ContactInfo[],
    warnings: Warning[],
}

export const s: StudentProfileInfo = {
    id: 1,
    firstName: "John",
    lastName: "Doe",
    biography: "I am a student",
    major: "Computer Science",
    minor: "Math",
    level: 3,
    noOfHours: 12,
    gpa: 3.5,
    profilePictureUrl: "https://res.cloudinary.com/dwux7kkey/image/upload/t_Maro/v1703602589/CentralStudentHub/Maro_galy1w.jpg",
    contacts: [
        {
            label: "Email",
            data: "JohnDoe@gmail.com"
        },
        {
            label: "Phone",
            data: "123-456-7890"
        },
        {
            label: "Address",
            data: "1234 Main St, Springfield, IL 62701"
        },
        {
            label: "Email",
            data: "JohnDoe@gmail.com"
        },
        {
            label: "Phone",
            data: "123-456-7890"
        },
        {
            label: "Address",
            data: "1234 Main St, Springfield, IL 62701"
        }
    ],
    warnings: [
        {
            warningId: 1,
            reason: "Low GPA",
            date: new Date("2021-03-01")
        },
        {
            warningId: 2,
            reason: "Low GPA",
            date: new Date("2021-04-01")
        }
    ]
}
