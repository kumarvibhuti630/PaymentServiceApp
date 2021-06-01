package com.paymentSystem.exception;
import com.paymentSystem.customExceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandling {
    @org.springframework.web.bind.annotation.ExceptionHandler(ErrorResponse.class)
    public ResponseEntity<String> emptyInputException(ErrorResponse errorResponse) {
        if (errorResponse.getErrorCode() == "404") {
            return new ResponseEntity<String>(errorResponse.getErrorMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(errorResponse.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler (MethodArgumentNotValidException methodArgumentNotValidException){
        return new ResponseEntity<String>("Input values must not be empty",HttpStatus.NOT_ACCEPTABLE);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler (IllegalArgumentException illegalArgumentException){
        return new ResponseEntity<String>("Invalid Request is made",HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationExceptionHandler (ConstraintViolationException constraintViolationException)
    {
        return new ResponseEntity<String>("Input values violates input constraint range",HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementException(NoSuchElementException noSuchElementExceptions) {
        return new ResponseEntity<String>("No such element exist, Please change your request.", HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpStatus status, WebRequest request) {
        return new ResponseEntity<String>("Invalid request is made", HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
        return new ResponseEntity<String>("Order Id is not Valid.", HttpStatus.NOT_ACCEPTABLE);
    }
}
