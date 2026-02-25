package com.encurtador.execeptions;

public class UrlExpired extends RuntimeException {
    public UrlExpired(String message) {
        super("Url expirada.");
    }
}
