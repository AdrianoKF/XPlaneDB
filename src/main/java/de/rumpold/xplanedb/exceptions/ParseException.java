package de.rumpold.xplanedb.exceptions;

/**
 * Created by Adriano on 14.07.2015.
 */
public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
}
