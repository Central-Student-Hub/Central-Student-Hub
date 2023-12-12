package com.centralstudenthub.error;

public class CourseAlreadyExistsException extends Exception{
    public CourseAlreadyExistsException(String message) {
        super(message);
    }
}
