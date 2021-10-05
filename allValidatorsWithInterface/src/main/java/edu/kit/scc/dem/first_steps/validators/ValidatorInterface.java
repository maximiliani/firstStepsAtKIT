package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.validators.exceptions.ValidationException;
import edu.kit.scc.dem.first_steps.validators.impl.DomainValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * This interface provides two methods which are necessary for validating things.
 *
 * @author maximilianiKIT
 */
public interface ValidatorInterface {

    Logger log = LoggerFactory.getLogger(ValidatorInterface.class);

    /**
     * This method is implemented in every class that implements this interface.
     * It validates the given input and returns either a boolean or a ValidationException.
     *
     * @param input is the string (e.g. mail address, domain name, ...) which should get validated.
     * @return true if the input is valid.
     * @throws ValidationException with a description if the input is invalid or no input was given.
     */
    boolean isValid(String input) throws ValidationException;

    /**
     * This method prints custom query messages and should be overwritten in every validator.
     */
    default void printQueryMessage() {
      System.out.println("Please enter the text you want to validate: ");
    }

    /**
     * This method asks the user via command line for input and validates it with the isValid() method.
     *
     * @throws ValidationException with a description if an error occurs or the input is invalid.
     */
    default void askForInputAndValidate() throws ValidationException {
        Scanner userInput = new Scanner(System.in);
        printQueryMessage();
        String inputMessage = null;
        try {
            inputMessage = userInput.nextLine();
            userInput.close();
        } catch (Exception e) {
            log.error("No or invalid input.");
            log.debug("Input: {}", inputMessage);
            throw new ValidationException("No or invalid inputMessage.", e);
        }
        System.out.println(inputMessage);
        if (inputMessage.length() > 1) {
            isValid(inputMessage);
            log.info("The input {} is valid.", inputMessage);  // error handling should be done in implementations
            System.out.println("Your input is valid!");
        } else {
            log.error("The input {} is invalid.", inputMessage);
            throw new ValidationException();
        }
    }
}
