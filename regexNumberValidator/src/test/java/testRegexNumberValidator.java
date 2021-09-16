import org.junit.jupiter.api.Test;
import regexNumberValidator.RegexNumberValidator;

import static org.junit.jupiter.api.Assertions.*;

public class testRegexNumberValidator {

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
    public void invalidNationalCode(){ assertFalse(RegexNumberValidator.validInput("01234567890"));}

    @Test
    public void invalidShortNumber(){ assertFalse(RegexNumberValidator.validInput("111"));}

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
}
