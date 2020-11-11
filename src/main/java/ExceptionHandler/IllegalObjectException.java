package ExceptionHandler;

public class IllegalObjectException extends RuntimeException{

    private String message;

    public IllegalObjectException(String message) {
        super();
        this.message = message;
    }
}