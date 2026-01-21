package dev.antoniogrillo.esempiocinecaavanzato.exception;

import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class GestoreErroriGraphQL extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable t, DataFetchingEnvironment e){
        if(t instanceof ConstraintViolationException c){
            return GraphQLError.newError()
                    .message("Errore di validazione: "+c.getMessage())
                    .errorType(ErrorType.BAD_REQUEST)
                    .build();
        }else if(t instanceof GraphQlCustomExcpetion gce){
            return GraphQLError.newError()
                    .message(gce.getMessage())
                    .errorType(gce.getType())
                    .build();
        }

        return GraphQLError.newError()
                .message("Errore sconosciuto: "+t.getMessage())
                .errorType(ErrorType.INTERNAL_ERROR)
                .build();
    }
}
