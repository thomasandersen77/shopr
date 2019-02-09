package io.shopr.common;

public class ShoprException extends RuntimeException {

    private final Type type;

    public ShoprException(String message, Type type) {
        super(message);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        SERVER_ERROR,
        CLIENT_ERROR
    }
}
