package com.example.estacionamento.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class EstabelecimentoNaoEncontradoException extends EntityNotFoundException {

    public EstabelecimentoNaoEncontradoException(){
        super("Estabelecimento não encontrado");
    }

    public EstabelecimentoNaoEncontradoException(String message) {
        super(message);
    }
}
