package edu.kit.scc.dem.first_steps;

import edu.kit.scc.dem.first_steps.validators.ValidatorInterface;
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
public class Main { //Code coverage: add test for new Main() to cover this line.
    // See code conventions for static final properties. Use upper CASE!
    static final Logger log = LoggerFactory.getLogger(Main.class);
    private static ValidatorInterface validator = null;

    /**
     * This is the main method.
     * It reads the user input either form args or from console and validates them.
     *
     * @param args args to parse and validate
     * @throws ValidatorInterface.ValidationException with error message inside
     */
    public static void main(String[] args) throws ValidatorInterface.ValidationException {
      // To shorten method outsource initialization for options:  Options options = initCommandlineArgs();
      // What happens if someone call the method with the following args: -m -d -r -i someInput!?
      // Allow argument for type and and additional input (cmd.getArgList())
      // in argList is empty ask for given type.
      // I would cleanup args list: 
      // Usage: Main -t Type (one of ...) -c countrycode (only for type google otherwise an exception
      // should be thrown) INPUT 
      // If INPUT is not available ask for input.
      // This simplifies argument parsing. 
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        Option input = new Option("i", "input", true, "String to validate");
        options.addOption(input);
        options.addOption(new Option("c", "countrycode", true, "countrycode for validating international phone numbers"));
        options.addOption(new Option("m", "mail", false, "use mail address validator"));
        options.addOption(new Option("d", "domain", false, "use domain validator"));
        options.addOption(new Option("r", "regex", false, "use regex phone number validator (only for German national phone numbers)"));
        options.addOption(new Option("g", "google", false, "use google library for international phone number validator"));
        options.addOption(new Option("h", "help", false, "show help"));
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {  // Why no tests with invalid input! 
          // Testing only valid input makes no sense as the handling of invalid input
          // is much more important.
            log.error("Failed parsing args - printing help");
            formatter.printHelp("Main.java", options);
            throw new ValidatorInterface.ValidationException("Not able to parse args.", e);
        }

        if (!cmd.hasOption("i") && !cmd.hasOption("h")) {
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
                throw new ValidatorInterface.ValidationException("No input given!", e);
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
                    throw new ValidatorInterface.ValidationException("Invalid input given!", new ValidatorInterface.ValidationException());
            }
            try {
                validator.askForInputAndValidate();
                log.debug("Finished validation process without errors!");
                return;
            } catch (ValidatorInterface.ValidationException e) {
                log.error("Failed validation because of: {}", e.getMessage());
                System.out.println(e.getMessage());
                throw e;
            }
        } else if (cmd.hasOption("m")) {
            log.debug("m arg recognized - starting MailAddressValidator");
            validator = new MailAddressValidator();
        } else if (cmd.hasOption("r")) {
            log.debug("r arg recognized - starting RegexNumberValidator");
            validator = new RegexNumberValidator();
        } else if (cmd.hasOption("d")) {
            log.debug("d arg recognized - starting DomainValidator");
            validator = new DomainValidator();
        } else if (cmd.hasOption("g")) {
            log.debug("g arg recognized - starting PhoneNumberValidator");
            if (cmd.hasOption("c")) {
                log.debug("c arg recognized - using constructor with arg value");
                validator = new PhoneNumberValidator(cmd.getOptionValue("c"));
            } else {
                log.debug("c arg NOT recognized - using constructor without arg value");
                validator = new PhoneNumberValidator();
            }
        } else if (cmd.hasOption("h")) {
            log.debug("h arg recognized - printing help");
            formatter.printHelp("Main.java", options);
            return;
        }

        try {
            validator.isValid(cmd.getOptionValue("i"));
            log.info("The input {} is valid", cmd.getOptionValue("i"));
            System.out.println("Valid input!");
        } catch (ValidatorInterface.ValidationException e) {
            log.error("The input {} is invalid because of this reason: {}", input, e.getMessage());
            System.out.println("Invalid Input!");
            System.out.println("REASON: " + e.getMessage());
            throw e;
        }
    }
}
