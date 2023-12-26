import { Session } from "./Session";

type Semester = "FALL" | "SPRING" | "SUMMER";

export type Course = {
    semCourseId: number,
    code: string,
    name: string,
    description: string,
    creditHours: number,
    prerequisitesCodes: string[],
    maxSeats: number,
    remainingSeats: number,
    sessions: Session[],
    semester: Semester
};
  