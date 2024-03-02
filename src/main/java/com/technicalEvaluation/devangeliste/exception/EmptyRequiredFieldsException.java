package com.technicalEvaluation.devangeliste.exception;

public class EmptyRequiredFieldsException extends IllegalArgumentException {
    public EmptyRequiredFieldsException(String message){
        super(message);
    }
}
