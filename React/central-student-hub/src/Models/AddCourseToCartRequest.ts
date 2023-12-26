import { Session } from "./Session"

export type AddCourseToCartRequest = {
    courseId: number,
    creditHours: number,
    sessions: Session[],
    newSessions: Session[]
}
