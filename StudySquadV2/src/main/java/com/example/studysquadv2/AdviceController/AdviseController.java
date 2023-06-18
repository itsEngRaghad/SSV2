package com.example.studysquadv2.AdviceController;

import com.example.studysquadv2.ApiException.ApiException;
import com.example.studysquadv2.ApiResponse.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice

public class AdviseController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> APIException(ApiException e){
        String message= e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ApiResponse>ArithmeticException(ArithmeticException e){
        String message= e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    // Argument Not Valid

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse>MethodArgumentNotValidException(MethodArgumentNotValidException e){
        String message= e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    //Mismatch

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse>MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        String message=e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));

    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> DataIntegrityViolationException(DataIntegrityViolationException e){
        String message=e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    // Json message not readable
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ApiResponse> TransactionSystemException(TransactionSystemException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }
}
