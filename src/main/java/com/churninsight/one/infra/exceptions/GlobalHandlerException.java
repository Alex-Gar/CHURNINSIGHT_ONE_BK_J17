package com.churninsight.one.infra.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleJsonParseError(HttpMessageNotReadableException ex){
        Throwable causa = ex.getCause();

        if (causa instanceof InvalidFormatException ife) {
            Map<String, Object> body = new HashMap<>();
            body.put("error", "Valor invalido en el JSON");
            body.put("campo", ife.getPath().get(0).getFieldName());
            body.put("valor", ife.getValue());
            body.put("mensaje", ife.getOriginalMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "error", "El JSON esta mal Fromado", "mensaje", ex.getMessage()
                ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handlerInlegaArgument(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "error", "Valor invalido en el JSON",
                        "mensaje", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handlerMethodArgument(MethodArgumentNotValidException ex){
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(
                        error -> errores.put(error.getField(), error.getDefaultMessage())
                );
        return ResponseEntity.badRequest().body(
                Map.of(
                        "error", "validacion fallida",
                        "campos", errores
                )
        );
    }

}
