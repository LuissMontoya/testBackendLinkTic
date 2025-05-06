package co.com.test.linktic.appEcommerce.Config;

import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .badRequest()
                .body(new ResponseDTO(400, errores, null));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGeneral(Exception ex) {

        String errorMessage = (ex.getCause() != null) ? ex.getCause().getMessage() : ex.getMessage();

       ResponseDTO response = new ResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                errorMessage,
                null
        );

        // Retornar una respuesta con el estado BAD_REQUEST (400) y el objeto ResponseDTO
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
