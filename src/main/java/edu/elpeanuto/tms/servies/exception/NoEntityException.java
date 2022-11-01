package edu.elpeanuto.tms.servies.exception;

/**
 * NoEntityException is thrown when Optional from DAO class is empty, i.e. entity not found
 */
public class NoEntityException extends Exception{
    public NoEntityException() {
    }

    public NoEntityException(String message) {
        super(message);
    }

    public NoEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
