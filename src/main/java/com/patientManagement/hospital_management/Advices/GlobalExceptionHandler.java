package com.patientManagement.hospital_management.Advices;

import com.patientManagement.hospital_management.Exceptions.BadRequestException;
import com.patientManagement.hospital_management.Exceptions.DataIntegrityViolationException;
import com.patientManagement.hospital_management.Exceptions.MethodArgumentTypeMismatchException;
import com.patientManagement.hospital_management.Exceptions.ResourceNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> resourceNotFoundHandler(ResourceNotFoundException exception){
        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildErrorApiResponse(apiError);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<?>> handleBadRequest(BadRequestException exception){
        ApiError error = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage())
                .build();
        return buildErrorApiResponse(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ApiResponse<?>> databaseErrosHandler(DataIntegrityViolationException exception){
            ApiError apiError = ApiError.builder()
                    .status(HttpStatus.CONFLICT)
                    .message(exception.getMessage())
                    .build();
            return buildErrorApiResponse(apiError);
        }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ApiResponse<?>> handleTypeMissMatch(MethodArgumentTypeMismatchException exception){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage())
                .build();

        return buildErrorApiResponse(apiError);
        }

    private ResponseEntity<ApiResponse<?>> buildErrorApiResponse(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> haldleInputValidationError(MethodArgumentNotValidException exception){
        List<String> errorList = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();
        ApiError error = ApiError
                .builder()
                .status(HttpStatus.BAD_REQUEST) // StatusCode=400 (badReqest)
                .message("input validation error")
                .subError(errorList)
                .build();
        return  new ResponseEntity<>(new ApiResponse<>(error), error.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception exception){
        ApiError error = ApiError
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(new ApiResponse<>(error), error.getStatus());
    }
}
