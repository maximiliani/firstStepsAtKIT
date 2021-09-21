package mail_address_validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class validates e-mail addresses.
 *
 */
public class MailAddressValidator {

    /**
     * This main method calls the validInput method and prints information to the console.
     *
     * @param args e-mail address to validate
     */
    public static void main(String[] args) throws IllegalArgumentException {
        System.out.println("This program validates only e-mail addresses without comments and static IP-addresses.");
        System.out.println();

        if ( args == null || args.length!=1 || args[0] == null) {
            System.out.println("ERROR: Invalid input! ");
            System.out.println();
            throw new IllegalArgumentException();
        }
        else{
            try{
                String number = args[0];
                validInput(number);
                System.out.println("This is a valid e-mail address!");
            } catch (Exception e) {
                System.out.println("ERROR:   This e-mail address is invalid!");
                System.out.println();
                e.printStackTrace();
                throw e;
            }
        }
    }

    /**
     * This method validates e-mail addresses and throws an IllegalArgumentException if the address isn't valid.
     *
     * @param inputAddress has to be a german national number without special characters or spaces
     * @return true, if the number is valid
     */
    public static boolean validInput(String inputAddress) throws IllegalArgumentException {

        if(inputAddress== null) throw new IllegalArgumentException();
        /**
         * This regex pattern was built based on the information about the german phone number system provided by the Bundesnetzagentur (Federal Grid Agency).
         * It seperates the area code from the participants number by the position in the number.
         * Emergency numbers like 110, 112 or other special short numbers are interpreted as an area code by this regex, so there is no participants number.
         * In this case it must be verified that the area code matches one of these special numbers. This is done in the if-else-construct below.
         */

        String regex = "^([A-Za-z0-9!#$%&'*+\\-/=?^_`{|}~][A-Za-z0-9.!#$%&'*+\\-/=?^_`{|}~]{0,62}[A-Za-z0-9!#$%&'*+\\-=?^_`|~]?)@((([A-Za-z0-9]+?[A-Za-z0-9-]{0,61}[A-Za-z0-9.])+)\\.([A-Za-z]+?[A-Za-z0-9+-]+))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputAddress);

        if (matcher.find()) {
            System.out.println("Recognized e-mail address: " + matcher.group(0));
            System.out.println("Local part (often username/name): " + matcher.group(1));
            System.out.println("Server domain: " + matcher.group(2));
            System.out.println("Server domain without TLD: " + matcher.group(3));
            System.out.println("Second level domain (SLD): " + matcher.group(4));
            System.out.println("Top level domain (TLD): " + matcher.group(5));
            if (inputAddress.length() == matcher.group(0).length() && matcher.group(0).length()<255) return true;
        }
        throw new IllegalArgumentException();
    }
}
