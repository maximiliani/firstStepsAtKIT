package edu.kit.scc.dem.first_steps.validators.impl;

import edu.kit.scc.dem.first_steps.validators.ValidatorInterface;

import java.net.InetAddress;

/**
 * This class validates domain names by checking if an A or AAAA record is available.
 *
 * @author maximilianiKIT
 */
public class DomainValidator implements ValidatorInterface {
    /**
     * This method checks if A or AAAA records are available for the domain.
     *
     * @param input The domain of the server (the part after the '@')
     * @return true if there are such records
     * @throws ValidationException if there aren't such records
     */
    @Override
    public boolean isValid(String input) throws ValidationException {
        if (input == null || input.length() == 0) {
            System.out.println("ERROR: Invalid input! ");
            System.out.println();
            throw new ValidationException("Invalid input", new IllegalArgumentException());
        }
        try {
            InetAddress domain = InetAddress.getByName(input);
            System.out.println("Hostname: " + domain.getHostName());
            System.out.println("IP-Address: " + domain.getHostAddress());
            return true;
        } catch (Exception e) {
            throw new ValidationException("No A or AAAA record available", e);
        }
    }
}
