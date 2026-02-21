package com.patientManagement.hospital_management.Exceptions;

public class MethodArgumentTypeMismatchException extends RuntimeException{
    public MethodArgumentTypeMismatchException(String message){
        super(message);
    }
}
