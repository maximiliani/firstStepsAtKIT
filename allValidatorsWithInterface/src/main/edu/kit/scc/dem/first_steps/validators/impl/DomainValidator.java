package edu.kit.scc.dem.first_steps.validators.impl;

import edu.kit.scc.dem.first_steps.validators.ValidatorInterface;
import java.net.InetAddress;

public class DomainValidator implements ValidatorInterface {
    /**
      * This method checks if A or AAAA records are available for the domain.
      * @param input The domain of the server (the part after the '@')
      * @return true if there are such records
      * @throws ValidationException if there aren't such records
      */
    @Override
    public boolean isValid(String[] input) throws ValidationException {
        try{
            InetAddress domain = InetAddress.getByName(input[0]);
            System.out.println("Hostname: " + domain.getHostName());
            System.out.println("IP-Address: " + domain.getHostAddress());
            return true;
        } catch (Exception e){
//            System.out.println("ERROR:     No A or AAAA record available.");
//            System.out.println();
//            e.printStackTrace();
            throw new ValidationException("No A or AAAA record available", e);
        }
    }

    @Override
    public String[] getInputMessage() throws IllegalArgumentException {
        return new String[0];
    }

    @Override
    public void askForInputAndValidate(String[] input) throws ValidationException {

    }
}
