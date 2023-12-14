import { Session } from "../Registration"

export type AddCourseToCartRequest = {
    courseId: number,
    creditHours: number,
    sessions: Session[],
    newSession: Session
}