package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.validators.impl.MailAddressValidator;
import org.junit.jupiter.api.Test;
import java.net.UnknownHostException;
import static org.junit.jupiter.api.Assertions.*;

public class TestMailAddressValidator {
    MailAddressValidator mailAddressValidator = new MailAddressValidator();

    @Test
    public void valid(){
        try{
            assertTrue(mailAddressValidator.isValidInput(new String[]{"maximilian.inckmann@kit.edu"}));
        } catch (Exception e){
            fail(e);
        }
    }

    @Test
    public void validWithSubdomain(){
        try{
            assertTrue(mailAddressValidator.isValidInput(new String[]{"test@scc.kit.edu"}));
        } catch (Exception e){
            fail(e);
        }
    }

    @Test
    public void tooLong(){ assertThrows(IllegalArgumentException.class, () -> { mailAddressValidator.isValidInput(new String[]{"test.test.test.test@fhgakhjsdfgkajhdgfkajhgdfkjahgdfkjahgdfkjahgdfkjahgdfkjahdsgfkjahdgfkjahgdsfkjhagsdfkjahsgdfjhagdsfjhgasdjhkfgasdhjgfajsdgfjasgdfjasgdfjagsdfjhgadsfjhgadsjfkhgash.example.jalsjkfhaldjkhfalkjdhflakjdshflkjahdflkjhadlkjfhaljdshflajshdflkahdsflkjhdaljfhasljdkfhlaksjdhfkjalsdhfljkh.de"});});}

    @Test
    public void invalid(){ assertThrows(IllegalArgumentException.class, () -> { mailAddressValidator.isValidInput(new String[]{""});});}
    @Test
    public void invalid2(){ assertThrows(IllegalArgumentException.class, () -> { mailAddressValidator.isValidInput(new String[]{});});}

    @Test
    public void invalidDot(){ assertThrows(IllegalArgumentException.class, () -> { mailAddressValidator.isValidInput(new String[]{".maximilian@test.example"});});}

    @Test
    public void invalidNumbericTLD(){ assertThrows(IllegalArgumentException.class, () -> { mailAddressValidator.isValidInput(new String[]{"test@test.123"});});}

    @Test
    public void invalidHyphens(){ assertThrows(IllegalArgumentException.class, () -> { mailAddressValidator.isValidInput(new String[]{"test@test.-test-"});});}

    @Test
    public void tooShort(){ assertThrows(IllegalArgumentException.class, () -> { mailAddressValidator.isValidInput(new String[]{"t@es.t"});});}

    @Test
    public void notAnAddress(){ assertThrows(IllegalArgumentException.class, () -> { mailAddressValidator.isValidInput(new String[]{"hello"});});}

    @Test
    public void noRecord(){ assertThrows(UnknownHostException.class, () -> { MailAddressValidator.isValidDomain("test.test.test.kit.edu");});}

    @Test
    public void recordAvailable(){
        try{
            assertTrue(MailAddressValidator.isValidDomain("kit.edu"));
        } catch (Exception e){
            fail(e);
        }
    }

    @Test void testMain(){
        String[] args = {"maximilian.inckmann@kit.edu"};
        try{
            mailAddressValidator.processIsValid(args);
        } catch (Exception e) {
            fail(e);
        }
    }
    @Test void testInvalidMain(){ assertThrows(IllegalArgumentException.class, () -> { mailAddressValidator.processIsValid(new String[]{"maximilian@test.t"});});}

    @Test void testInvalidMainNullArgument(){ assertThrows(IllegalArgumentException.class, () -> { mailAddressValidator.processIsValid(new String[]{});});}

    @Test void testInvalidMainMultipleArguments(){
        // only the first argument gets processed...
        String[] args = {"test@test.example", "t@t", "hello!@t.es", "+49 150 123 456"};
        assertThrows(IllegalArgumentException.class, () -> { mailAddressValidator.processIsValid(args);});
    }
}
