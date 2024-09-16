package dev.dubrovsky.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DbResponseErrorException extends RuntimeException {

    public DbResponseErrorException() {
        super();
    }

    public DbResponseErrorException(String message) {
        super(message);
    }

}
