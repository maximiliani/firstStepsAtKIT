package edu.kit.scc.dem.first_steps;

import edu.kit.scc.dem.first_steps.validators.ValidatorInterface;
import edu.kit.scc.dem.first_steps.validators.exceptions.ValidationException;
import edu.kit.scc.dem.first_steps.validators.impl.DomainValidator;
import edu.kit.scc.dem.first_steps.validators.impl.MailAddressValidator;
import edu.kit.scc.dem.first_steps.validators.impl.PhoneNumberValidator;
import edu.kit.scc.dem.first_steps.validators.impl.RegexNumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import org.apache.commons.cli.*;

/**
 * This class manages the communication between the user and the validator
 * classes.
 *
 * @author maximilianiKIT
 */
public class Main {

    static Logger log = LoggerFactory.getLogger(Main.class);
    private static ValidatorInterface validator = null;

    /**
     * This is the main method.
     * It reads the user input either form args or from console and validates them.
     *
     * @param args args to parse and validate
     * @throws ValidationException with error message inside
     */
    public static void main(String[] args) throws ValidationException {
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        Option input = new Option("i", "input", true, "String to validate");
        options.addOption(input);
        options.addOption(new Option("c", "countrycode", true, "countrycode for validating international phone numbers"));
        options.addOption(new Option("h", "help", false, "show help"));
        options.addOption(new Option("t", "type", true, "choose one of these types: domain, mail, regex, google"));
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            log.error("Failed parsing args - printing help");
            formatter.printHelp("Main.java", options);
            throw new ValidationException("Not able to parse args.", e);
        }

        if ((!cmd.hasOption("i") || !cmd.hasOption("t")) && !cmd.hasOption("h")) {
            Scanner scannerInput = new Scanner(System.in);
            System.out.println("Hello, there were no/invalid arguments given. Which validator do you want to use?");
            System.out.println("If you want to know something about the arguments use '-h'.");
            System.out.println();
            System.out.println("Please enter the type of validator you wish (regexValidator, googleValidator, mailValidator, domainValidator): ");
            String type;
            try {
                type = scannerInput.nextLine();
                scannerInput.close();
                log.debug("User input recognized: {}", type);
            } catch (Exception e) {
                System.out.println("No input given!");
                log.error("No user input given!");
                throw new ValidationException("No input given!", e);
            }
            switch (type) {
                case "regexValidator":
                    log.debug("regexValidator input recognized - starting RegexNumberValidator");
                    validator = new RegexNumberValidator();
                    break;
                case "googleValidator":
                    log.debug("googleValidator input recognized - starting PhoneNumberValidator");
                    validator = new PhoneNumberValidator();
                    break;
                case "mailValidator":
                    log.debug("mailValidator input recognized - starting MailAddressValidator");
                    validator = new MailAddressValidator();
                    break;
                case "domainValidator":
                    log.debug("domainValidator input recognized - starting DomainValidator");
                    validator = new DomainValidator();
                    break;
                default:
                    System.out.println("Invalid input! Please see this: ");
                    System.out.println();
                    formatter.printHelp("Main.java", options);
                    log.error("Invalid user input given!");
                    throw new ValidationException("Invalid input given!", new ValidationException());
            }
            try {
                validator.askForInputAndValidate();
                log.debug("Finished validation process without errors!");
                return;
            } catch (ValidationException e) {
                log.error("Failed validation because of: {}", e.getMessage());
                System.out.println(e.getMessage());
                throw e;
            }
        } else if (cmd.hasOption("h")) {
            log.debug("h arg recognized - printing help");
            formatter.printHelp("Main.java", options);
            return;
        } else if (cmd.getOptionValue("t").equals("mail")) {
            log.debug("mail arg recognized - starting MailAddressValidator");
            validator = new MailAddressValidator();
        } else if (cmd.getOptionValue("t").equals("regex")) {
            log.debug("regex arg recognized - starting RegexNumberValidator");
            validator = new RegexNumberValidator();
        } else if (cmd.getOptionValue("t").equals("domain")) {
            log.debug("domain arg recognized - starting DomainValidator");
            validator = new DomainValidator();
        } else if (cmd.getOptionValue("t").equals("google")) {
            log.debug("google arg recognized - starting PhoneNumberValidator");
            if (cmd.hasOption("c")) {
                log.debug("c arg recognized - using constructor with arg value");
                validator = new PhoneNumberValidator(cmd.getOptionValue("c"));
            } else {
                log.debug("c arg NOT recognized - using constructor without arg value");
                validator = new PhoneNumberValidator();
            }
        }

        try {
            validator.isValid(cmd.getOptionValue("i"));
            log.info("The input {} is valid", cmd.getOptionValue("i"));
            System.out.println("Valid input!");
        } catch (ValidationException e) {
            log.error("The input {} is invalid because of this reason: {}", input, e.getMessage());
            System.out.println("Invalid Input!");
            System.out.println("REASON: " + e.getMessage());
            throw e;
        }
    }
}
