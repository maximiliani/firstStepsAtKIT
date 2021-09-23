package all_validators_with_interface;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

public class TestPhoneNumberValidator {

    private final PhoneNumberValidator validator = new PhoneNumberValidator();
    private static final PhoneNumberUtil util = PhoneNumberUtil.getInstance();

    @Test
    public void validMain(){
        String[] args = {"+4972160828033","DE"};
        try{
            validator.processIsValid(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void invalidMain() {
        String[] args = {"123","DE"};
        try{
            validator.processIsValid(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void farTooShort(){
        assertThrows(IllegalArgumentException.class, () -> {validator.isValidInput(new String[]{"1","DE"});});
    }

    @Test
    public void possibleButNotValid(){
        try{
            Phonenumber.PhoneNumber number = util.parseAndKeepRawInput("+497216", "DE");
            assertNotEquals(validator.isValidInput(new String[]{"+497216","DE"}), util.isPossibleNumber(number));
        } catch (NumberParseException e) {
            assumeNoException(e);
        }
    }

    @Test
    public void valid(){
        assertTrue(validator.isValidInput(new String[]{"+497216081234","DE"}));
    }

    @Test
    public void validWith00(){
        assertTrue(validator.isValidInput(new String[]{"00497216081234","DE"}));
    }

    @Test
    public void tooLong(){
        assertThrows(IllegalArgumentException.class, () -> {validator.isValidInput(new String[]{"+49721608333333333333333","DE"});});
    }

    @Test
    public void invalid(){
        assertThrows(IllegalArgumentException.class, () -> {validator.isValidInput(new String[]{"+03245222342","DE"});});
    }

    @Test
    public void invalidCountryCode(){
        assertThrows(IllegalArgumentException.class, () -> {validator.isValidInput(new String[]{"+9991234123","DE"});});
    }

    @Test
    public void tooShort(){
        assertThrows(IllegalArgumentException.class, () -> {validator.isValidInput(new String[]{"+491","DE"});});
    }

    @Test
    public void notANumber(){
        assertThrows(IllegalArgumentException.class, () -> {validator.isValidInput(new String[]{"hello","DE"});});
    }

    @Test
    public void noInputInProcess(){
        assertThrows(IllegalArgumentException.class, () -> {validator.processIsValid(new String[]{});});
    }

    @Test
    public void noInputInValidate(){
        assertThrows(IllegalArgumentException.class, () -> {validator.isValidInput(new String[]{});});
    }
}
