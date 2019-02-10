package io.shopr.common;

public class ShoprException extends RuntimeException {

    public ShoprException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
