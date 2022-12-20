package gogroups.warehouseapp.inventoryservice.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RequiredRequestBodyHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleRequiredRequestBodyException(HttpMessageNotReadableException ex) {
        Map<String, String> subErrors = new HashMap<>();

            subErrors.put("error",ex.getMessage().substring(0, ex.getMessage().indexOf(":")));
        return new ResponseEntity<>(subErrors,HttpStatus.BAD_REQUEST);
    }
}
