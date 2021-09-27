package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.validators.impl.RegexNumberValidator;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TestRegexNumberValidator {

    private final RegexNumberValidator validator = new RegexNumberValidator();

    @Test
    public void valid(){ assertTrue(validator.isValid(new String[]{"02504130"}));}

    @Test
    public void valid110(){ assertTrue(validator.isValid(new String[]{"110"}));}

    @Test
    public void valid112(){ assertTrue(validator.isValid(new String[]{"112"}));}

    @Test
    public void valid115(){ assertTrue(validator.isValid(new String[]{"115"}));}

    @Test
    public void valid031(){ assertTrue(validator.isValid(new String[]{"0310"}));}

    @Test
    public void validMobile(){ assertTrue(validator.isValid(new String[]{"015253909714"}));}

    @Test
    public void tooLong(){ assertThrows(IllegalArgumentException.class, () -> { validator.isValid(new String[]{"072160889101214"});});}

    @Test
    public void invalid(){ assertThrows(IllegalArgumentException.class, () -> { validator.isValid(new String[]{""});});}
    @Test
    public void invalid2(){ assertThrows(IllegalArgumentException.class, () -> { validator.isValid(new String[]{});});}

    @Test
    public void invalidNationalCode(){ assertThrows(IllegalArgumentException.class, () -> { validator.isValid(new String[]{"01234567890"});});}

    @Test
    public void invalidShortNumber(){ assertThrows(IllegalArgumentException.class, () -> { validator.isValid(new String[]{"111"});});}

    @Test
    public void invalidShortNumber2(){ assertThrows(IllegalArgumentException.class, () -> { validator.isValid(new String[]{"1101"});});}

    @Test
    public void tooShort(){ assertThrows(IllegalArgumentException.class, () -> { validator.isValid(new String[]{"0721608"});});}

    @Test
    public void notANumber(){ assertThrows(IllegalArgumentException.class, () -> { validator.isValid(new String[]{"hello"});});}

    @Test void testMain(){
        String[] args = {"112"};
        try{
            validator.askForInputAndValidate(args);
        } catch (Exception e) {
            fail(e);
        }
    }
    @Test void testInvalidMain(){
        String[] args = {"111"};
        assertThrows(IllegalArgumentException.class, () -> { validator.askForInputAndValidate(args);});
    }
    @Test void testInvalidMain2(){
        String[] args = null;
        assertThrows(IllegalArgumentException.class, () -> { validator.askForInputAndValidate(args);});
    }
    @Test void testInvalidMain3(){
        String[] args = {"110", "11", "000345678", "+49 150 123 456"};
        assertThrows(IllegalArgumentException.class, () -> { validator.askForInputAndValidate(args);});
    }
}
