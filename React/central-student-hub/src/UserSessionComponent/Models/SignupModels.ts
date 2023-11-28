export type UserType = "Student" | "Teacher" | "Admin"

export type SignupRequest = {
    ssn: string
    email: string,
    password: string,
    userType: UserType
}

export type SignupResponse = {
    message: string,
    accept: boolean
}
