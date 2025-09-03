package com.example.estacionamento.exceptions;

public class VeiculoNaoEncontradoException extends RuntimeException {

    public VeiculoNaoEncontradoException(){
        super("Veiculo não encontrado");
    }

    public VeiculoNaoEncontradoException(String message) {
        super(message);
    }
}
