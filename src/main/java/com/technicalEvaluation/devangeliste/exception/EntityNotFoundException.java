package com.technicalEvaluation.devangeliste.exception;

public class EntityNotFoundException extends NoSuchFieldException {
    public EntityNotFoundException(String message){
        super(message);
    }
}
