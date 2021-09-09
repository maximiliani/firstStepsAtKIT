package testPhoneNumberValidator;

import phoneNumberValidator.PhoneNumberValidator;
import org.junit.Test;
import static org.junit.Assert.*;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.NumberParseException;

public class testPhoneNumberValidator {

    private PhoneNumberValidator validator = new PhoneNumberValidator();
    private static final PhoneNumberUtil util = PhoneNumberUtil.getInstance();

    @Test
    public void farTooShort(){
        assertFalse(validator.validInput("1","DE"));
    }

    @Test
    public void possibleButNotValid(){
        try{
            Phonenumber.PhoneNumber number = util.parseAndKeepRawInput("+497216", "DE");
            assertNotEquals(validator.validInput("+497216","DE"), util.isPossibleNumber(number));
        } catch (NumberParseException e) {
            fail(e.toString());
        }
    }

    @Test
    public void valid(){
        assertTrue(validator.validInput("+497216083333","DE"));
    }

    @Test
    public void validWith00(){
        assertTrue(validator.validInput("00497216083333","DE"));
    }

    @Test
    public void tooLong(){
        assertFalse(validator.validInput("+49721608333333333333333","DE"));
    }

    @Test
    public void invalid(){
        assertFalse(validator.validInput("++3245222342","DE"));
    }

    @Test
    public void invalidCountryCode(){
        assertFalse(validator.validInput("+9991234123","DE"));
    }

    @Test
    public void tooShort(){
        assertFalse(validator.validInput("+491","DE"));
    }

    @Test
    public void notANumber(){
        assertFalse(validator.validInput("hello","DE"));
    }
}
