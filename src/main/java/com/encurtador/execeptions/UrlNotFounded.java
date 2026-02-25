package com.encurtador.execeptions;

public class UrlNotFounded extends RuntimeException {
    public UrlNotFounded(String message) {
        super("Url não encontrada.");
    }
}
