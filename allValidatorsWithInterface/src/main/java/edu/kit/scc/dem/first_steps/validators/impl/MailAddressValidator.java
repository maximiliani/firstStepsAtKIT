package edu.kit.scc.dem.first_steps.validators.impl;

import edu.kit.scc.dem.first_steps.validators.ValidatorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class validates e-mail addresses.
 *
 * @author maximilianiKIT
 */
public class MailAddressValidator implements ValidatorInterface {

    final Logger log = LoggerFactory.getLogger(MailAddressValidator.class);
    private static final DomainValidator domainValidator = new DomainValidator();

    /**
     * This method validates e-mail addresses and throws an ValidationException if the address isn't valid.
     *
     * @param input the mail address to check
     * @return true, if the mail address is valid
     */
    @Override
    public boolean isValid(String input) throws ValidationException {

        if (input == null || input.length() == 0) {
            log.error("No input!");
            throw new ValidationException("No input", new ValidationException());
        }

        /*
         * This regex pattern was built based on the information provided on {@link https://www.mailboxvalidator.com/resources/articles/acceptable-email-address-syntax-rfc/#:~:text=The%20syntax%20for%20an%20email%20address%20is%20familiar,There%20specifications%20are%20called%20Requests%20For%20Comments%20%28RFC%29}.
         * It splits the mail address in different parts like the local part (often username or name of the person who owns the mail address), the serverdomain, the SLD and the TLD.
         * It also verifies that no unallowed characters are used.
         */
        String regex = "^([A-Za-z0-9!#$%&'*+\\-/=?^_`{|}~][A-Za-z0-9.!#$%&'*+\\-/=?^_`{|}~]{0,62}[A-Za-z0-9!#$%&'*+\\-=?^_`|~]?)@((([A-Za-z0-9]+?[A-Za-z0-9-]{0,61}[A-Za-z0-9.])+)\\.([A-Za-z]+?[A-Za-z0-9+-]+))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            log.info("Recognized e-mail address: {}", matcher.group(0));
            log.info("Local part (often username/name): {}", matcher.group(1));
            log.info("Server domain: {}", matcher.group(2));
            log.debug("Server domain without TLD: {}", matcher.group(3));
            log.debug("Second level domain (SLD): {}", matcher.group(4));
            log.debug("Top level domain (TLD): {}", matcher.group(5));
            if (input.length() == matcher.group(0).length() && matcher.group(0).length() < 255 && domainValidator.isValid(matcher.group(2)))
                return true;
        }
        log.error("The mail address {} is invalid.", input);
        throw new ValidationException("Invalid mail address", new ValidationException());
    }
}
