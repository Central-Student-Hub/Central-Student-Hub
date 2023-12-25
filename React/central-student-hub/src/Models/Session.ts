import { Location } from "./Location";

export type SessionType = "LECTURE" | "TUTORIAL" | "LAB";

export type Session = {
    id: number;
    period: number;
    weekday: string;
    sessionType: SessionType;
    teacherName: string;
    location: Location;
};
