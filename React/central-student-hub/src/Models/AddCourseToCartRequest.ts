import { Session } from "../StudentInterface/Registration"

export type AddCourseToCartRequest = {
    courseId: number,
    creditHours: number,
    sessions: Session[],
    newSession: Session
}