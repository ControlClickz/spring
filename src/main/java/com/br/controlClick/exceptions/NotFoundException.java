package com.br.controlClick.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Algo não foi encontrado!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
