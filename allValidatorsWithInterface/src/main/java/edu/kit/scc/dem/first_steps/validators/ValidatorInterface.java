package edu.kit.scc.dem.first_steps.validators;

import java.util.Scanner;

/**
 * This interface provides two methods which are necessary for validating things.
 *
 * @author maximilianiKIT
 */
public interface ValidatorInterface {

    boolean isValid(String input) throws ValidationException;

    default void askForInputAndValidate() throws ValidationException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter the text you want to validate: ");
        String inputMessage;
        try {
            inputMessage = userInput.nextLine();
        } catch (Exception e) {
            throw new ValidationException("Invalid inputMessage", e);
        }
        System.out.println(inputMessage);
        if (inputMessage.length() > 1) {
            isValid(inputMessage);
            System.out.println("Your input is valid!");
        } else throw new ValidationException();
    }

    class ValidationException extends Exception {
        public ValidationException(String errorMessage, Throwable err) {
            super(errorMessage, err);
        }

        public ValidationException() {
            super();
        }
    }
}
