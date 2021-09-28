package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.validators.impl.MailAddressValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMailAddressValidator {
    MailAddressValidator mailAddressValidator = new MailAddressValidator();

    @Test
    public void valid() {
        try {
            assertTrue(mailAddressValidator.isValid("maximilian.inckmann@kit.edu"));
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void validWithSubdomain() {
        try {
            assertTrue(mailAddressValidator.isValid("test@scc.kit.edu"));
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void tooLong() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> mailAddressValidator.isValid("test.test.test.test@fhgakhjsdfgkajhdgfkajhgdfkjahgdfkjahgdfkjahgdfkjahgdfkjahdsgfkjahdgfkjahgdsfkjhagsdfkjahsgdfjhagdsfjhgasdjhkfgasdhjgfajsdgfjasgdfjasgdfjagsdfjhgadsfjhgadsjfkhgash.example.jalsjkfhaldjkhfalkjdhflakjdshflkjahdflkjhadlkjfhaljdshflajshdflkahdsflkjhdaljfhasljdkfhlaksjdhfkjalsdhfljkh.de"));
    }

    @Test
    public void invalid() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> mailAddressValidator.isValid(""));
    }

    @Test
    public void invalid2() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> mailAddressValidator.isValid(null));
    }

    @Test
    public void invalidDot() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> mailAddressValidator.isValid(".maximilian@test.example"));
    }

    @Test
    public void invalidNumbericTLD() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> mailAddressValidator.isValid("test@test.123"));
    }

    @Test
    public void invalidHyphens() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> mailAddressValidator.isValid("test@test.-test-"));
    }

    @Test
    public void tooShort() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> mailAddressValidator.isValid("t@es.t"));
    }

    @Test
    public void notAnAddress() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> mailAddressValidator.isValid("hello"));
    }
}
