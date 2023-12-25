export type Semester = "FALL" | "SPRING" | "SUMMER";

export type AdminAddSemesterCourseRequest = {
    courseId: number
    semester: Semester;
    maxSeats: number;
}
