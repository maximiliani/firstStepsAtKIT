package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.validators.exceptions.ValidationException;
import edu.kit.scc.dem.first_steps.validators.impl.DomainValidator;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assume.assumeNoException;
import static org.junit.jupiter.api.Assertions.*;

class TestValidatorInterface {

    @Test
    void askForInputAndValidate() {
        ValidatorInterface validatorInterface = new DomainValidator();
        assertThrows(ValidationException.class, validatorInterface::askForInputAndValidate);
    }

    @Test
    void validInput() {
        ValidatorInterface validatorInterface = new DomainValidator();
        InputStream stdin = System.in;
        String testString = "kit.edu" + System.getProperty("line.separator");
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            validatorInterface.askForInputAndValidate();
        } catch (ValidationException e) {
            assumeNoException(e);
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void noInput() {
        LogCaptor logCaptor = LogCaptor.forClass(ValidatorInterface.class);
        InputStream stdin = System.in;
        ValidatorInterface validatorInterface = new DomainValidator();
        String testString = "" + System.getProperty("line.separator");
        String expectedMessage = "The input  is invalid.";
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            validatorInterface.askForInputAndValidate();
        } catch (ValidationException e) {
            assertTrue(logCaptor.getErrorLogs().contains(expectedMessage));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void ValidatorExceptionWithoutMessage() {
        assertThrows(ValidationException.class, () -> {
            throw new ValidationException();
        });
    }

    @Test
    void ValidatorExceptionWithMessage() {
        assertThrows(ValidationException.class, () -> {
            throw new ValidationException("test", new Exception());
        });
    }

    @Test
    void testQueryMessage(){
        ValidatorInterface validator = new ValidatorInterface() {
            @Override
            public boolean isValid(String input) throws ValidationException {
                return false;
            }
        };
        validator.printQueryMessage();
    }
}