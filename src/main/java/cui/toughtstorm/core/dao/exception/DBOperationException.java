package cui.toughtstorm.core.dao.exception;

public class DBOperationException extends Exception {

    public DBOperationException(String message) {
        new DBOperationException(message, null);
    }

    public DBOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
