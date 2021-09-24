package edu.kit.scc.dem.first_steps.validators;

/**
 * This interface provides two methods which are necessary for validating things.
 * @author maximilianiKIT
 */
public interface ValidatorInterface {
    /**
     * input is an array!?
     * @param input
     * @throws Exception 
     */
    void processIsValid(String[] input) throws Exception;

    boolean isValidInput(String[] input) throws Exception;
}
