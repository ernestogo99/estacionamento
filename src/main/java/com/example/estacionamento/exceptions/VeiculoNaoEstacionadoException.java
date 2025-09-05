package com.example.estacionamento.exceptions;

public class VeiculoNaoEstacionadoException extends RuntimeException {
    public VeiculoNaoEstacionadoException(String message) {
        super(message);
    }
}
