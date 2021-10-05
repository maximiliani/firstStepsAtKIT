package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.validators.exceptions.ValidationException;
import edu.kit.scc.dem.first_steps.validators.impl.DomainValidator;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

import static org.junit.Assume.assumeNoException;
import static org.junit.jupiter.api.Assertions.*;

public class TestDomainValidator {

    private final DomainValidator validator = new DomainValidator();

    @Test
    public void noRecord() {
        assertThrows(ValidationException.class, () -> validator.isValid("test.test.test.kit.edu"));
    }

    @Test
    public void noInput() {
        assertThrows(ValidationException.class, () -> validator.isValid(""));
    }

    @Test
    public void nullInput() {
        assertThrows(ValidationException.class, () -> validator.isValid(null));
    }

    @Test
    public void shortInput() {
        assertThrows(ValidationException.class, () -> validator.isValid("h.d"));
    }

    @Test
    public void recordAvailable() {
        try {
            assertTrue(validator.isValid("kit.edu"));
        } catch (Exception e) {
            assumeNoException(e);
        }
    }
}
