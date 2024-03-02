package com.technicalEvaluation.devangeliste.exception.handler;

import com.technicalEvaluation.devangeliste.exception.EmptyRequiredFieldsException;
import com.technicalEvaluation.devangeliste.exception.EmptyTextException;
import com.technicalEvaluation.devangeliste.exception.EntityNotFoundException;
import com.technicalEvaluation.devangeliste.exception.ExcessInputParametersException;
import com.technicalEvaluation.devangeliste.exception.InputTechnologyInvalidatesException;
import com.technicalEvaluation.devangeliste.exception.InvalidFieldFormatException;
import com.technicalEvaluation.devangeliste.message.MessagesOfExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerToExceptions {

    @ExceptionHandler(EmptyRequiredFieldsException.class)
    public ResponseEntity<MessagesOfExceptions> emptyRequiredFieldsException(EmptyRequiredFieldsException e){
        MessagesOfExceptions message = new MessagesOfExceptions();
        message.setMessage(e.getMessage());
        message.setDetail("There are required fields that are empty.");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFieldFormatException.class)
    public ResponseEntity<MessagesOfExceptions> invalidFieldFormatException(InvalidFieldFormatException e){
        MessagesOfExceptions message = new MessagesOfExceptions();
        message.setMessage(e.getMessage());
        message.setDetail("There are fields that have an invalid format.");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessagesOfExceptions> entityNotFoundException(EntityNotFoundException e){
        MessagesOfExceptions message = new MessagesOfExceptions();
        message.setMessage(e.getMessage());
        message.setDetail("The requested entity does not exist.");
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyTextException.class)
    public ResponseEntity<MessagesOfExceptions> emptyTextException(EmptyTextException e) {
        MessagesOfExceptions message = new MessagesOfExceptions();
        message.setMessage(e.getMessage());
        message.setDetail("The text entered cannot be null and void.");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExcessInputParametersException.class)
    public ResponseEntity<MessagesOfExceptions> excessInputParametersException(ExcessInputParametersException e) {
        MessagesOfExceptions message = new MessagesOfExceptions();
        message.setMessage(e.getMessage());
        message.setDetail("More parameters than allowed were entered.");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InputTechnologyInvalidatesException.class)
    public ResponseEntity<MessagesOfExceptions> inputTechnologyInvalidatesException(InputTechnologyInvalidatesException e) {
        MessagesOfExceptions message = new MessagesOfExceptions();
        message.setMessage(e.getMessage());
        message.setDetail("A technology was entered that is not allowed/saved in the system.");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
