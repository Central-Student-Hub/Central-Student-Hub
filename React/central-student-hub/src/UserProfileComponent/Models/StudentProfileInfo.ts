import TeachingStaffProfile from "../TeachingStaffProfile/TeachingStaffProfile"
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
    contacts: ContactInfo[],
    warnings: Warning[],
    grades: Grade[],
}

const s: StudentProfileInfo = {
    firstName: "John",
    lastName: "Doe",
    biography: "I am a student",
    profilePictureUrl: "https://i.imgur.com/5N5J7bP.png",
    major: "Computer Science",
    minor: "Math",
    noOfHours: 12,
    gpa: 3.5,
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
    ],
    grades: [
        {
            courseName: "DS",
            grade: 90
        },
        {
            courseName: "OS",
            grade: 80
        },
        {
            courseName: "AI",
            grade: 70
        }
    ],
}