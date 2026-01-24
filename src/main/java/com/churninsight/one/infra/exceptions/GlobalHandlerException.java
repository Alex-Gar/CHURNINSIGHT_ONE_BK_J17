package com.churninsight.one.infra.exceptions;

import com.churninsight.one.infra.exceptions.ex.ResourceNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EntityExistsException;
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
    public ResponseEntity<Map<String, Object>> handleJsonParseError(HttpMessageNotReadableException ex) {
        Throwable causa = ex.getCause();
        Map<String, Object> body = new HashMap<>();

        if (causa instanceof InvalidFormatException ife) {
            body.put("error", "Valor invalido en el JSON");
            body.put("campo", ife.getPath().get(0).getFieldName());
            body.put("valor", ife.getValue());
            body.put("mensaje", "El formato del dato no es correcto para este campo.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }

        body.put("error", "El JSON está mal formado");

        String mesnajeAmigable = (ex.getMessage() != null && ex.getMessage().contains("Required request body is missing"))
                ? "El cuerpo de la solicitud es obligatorio y no fue enviado."
                : "Error al procesar el JSON. Verifica la sintaxis.";

        body.put("mensaje", mesnajeAmigable);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handlerInlegaArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "error", "Valor invalido en el JSON",
                        "mensaje", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handlerMethodArgument(MethodArgumentNotValidException ex) {
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

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(jakarta.persistence.EntityExistsException.class)
    public ResponseEntity<Map<String, Object>> handlerEntityExists(EntityExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "error", "Conflicto de duplcidad",
                "mensaje", ex.getMessage()
        ));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of(
                "error", "Servicio de prediccion no disponible",
                "mensaje", "No se pudo procesar la solicitud. El servicio de predicción no está disponible"
        ));
    }

}
