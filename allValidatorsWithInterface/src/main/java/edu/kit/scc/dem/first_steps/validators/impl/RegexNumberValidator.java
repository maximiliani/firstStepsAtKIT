package edu.kit.scc.dem.first_steps.validators.impl;

import edu.kit.scc.dem.first_steps.validators.ValidatorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class validates only German telephone numbers.
 *
 * @author maximilianiKIT
 */
public class RegexNumberValidator implements ValidatorInterface {

    final Logger log = LoggerFactory.getLogger(RegexNumberValidator.class);

    /**
     * This method validates German phone numbers and throws an IllegalArgumentException if the number isn't valid.
     *
     * @param input has to be a german national number without special characters or spaces
     * @return true, if the number is valid
     */
    @Override
    public boolean isValid(String input) throws ValidationException {

        if (input == null || input.length() == 0) {
            log.error("No input!");
            throw new ValidationException("No input!", new ValidationException());
        }

    /*
      This regex pattern was built based on the information about the german phone number system provided by the Bundesnetzagentur (Federal Grid Agency).
      It seperates the area code from the participants number by the position in the number.
      Emergency numbers like 110, 112 or other special short numbers are interpreted as an area code by this regex, so there is no participants number.
      In this case it must be verified that the area code matches one of these special numbers. This is done in the if-else-construct below.
     */

        String regex = "^(010[1-9]\\d|0100\\d\\d|110|112|115|116[01]\\d\\d|118[1-9]\\d|11800[1-9]|0137[1-9]|015\\d{2,3}|016[23489]|017\\d|018|019[1-4]|02\\d{1,3}|031[01]|03[02-9]\\d{1,3}|04\\d{1,4}|050[3-9]\\d{1,2}|05[1-9]\\d{1,3}|06\\d{1,4}|070[02-9]\\d{1,2}|07[1-9]\\d{1,3}|080[02-9]\\d{1,2}|08[1-9]\\d{1,3}|0900[1359]|09[1-9]\\d{1,3})([1-9]\\d{2,7})?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            log.info("Matched phone number: " + matcher.group(0));
            log.info("Area code: " + matcher.group(1));
            log.info("Participants number: " + matcher.group(2));
            if (matcher.group(2) != null && matcher.group(0).length() > 2 && matcher.group(0).length() <= 13 && matcher.group(0).equals(input))
                return true;
            else if (matcher.group(0).equals("110") && input.equals("110")) return true;
            else if (matcher.group(0).equals("112") && input.equals("112")) return true;
            else if (matcher.group(0).equals("115") && input.equals("115")) return true;
            else if (matcher.group(0).equals("0310") && input.equals("0310")) return true;
            else if (matcher.group(0).equals("0311") && input.equals("0311")) return true;
        }
        log.error("The number {} is invalid!", input);
        throw new ValidationException("Invalid number!", new ValidationException());
    }

}
