package com.example.estacionamento.exceptions;

public class MovimentacaoNaoEncontradaException extends RuntimeException {

    public MovimentacaoNaoEncontradaException(){
        super("Movimentação não encontrada");
    }

    public MovimentacaoNaoEncontradaException(String message) {
        super(message);
    }
}
