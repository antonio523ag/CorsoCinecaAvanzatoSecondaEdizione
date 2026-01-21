package dev.antoniogrillo.esempiocinecaavanzato.exception;

import dev.antoniogrillo.esempiocinecaavanzato.dto.response.MessageDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<MessageDTO> handleException(SQLIntegrityConstraintViolationException e){
        MessageDTO m= new MessageDTO(e.getMessage(), LocalDateTime.now(),400);
        return ResponseEntity.badRequest().body(m);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleException(MethodArgumentNotValidException e){
        Map<String,String> map=new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fe->{
            String nomeCampo=fe.getField();
            String messaggioErrore=fe.getDefaultMessage();
            map.put(nomeCampo,messaggioErrore);
        });
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageDTO> handleException(ConstraintViolationException e){
        MessageDTO m= new MessageDTO(e.getMessage(), LocalDateTime.now(),400);
        return ResponseEntity.badRequest().body(m);
    }
}
