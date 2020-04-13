package exception;

public class DuplicateLessonException extends Exception{

    public DuplicateLessonException() {
    }

    public DuplicateLessonException(String message) {
        super(message);
    }

    public DuplicateLessonException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateLessonException(Throwable cause) {
        super(cause);
    }

    public DuplicateLessonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
