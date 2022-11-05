package com.example.volexuiservice.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.volexuiservice.dto.ErrorDetails;
import com.example.volexuiservice.dto.ErrorValidate;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handlerResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),false,HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<>(errorDetails,HttpStatus.OK);
		
	}
	
	@ExceptionHandler(AccountAppException.class)
	public ResponseEntity<ErrorDetails> handlerAccountException(AccountAppException exception, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),exception.getSuccess(),exception.getStatus(),exception.getCode());
		
		return new ResponseEntity<>(errorDetails,HttpStatus.OK);
		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handlerGlobalException(Exception exception, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),false,HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<>(errorDetails,HttpStatus.OK);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){
		
		Map<String, String> errores= new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String nameField = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			
			errores.put(nameField, message);
			
			
		});
		
		ErrorValidate errorValidate = new ErrorValidate(new Date(),errores,false,HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<>(errorValidate,HttpStatus.OK);
	}

}
