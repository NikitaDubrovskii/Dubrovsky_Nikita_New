package dev.dubrovsky;

public class DbException extends RuntimeException {

    public DbException() {
        super();
    }

    public DbException(String message) {
        super(message);
    }

}