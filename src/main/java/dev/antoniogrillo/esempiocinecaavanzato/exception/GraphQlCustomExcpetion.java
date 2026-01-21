package dev.antoniogrillo.esempiocinecaavanzato.exception;

import lombok.Getter;
import org.springframework.graphql.execution.ErrorType;

@Getter
public class GraphQlCustomExcpetion extends RuntimeException{

    private final ErrorType type;

    public GraphQlCustomExcpetion(ErrorType type, String message) {
        super(message);
        this.type=type;
    }
}
