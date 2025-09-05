package com.example.estacionamento.utils;

import com.example.estacionamento.exceptions.*;
import com.example.estacionamento.shared.dto.response.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(VeiculoNaoEncontradoException.class)
    public ResponseEntity<ExceptionDTO> threatVehicleNotFound(VeiculoNaoEncontradoException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.toString()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(EstabelecimentoNaoEncontradoException.class)
    public ResponseEntity<ExceptionDTO> threatEstabelecimentoNotFound(EstabelecimentoNaoEncontradoException exception){
        ExceptionDTO exceptionDTO=new ExceptionDTO(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(MovimentacaoNaoEncontradaException.class)
    public ResponseEntity<ExceptionDTO> threatMovimentacaoNotFound(MovimentacaoNaoEncontradaException exception){
        ExceptionDTO exceptionDTO=new ExceptionDTO(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.toString()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(EstabelecimentoLotadoException.class)
    public  ResponseEntity<ExceptionDTO> threatEstabelecimentoLotado(EstabelecimentoLotadoException exception){
        ExceptionDTO exceptionDTO=new ExceptionDTO(
                exception.getMessage(),
                HttpStatus.CONFLICT.toString()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDTO);
    }

    @ExceptionHandler(VeiculoNaoEstacionadoException.class)
    public ResponseEntity<ExceptionDTO> threatVeiculoNaoestacionado(VeiculoNaoEstacionadoException exception){
        ExceptionDTO exceptionDTO=new ExceptionDTO(
                exception.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY.toString()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exceptionDTO);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralExceptions(Exception exception){
        ExceptionDTO exceptionDTO=new ExceptionDTO(exception.getMessage(),"500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {

        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ExceptionDTO response = new ExceptionDTO(errors, HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
