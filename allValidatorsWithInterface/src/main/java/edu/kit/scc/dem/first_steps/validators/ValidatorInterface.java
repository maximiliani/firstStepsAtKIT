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
    boolean isValid(String[] input) throws ValidationException;
    String getInputMessage();
    void askForInputAndValidate() throws ValidationException {
      String inputMessage = getInputMessage();
      System.out.println(inputMessage);
      // Read input
      //some code here
       isValid(input);
    }
      
      
    };
}
