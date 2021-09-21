package regex_number_validator;

import org.junit.jupiter.api.Test;

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
    public void tooLong(){ assertThrows(IllegalArgumentException.class, () -> { RegexNumberValidator.validInput("072160889101214");});}

    @Test
    public void invalid(){ assertThrows(IllegalArgumentException.class, () -> { RegexNumberValidator.validInput("");});}
    @Test
    public void invalid2(){ assertThrows(IllegalArgumentException.class, () -> { RegexNumberValidator.validInput(null);});}

    @Test
    public void invalidNationalCode(){ assertThrows(IllegalArgumentException.class, () -> { RegexNumberValidator.validInput("01234567890");});}

    @Test
    public void invalidShortNumber(){ assertThrows(IllegalArgumentException.class, () -> { RegexNumberValidator.validInput("111");});}

    @Test
    public void invalidShortNumber2(){ assertThrows(IllegalArgumentException.class, () -> { RegexNumberValidator.validInput("1101");});}

    @Test
    public void tooShort(){ assertThrows(IllegalArgumentException.class, () -> { RegexNumberValidator.validInput("0721608");});}

    @Test
    public void notANumber(){ assertThrows(IllegalArgumentException.class, () -> { RegexNumberValidator.validInput("hello");});}

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
        assertThrows(IllegalArgumentException.class, () -> { RegexNumberValidator.main(args);});
    }
    @Test void testInvalidMain2(){
        String[] args = null;
        assertThrows(IllegalArgumentException.class, () -> { RegexNumberValidator.main(args);});
    }
    @Test void testInvalidMain3(){
        String[] args = {"110", "11", "000345678", "+49 150 123 456"};
        assertThrows(IllegalArgumentException.class, () -> { RegexNumberValidator.main(args);});
    }
}
