package edu.elpeanuto.tms.servies.exception;

/**
 * HTMLValueParseException is thrown when value from JSP page selector not suitable for writing to the database
 * Example: variable can only store value 'Yes' or 'No'
 *          JSP selector value="Yes" - correct
 *          JSP selector value="Yse" - HTMLValueParseException will be thrown
 */
public class EnumParseException extends RuntimeException{
    public EnumParseException() {
    }

    public EnumParseException(String message) {
        super(message);
    }

    public EnumParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
