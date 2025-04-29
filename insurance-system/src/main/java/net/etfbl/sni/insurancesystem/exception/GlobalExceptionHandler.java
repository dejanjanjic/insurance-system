package net.etfbl.sni.insurancesystem.exception;

import net.etfbl.sni.insurancesystem.model.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseMessage> userAlreadyExistsException(UserAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage(e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseMessage> wrongRoleException(UsernameNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseMessage> globalExceptionHandler(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(e.getMessage()));
    }


}
