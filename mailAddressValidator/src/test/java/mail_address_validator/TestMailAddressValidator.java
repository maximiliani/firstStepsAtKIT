package mail_address_validator;

import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

public class TestMailAddressValidator {
    @Test
    public void valid(){
        try{
            assertTrue(MailAddressValidator.isValidInput("maximilian.inckmann@kit.edu"));
        } catch (Exception e){
            fail(e);
        }
    }

    @Test
    public void validWithSubdomain(){
        try{
            assertTrue(MailAddressValidator.isValidInput("test@scc.kit.edu"));
        } catch (Exception e){
            fail(e);
        }
    }

    @Test
    public void tooLong(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.isValidInput("test.test.test.test@fhgakhjsdfgkajhdgfkajhgdfkjahgdfkjahgdfkjahgdfkjahgdfkjahdsgfkjahdgfkjahgdsfkjhagsdfkjahsgdfjhagdsfjhgasdjhkfgasdhjgfajsdgfjasgdfjasgdfjagsdfjhgadsfjhgadsjfkhgash.example.jalsjkfhaldjkhfalkjdhflakjdshflkjahdflkjhadlkjfhaljdshflajshdflkahdsflkjhdaljfhasljdkfhlaksjdhfkjalsdhfljkh.de");});}

    @Test
    public void invalid(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.isValidInput("");});}
    @Test
    public void invalid2(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.isValidInput(null);});}

    @Test
    public void invalidDot(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.isValidInput(".maximilian@test.example");});}

    @Test
    public void invalidNumbericTLD(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.isValidInput("test@test.123");});}

    @Test
    public void invalidHyphens(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.isValidInput("test@test.-test-");});}

    @Test
    public void tooShort(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.isValidInput("t@es.t");});}

    @Test
    public void notAnAddress(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.isValidInput("hello");});}

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
            MailAddressValidator.main(args);
        } catch (Exception e) {
            fail(e);
        }
    }
    @Test void testInvalidMain(){
        String[] args = {"maximilian@test.t"};
        assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.main(args);});
    }
    @Test void testInvalidMainNullArgument(){
        String[] args = null;
        assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.main(args);});
    }
    @Test void testInvalidMainMultipleArguments(){
        // only the first argument gets processed...
        String[] args = {"test@test.example", "t@t", "hello!@t.es", "+49 150 123 456"};
        assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.main(args);});
    }
}
