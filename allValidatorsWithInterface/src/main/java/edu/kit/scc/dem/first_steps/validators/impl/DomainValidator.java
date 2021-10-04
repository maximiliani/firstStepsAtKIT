package edu.kit.scc.dem.first_steps.validators.impl;

import edu.kit.scc.dem.first_steps.validators.ValidatorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

/**
 * This class validates domain names by checking if an A or AAAA record is available.
 *
 * @author maximilianiKIT
 */
public class DomainValidator implements ValidatorInterface {

    Logger log = LoggerFactory.getLogger(DomainValidator.class);

    /**
     * This method checks if A or AAAA records are available for the domain.
     *
     * @param input The domain of the server (the part after the '@')
     * @return true if there are such records
     * @throws ValidationException if there aren't such records
     */
    @Override
    public boolean isValid(String input) throws ValidationException {
        if (input == null || input.length() < 4) {
            log.error("No input!");
            throw new ValidationException("No input", new IllegalArgumentException());
        }
        try {
            InetAddress domain = InetAddress.getByName(input);
            log.info("Hostname: " + domain.getHostName());
            log.info("IP-Address: " + domain.getHostAddress());
            return true;
        } catch (Exception e) {
            log.error("No A or AAAA record available.");
            throw new ValidationException("No A or AAAA record available", e);
        }
    }
}
