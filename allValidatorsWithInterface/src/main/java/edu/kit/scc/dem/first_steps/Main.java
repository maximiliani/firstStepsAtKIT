package edu.kit.scc.dem.first_steps;

import edu.kit.scc.dem.first_steps.validators.ValidatorInterface;
import edu.kit.scc.dem.first_steps.validators.impl.DomainValidator;
import edu.kit.scc.dem.first_steps.validators.impl.MailAddressValidator;
import edu.kit.scc.dem.first_steps.validators.impl.PhoneNumberValidator;
import edu.kit.scc.dem.first_steps.validators.impl.RegexNumberValidator;

import java.util.Scanner;

import org.apache.commons.cli.*;

/**
 * This class manages the communication between the user and the validator
 * classes.
 *
 * @author maximilianiKIT
 */
public class Main {

    private static ValidatorInterface validator = null;

    public static void main(String[] args) throws ValidatorInterface.ValidationException {
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
        } catch (ParseException e) {
            formatter.printHelp("Main.java", options);
            throw new ValidatorInterface.ValidationException("Not able to parse args.", e);
        }

        if (cmd == null || (!cmd.hasOption("i") && !cmd.hasOption("h"))) {
            Scanner scannerInput = new Scanner(System.in);
            System.out.println("Hello, there were no/invalid arguments given. Which validator do you want to use?");
            System.out.println("If you want to know something about the arguments use '-h'.");
            System.out.println();
            System.out.println("Please enter the type of validator you wish (regexValidator, googleValidator, mailValidator, domainValidator): ");
            String type;
            try {
                type = scannerInput.nextLine();
            } catch (Exception e) {
                System.out.println("No input given!");
                throw new ValidatorInterface.ValidationException("No input given!", e);
            }
            switch (type) {
                case "regexValidator":
                    validator = new RegexNumberValidator();
                    break;
                case "googleValidator":
                    validator = new PhoneNumberValidator();
                    break;
                case "mailValidator":
                    validator = new MailAddressValidator();
                    break;
                case "domainValidator":
                    validator = new DomainValidator();
                    break;
                default:
                    System.out.println("Invalid input! Please see this: ");
                    System.out.println();
                    formatter.printHelp("Main.java", options);
                    throw new ValidatorInterface.ValidationException("Invalid input given!", new ValidatorInterface.ValidationException());
            }
            try {
                validator.askForInputAndValidate();
                return ;
            } catch (ValidatorInterface.ValidationException e) {
                System.out.println(e.getMessage());
                throw e;
            }
        } else if (cmd.hasOption("m")) {
            validator = new MailAddressValidator();
        } else if (cmd.hasOption("r")) {
            validator = new RegexNumberValidator();
        } else if (cmd.hasOption("d")) {
            validator = new DomainValidator();
        } else if (cmd.hasOption("g")) {
            if (cmd.hasOption("c")) {
                validator = new PhoneNumberValidator(cmd.getOptionValue("c"));
            } else {
                validator = new PhoneNumberValidator();
            }
        } else if (cmd.hasOption("h")) {
            formatter.printHelp("Main.java", options);
            return;
        }

        try {
            validator.isValid(cmd.getOptionValue("i"));
            System.out.println("Valid input!");
        } catch (ValidatorInterface.ValidationException e) {
            System.out.println("Invalid Input!");
            System.out.println("REASON: " + e.getMessage());
            throw e;
        }
    }
}
