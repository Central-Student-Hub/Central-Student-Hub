import { Semester } from "./AdminAddSemesterCourseRequest"

export type SemesterCourseResponse = {
    semCourseId: number,
    code: string,
    name: string,
    description: string,
    creditHours: number,
    semester: Semester,
    maxSeats: number
}