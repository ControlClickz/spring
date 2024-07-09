package com.br.controlClick.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException() {
        super("Algum registro já existe com essas informações!");
    }

    public AlreadyExistsException(String message) {
        super(message);
    }
}
