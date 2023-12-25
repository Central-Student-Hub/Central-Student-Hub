export type SessionType = "LECTURE" | "LAB" | "TUTORIAL";

export type AdminAddSessionRequest = {
    semCourseId: number,
    teacherId: number,
    period: number,
    weekDay: string,
    sessionType: SessionType
}
