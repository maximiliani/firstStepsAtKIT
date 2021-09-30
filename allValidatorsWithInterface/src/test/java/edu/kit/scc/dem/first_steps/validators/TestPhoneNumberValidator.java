package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.validators.impl.DomainValidator;
import edu.kit.scc.dem.first_steps.validators.impl.PhoneNumberValidator;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPhoneNumberValidator {

    private final PhoneNumberValidator validator = new PhoneNumberValidator("DE");
    private static final PhoneNumberUtil util = PhoneNumberUtil.getInstance();

    @Test
    void noCountryCodeInput() {
        LogCaptor logCaptor = LogCaptor.forClass(PhoneNumberValidator.class);
        InputStream stdin = System.in;
        String testString = System.getProperty("line.separator");
        String expectedMessage = "No country code provided.";
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            PhoneNumberValidator validator = new PhoneNumberValidator();
        } catch (ValidatorInterface.ValidationException e) {
            assertTrue(logCaptor.getErrorLogs().contains(expectedMessage));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void validCountryCodeInput() {
        LogCaptor logCaptor = LogCaptor.forClass(PhoneNumberValidator.class);
        InputStream stdin = System.in;
        String testString = "DE" + System.getProperty("line.separator");
        String expectedMessage = "Set country code to DE";
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            PhoneNumberValidator validator = new PhoneNumberValidator();
        } catch (ValidatorInterface.ValidationException e) {
            assertTrue(logCaptor.getDebugLogs().contains(expectedMessage));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    public void farTooShort() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> validator.isValid("1"));
    }

    @Test
    public void possibleButNotValid() {
        try {
            Phonenumber.PhoneNumber number = util.parseAndKeepRawInput("+497216", "DE");
            Assertions.assertNotEquals(validator.isValid("+497216"), util.isPossibleNumber(number));
        } catch (NumberParseException | ValidatorInterface.ValidationException e) {
            assumeNoException(e);
        }
    }

    @Test
    public void valid() {
        try {
            Assertions.assertTrue(validator.isValid("+497216081234"));
        } catch (ValidatorInterface.ValidationException e) {
            assumeNoException(e);
        }
    }

    @Test
    public void validWith00() {
        try {
            Assertions.assertTrue(validator.isValid("00497216081234"));
        } catch (ValidatorInterface.ValidationException e) {
            assumeNoException(e);
        }
    }

    @Test
    public void tooLong() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> validator.isValid("+49721608333333333333333"));
    }

    @Test
    public void invalid() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> validator.isValid("+03245222342"));
    }

    @Test
    public void invalidCountryCode() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> validator.isValid("+9991234123"));
    }

    @Test
    public void tooShort() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> validator.isValid("+491"));
    }

    @Test
    public void notANumber() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> validator.isValid("hello"));
    }

    @Test
    public void noInputInVIsValid() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> validator.isValid(null));
    }

    @Test
    public void isPossibleLocalOnly() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> validator.isValid("123"));
    }
}
