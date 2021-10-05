package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.validators.exceptions.ValidationException;
import edu.kit.scc.dem.first_steps.validators.impl.RegexNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TestRegexNumberValidator {

    private final RegexNumberValidator validator = new RegexNumberValidator();

    @Test
    public void valid() {
        try {
            Assertions.assertTrue(validator.isValid("02504130"));
        } catch (ValidationException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    @Test
    public void valid110() {
        try {
            Assertions.assertTrue(validator.isValid("110"));
        } catch (ValidationException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    @Test
    public void invalid110() {
        assertThrows(ValidationException.class, () -> validator.isValid("1101"));
    }

    @Test
    public void valid112() {
        try {
            Assertions.assertTrue(validator.isValid("112"));
        } catch (ValidationException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    @Test
    public void invalid112() {
        assertThrows(ValidationException.class, () -> validator.isValid("1121"));
    }

    @Test
    public void valid115() {
        try {
            Assertions.assertTrue(validator.isValid("115"));
        } catch (ValidationException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    @Test
    public void invalid115() {
        assertThrows(ValidationException.class, () -> validator.isValid("1155"));
    }

    @Test
    public void valid0310() {
        try {
            Assertions.assertTrue(validator.isValid("0310"));
        } catch (ValidationException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    @Test
    public void invalid0310() {
        assertThrows(ValidationException.class, () -> validator.isValid("03101"));
    }

    @Test
    public void valid0311() {
        try {
            Assertions.assertTrue(validator.isValid("0311"));
        } catch (ValidationException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    @Test
    public void invalid0311() {
        assertThrows(ValidationException.class, () -> validator.isValid("03111"));
    }

    @Test
    public void validMobile() {
        try {
            Assertions.assertTrue(validator.isValid("015253909714"));
        } catch (ValidationException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    @Test
    public void tooLong() {
        assertThrows(ValidationException.class, () -> validator.isValid("072160889101214"));
    }

    @Test
    public void invalid() {
        assertThrows(ValidationException.class, () -> validator.isValid(""));
    }

    @Test
    public void invalid2() {
        assertThrows(ValidationException.class, () -> validator.isValid(null));
    }

    @Test
    public void invalidNationalCode() {
        assertThrows(ValidationException.class, () -> validator.isValid("01234567890"));
    }

    @Test
    public void invalidShortNumber() {
        assertThrows(ValidationException.class, () -> validator.isValid("111"));
    }

    @Test
    public void invalidShortNumber2() {
        assertThrows(ValidationException.class, () -> validator.isValid("1101"));
    }

    @Test
    public void tooShort() {
        assertThrows(ValidationException.class, () -> validator.isValid("0721608"));
    }

    @Test
    public void notANumber() {
        assertThrows(ValidationException.class, () -> validator.isValid("hello"));
    }
}
