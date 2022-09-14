package io.swagger.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-09-10T19:42:49.684Z")

public class ApiError {

    @JsonProperty("errors")
    HashSet<String> errors;
    public ApiError (HashSet<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "errors=" + errors +
                '}';
    }
}
