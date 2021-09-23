package testPhoneNumberValidator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phoneNumberValidator.PhoneNumberValidator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

public class testPhoneNumberValidator {

    private final PhoneNumberValidator validator = new PhoneNumberValidator();
    private static final PhoneNumberUtil util = PhoneNumberUtil.getInstance();private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void validMain(){
        String[] args = {"DE","+49721608"};
        try{
            PhoneNumberValidator.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void invalidMain() {
        String[] args = {"DE","+497216"};
        try{
            PhoneNumberValidator.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void farTooShort(){
        assertFalse(PhoneNumberValidator.validInput("1","DE"));
    }

    @Test
    public void possibleButNotValid(){
        try{
            Phonenumber.PhoneNumber number = util.parseAndKeepRawInput("+497216", "DE");
            assertNotEquals(PhoneNumberValidator.validInput("+497216","DE"), util.isPossibleNumber(number));
        } catch (NumberParseException e) {
            fail(e.toString());
        }
    }

    @Test
    public void valid(){
        assertTrue(PhoneNumberValidator.validInput("+497216081234","DE"));
    }

    @Test
    public void validWith00(){
        assertTrue(PhoneNumberValidator.validInput("00497216081234","DE"));
    }

    @Test
    public void tooLong(){
        assertFalse(PhoneNumberValidator.validInput("+49721608333333333333333","DE"));
    }

    @Test
    public void invalid(){
        assertFalse(PhoneNumberValidator.validInput("++3245222342","DE"));
    }

    @Test
    public void invalidCountryCode(){
        assertFalse(PhoneNumberValidator.validInput("+9991234123","DE"));
    }

    @Test
    public void tooShort(){
        assertFalse(PhoneNumberValidator.validInput("+491","DE"));
    }

    @Test
    public void notANumber(){
        assertFalse(PhoneNumberValidator.validInput("hello","DE"));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
