package dev.antoniogrillo.esempiocinecaavanzato.exception;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.MessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<MessageDTO> handleException(SQLIntegrityConstraintViolationException e){
        MessageDTO m= new MessageDTO(e.getMessage(), LocalDateTime.now(),400);
        return ResponseEntity.badRequest().body(m);
    }
}
