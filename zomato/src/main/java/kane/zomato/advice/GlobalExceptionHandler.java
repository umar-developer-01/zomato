package kane.zomato.advice;


import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import kane.zomato.exception.ResourceNotFoundException;
import org.springframework.security.core.AuthenticationException;
import io.jsonwebtoken.JwtException;
import org.springframework.security.access.AccessDeniedException;


import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {

        List<String> subErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Validation failed")
                .subErrors(subErrors)
                .build();

        return buildErrorResponseEntity(apiError);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException ex) {
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.UNAUTHORIZED)
//                .message(ex.getMessage())
//                .build();
//        return buildErrorResponseEntity(apiError);
//    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<?>> handleJwtException(JwtException ex) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(ex.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }


//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException ex) {
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.FORBIDDEN)
//                .message(ex.getMessage())
//                .build();
//        return buildErrorResponseEntity(apiError);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }

}


//    {
//        "timeStamp": "2026-01-09T11:55:30.123",
//        "data": null,
//        "error": {
//                "status": "FORBIDDEN",
//                "message": "Access is denied",
//                "subErrors": null
//                 }
//     }











