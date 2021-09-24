package edu.kit.scc.dem.first_steps.validators;

/**
 * This interface provides two methods which are necessary for validating things.
 * @author maximilianiKIT
 */
public interface ValidatorInterface {
    void processIsValid(String[] input) throws Exception;

    boolean isValidInput(String[] input) throws Exception;
}
