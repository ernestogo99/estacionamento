package com.example.estacionamento.exceptions;

public class EstabelecimentoNaoEncontradoException extends RuntimeException {

    public EstabelecimentoNaoEncontradoException(){
        super("Estabelecimento não encontrado");
    }

    public EstabelecimentoNaoEncontradoException(String message) {
        super(message);
    }
}
