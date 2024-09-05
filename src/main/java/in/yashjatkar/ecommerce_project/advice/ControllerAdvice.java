package in.yashjatkar.ecommerce_project.advice;

import in.yashjatkar.ecommerce_project.Dto.ErrorDto;
import in.yashjatkar.ecommerce_project.Exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception) {
        // Create an ErrorDto object with the exception message
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());

        // Return the ResponseEntity with the ErrorDto and HTTP status 404 Not Found
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
