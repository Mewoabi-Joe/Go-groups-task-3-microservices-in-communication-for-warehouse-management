package gogroups.warehouseapp.uidrivenservice.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MissenRequestHeaderHandler {
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMissenRequestHeaderException(MissingRequestHeaderException ex) {
        Map<String, String> subErrors = new HashMap<>();

            subErrors.put("error",ex.getMessage());
        return new ResponseEntity<>(subErrors,HttpStatus.BAD_REQUEST);
    }
}
