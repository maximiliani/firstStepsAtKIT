package edu.kit.scc.dem.first_steps.validators.exceptions;

/**
 * This class is a custom exception for handling validation errors.
 * @author maximilianiKIT
 */
public class ValidationException extends Exception {

    /**
     * This constructor expects an errorMessage and a Throwable for more information.
     *
     * @param errorMessage is a description about what happened.
     * @param err          is a Throwable from former errors like IllegalArgumentExceptions, which are not handled by the caller class.
     */
    public ValidationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    /**
     * This is an empty constructor with no additional information.
     */
    public ValidationException() {
        super();
    }
}
