package com.imageuplod.RESTIMAGE.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppException extends ResponseEntityExceptionHandler{
	
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception{
       ExceptionResponse exceptionResponse= new ExceptionResponse
               (new Date(),ex.getMessage(),request.getDescription(false));

    return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
  @ExceptionHandler(RegisterException.class)
  public final ResponseEntity<Object> handleBadInput(Exception ex, WebRequest request) throws Exception{
     ExceptionResponse exceptionResponse= new ExceptionResponse
             (new Date(),ex.getMessage(),request.getDescription(false));

  return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
    
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

      ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),"Validation Failed",
              ex.getBindingResult().toString());
      return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
  } 
    
    
    

//    @ExceptionHandler(DataNotFoundException.class)
//    public final ResponseEntity<Object> handleBadInput(Exception ex, WebRequest request) throws Exception{
//       ExceptionResponse exceptionResponse= new ExceptionResponse
//               (new Date(),ex.getMessage(),request.getDescription(false));
//
//    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }
    
//    @ExceptionHandler(FailedToSaveException.class)
//    public final ResponseEntity<Object> failedSave(Exception ex, WebRequest request) throws Exception{
//       ExceptionResponse exceptionResponse= new ExceptionResponse
//               (new Date(),ex.getMessage(),request.getDescription(false));
//
//    return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    
}
