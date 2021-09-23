package all_validators_with_interface;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

/**
 * This class validates international phone numbers by using a Google library.
 * @author maximilianiKIT
 */
public class PhoneNumberValidator implements ValidatorInterface{

    private static final PhoneNumberUtil util = PhoneNumberUtil.getInstance();

    /**
     * This method does some simple processing...
     * @param input [0] - number to validate in an international format
     *              [1] - country code from where to validate the number
     */
    @Override
    public void processIsValid(String[] input) throws IllegalArgumentException {
        System.out.println("This program validates international phone numbers.");
        System.out.println("Please use only an international notation and '+' or '00' as traffic elimination digit.");
        System.out.println();

        if ( input == null || input.length!=2 || input[0] == null) {
            System.out.println("ERROR: Invalid input! ");
            System.out.println();
            throw new IllegalArgumentException();
        }
        else{
            try{
                isValidInput(input);
                System.out.println("The number is valid!");
            } catch (Exception e) {
                System.out.println("ERROR:   The number is invalid!");
                System.out.println();
                e.printStackTrace();
                throw e;
            }
        }
    }

    /**
     * This method validates international phone numbers by using a Google library.
     * @param input [0] - number to validate in an international format
     *              [1] - country code from where to validate the number
     * @return  true if the number is valid and possible
     * @throws IllegalArgumentException if the number is not possible or/and not valid
     */
    @Override
    public boolean isValidInput(String[] input) throws IllegalArgumentException {
        if ( input == null || input.length!=2 || input[0] == null || input[1] == null) {
            System.out.println("ERROR: Invalid input! ");
            System.out.println();
            throw new IllegalArgumentException();
        }
        try {
            Phonenumber.PhoneNumber number = util.parseAndKeepRawInput(input[0], input[1]);
            PhoneNumberUtil.ValidationResult possibleResult = util.isPossibleNumberWithReason(number);
            switch (possibleResult) {
                case IS_POSSIBLE:
                    System.out.println("This number is possible.");
                    break;
                case IS_POSSIBLE_LOCAL_ONLY:
                    System.out.println("This number is only possible within a certain region and does not meet all the criteria of an international number.");
                    break;
                default:
                    System.out.println(possibleResult);
                    break;
            }
            return util.isValidNumber(number);
        } catch (NumberParseException e) {
            System.out.print("ERROR:    ");
            switch (e.getErrorType()) {
                case INVALID_COUNTRY_CODE:
                    System.out.println("Invalid country code");
                    break;
                case NOT_A_NUMBER:
                    System.out.println("The input does not meet the minimum requirements of a phone number!");
                    break;
                case TOO_SHORT_NSN:
                case TOO_SHORT_AFTER_IDD:
                    System.out.println("This number is too short!");
                    break;
                case TOO_LONG:
                    System.out.println("This number is too long!");
                    break;
            }
        }
        throw new IllegalArgumentException();
    }
}
