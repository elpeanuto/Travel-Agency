package edu.elpeanuto.tms.servies.exception;

/**
 * FailToUpdateDBException is thrown when fields in the database have not been modified, added or deleted
 */
public class FailToUpdateDBException extends Exception{
    public FailToUpdateDBException() {
    }

    public FailToUpdateDBException(String message) {
        super(message);
    }

    public FailToUpdateDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
