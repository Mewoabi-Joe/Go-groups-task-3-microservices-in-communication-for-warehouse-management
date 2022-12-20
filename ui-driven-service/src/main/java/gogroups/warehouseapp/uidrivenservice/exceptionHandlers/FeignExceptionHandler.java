package gogroups.warehouseapp.uidrivenservice.exceptionHandlers;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class FeignExceptionHandler {
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignExceptions(FeignException ex) {
        String exMessage = ex.getMessage();

        int codeStartIndex = exMessage.indexOf("[");
        int codeEndIndex = exMessage.indexOf("]");
        int code = Integer.parseInt(exMessage.substring(codeStartIndex + 1, codeEndIndex));

        int msgStartIndex = exMessage.lastIndexOf("{");
        int msgEndIndex = exMessage.indexOf("}");
        String actualExMessage = exMessage.substring(msgStartIndex + 10, msgEndIndex - 1);

        Map<String, String> subErrors = new HashMap<>();

        subErrors.put("error", actualExMessage);

        return new ResponseEntity<>(subErrors, HttpStatus.resolve(code));

//        String exMessage = ex.getMessage();
//        System.out.println("exMessage: "+ exMessage);
//        int msgStartIndex = exMessage.lastIndexOf("\\\"");
//        int msgEndIndex = exMessage.indexOf("\\\"");
//        int codeStartIndex = exMessage.indexOf("[");
//        int codeEndIndex = exMessage.indexOf("]");
//            subErrors.put("error",exMessage.substring(msgStartIndex, msgEndIndex));
//        System.out.println("code: " + exMessage.substring(codeStartIndex, codeEndIndex));
//        return new ResponseEntity<>(exMessage,HttpStatus.BAD_REQUEST);
    }
}
