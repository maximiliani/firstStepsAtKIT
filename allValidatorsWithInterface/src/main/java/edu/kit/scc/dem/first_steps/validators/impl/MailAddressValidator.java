package edu.kit.scc.dem.first_steps.validators.impl;

import edu.kit.scc.dem.first_steps.validators.ValidatorInterface;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class validates e-mail addresses.
 * @author maximilianiKIT
 */
public class MailAddressValidator implements ValidatorInterface{

    /**
     * This method calls the validInput method and prints information to the console.
     *
     * @param input e-mail address to validate
     */
    @Override
    public void processIsValid(String[] input) throws ValidatorException {
      // CAUTION: IllegalArgumentException is a runtime Exception which will
      // not necessarily catched by the calling class.
        System.out.println("This program validates only e-mail addresses without comments and static IP-addresses.");
        System.out.println();

        if ( input == null || input.length!=1 || input[0] == null) {
          // Don't handle errors here. If the library is used inside a server this is 
          // not useful! ;-)
          // Errorhandling should be done outside. Throw an exception with an explaining message.
//            System.out.println("ERROR: Invalid input! ");
//            System.out.println();
            String message = String.format("'%s' is not a valid input!", input);
            throw new ValidatorException(message);
        }
        else{
            try{
                isValidInput(input);
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
     * @param input the mail address to check
     * @return true, if the mail address is valid
     */
    @Override
    public boolean isValidInput(String[] input) throws IllegalArgumentException, UnknownHostException {

        if ( input == null || input.length!=1 || input[0] == null) {
            System.out.println("ERROR: Invalid input! ");
            System.out.println();
            throw new IllegalArgumentException();
        }

        /**
         * This regex pattern was built based on the information provided on {@link https://www.mailboxvalidator.com/resources/articles/acceptable-email-address-syntax-rfc/#:~:text=The%20syntax%20for%20an%20email%20address%20is%20familiar,There%20specifications%20are%20called%20Requests%20For%20Comments%20%28RFC%29}.
         * It splits the mail address in different parts like the local part (often username or name of the person who owns the mail address), the serverdomain, the SLD and the TLD.
         * It also verifies that no unallowed characters are used.
         */
        String regex = "^([A-Za-z0-9!#$%&'*+\\-/=?^_`{|}~][A-Za-z0-9.!#$%&'*+\\-/=?^_`{|}~]{0,62}[A-Za-z0-9!#$%&'*+\\-=?^_`|~]?)@((([A-Za-z0-9]+?[A-Za-z0-9-]{0,61}[A-Za-z0-9.])+)\\.([A-Za-z]+?[A-Za-z0-9+-]+))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input[0]);

        if (matcher.find()) {
            System.out.println("Recognized e-mail address: " + matcher.group(0));
            System.out.println("Local part (often username/name): " + matcher.group(1));
            System.out.println("Server domain: " + matcher.group(2));
            System.out.println("Server domain without TLD: " + matcher.group(3));
            System.out.println("Second level domain (SLD): " + matcher.group(4));
            System.out.println("Top level domain (TLD): " + matcher.group(5));
            if (input[0].length() == matcher.group(0).length() && matcher.group(0).length()<255 && isValidDomain(matcher.group(2))) return true;
        }
        throw new IllegalArgumentException();
    }

    /**
     * This method checks if A or AAAA records are available for the domain.
     * @param inputDomain The domain of the server (the part after the '@')
     * @return true if there are such records
     * @throws UnknownHostException if there aren't such records
     */
    public static boolean isValidDomain(String inputDomain) throws UnknownHostException{
        try{
            InetAddress domain = InetAddress.getByName(inputDomain);
            System.out.println("Hostname: " + domain.getHostName());
            System.out.println("IP-Address: " + domain.getHostAddress());
            return true;
        } catch (UnknownHostException e){
            System.out.println("ERROR:     No A or AAAA record available.");
            System.out.println();
            e.printStackTrace();
            throw new UnknownHostException();
        }
    }
}
