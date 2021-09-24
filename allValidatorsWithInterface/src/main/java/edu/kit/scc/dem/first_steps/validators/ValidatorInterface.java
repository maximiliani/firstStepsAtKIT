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
    boolean isValid(String[] input) throws Exception;
}
