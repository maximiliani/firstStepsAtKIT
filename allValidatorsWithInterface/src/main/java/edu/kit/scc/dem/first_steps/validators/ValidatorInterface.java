package edu.kit.scc.dem.first_steps.validators;

import java.util.Scanner;

/**
 * This interface provides two methods which are necessary for validating things.
 *
 * @author maximilianiKIT
 */
public interface ValidatorInterface {

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
     * This method asks the user via command line for input and validates it with the isValid() method.
     *
     * @throws ValidationException with a description if an error occurs or the input is invalid.
     */
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

    /**
     * This class is a custom exception.
     */
    class ValidationException extends Exception {

        /**
         * This constructor expects an errorMessage and an Throwable for more information.
         *
         * @param errorMessage is a description about what happened.
         * @param err          is a Throwable from former errors like IllegalArgumentExceptions, which are not handled by the caller class.
         */
        public ValidationException(String errorMessage, Throwable err) {
            super(errorMessage, err);
        }

        /**
         * This is a empty constructor with no additional information.
         */
        public ValidationException() {
            super();
        }
    }
}
