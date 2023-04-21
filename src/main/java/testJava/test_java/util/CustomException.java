package testJava.test_java.util;

public class CustomException extends RuntimeException{
    public String message;

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
