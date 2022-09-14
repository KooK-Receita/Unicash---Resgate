package io.swagger.api;

import java.util.HashSet;

public class ApiException extends Exception {

    public HashSet<String> erros = new HashSet<>();

    public ApiException(HashSet<String> erros) {
        this.erros = erros;
    }
}
