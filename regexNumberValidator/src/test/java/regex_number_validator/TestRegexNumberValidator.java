import org.junit.jupiter.api.Test;
import regex_number_validator.RegexNumberValidator;

import static org.junit.jupiter.api.Assertions.*;

public class TestRegexNumberValidator {

    @Test
    public void valid(){ assertTrue(RegexNumberValidator.validInput("02504130"));}

    @Test
    public void valid110(){ assertTrue(RegexNumberValidator.validInput("110"));}

    @Test
    public void valid112(){ assertTrue(RegexNumberValidator.validInput("112"));}

    @Test
    public void valid115(){ assertTrue(RegexNumberValidator.validInput("115"));}

    @Test
    public void valid031(){ assertTrue(RegexNumberValidator.validInput("0310"));}

    @Test
    public void validMobile(){ assertTrue(RegexNumberValidator.validInput("015253909714"));}

    @Test
    public void tooLong(){ assertFalse(RegexNumberValidator.validInput("072160889101214"));}

    @Test
    public void invalid(){ assertFalse(RegexNumberValidator.validInput(""));}
    @Test
    public void invalid2(){ assertFalse(RegexNumberValidator.validInput(null));}

    @Test
    public void invalidNationalCode(){ assertFalse(RegexNumberValidator.validInput("01234567890"));}

    @Test
    public void invalidShortNumber(){ assertFalse(RegexNumberValidator.validInput("111"));}

    @Test
    public void invalidShortNumber2(){ assertFalse(RegexNumberValidator.validInput("1101"));}

    @Test
    public void tooShort(){ assertFalse(RegexNumberValidator.validInput("0721608"));}

    @Test
    public void notANumber(){ assertFalse(RegexNumberValidator.validInput("hello"));}

    @Test void testMain(){
        String[] args = {"112"};
        try{
            RegexNumberValidator.main(args);
        } catch (Exception e) {
            fail(e);
        }
    }
    @Test void testInvalidMain(){
        String[] args = {"111"};
        try{
            RegexNumberValidator.main(args);
        } catch (Exception e) {
            fail(e);
        }
    }
    @Test void testInvalidMain2(){
        String[] args = null;
        try{
            RegexNumberValidator.main(args);
        } catch (Exception e) {
            fail(e);
        }
    }
    @Test void testInvalidMain3(){
        String[] args = {"110", "11", "000345678", "+49 150 123 456"};
        try{
            RegexNumberValidator.main(args);
        } catch (Exception e) {
            fail(e);
        }
    }
}
