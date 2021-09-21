package mail_address_validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMailAddressValidator {
    @Test
    public void valid(){ assertTrue(MailAddressValidator.validInput("maximilian.inckmann@kit.edu"));}

    @Test
    public void valid2(){ assertTrue(MailAddressValidator.validInput("test@123.test.example"));}

    @Test
    public void tooLong(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.validInput("test.test.test.test@fhgakhjsdfgkajhdgfkajhgdfkjahgdfkjahgdfkjahgdfkjahgdfkjahdsgfkjahdgfkjahgdsfkjhagsdfkjahsgdfjhagdsfjhgasdjhkfgasdhjgfajsdgfjasgdfjasgdfjagsdfjhgadsfjhgadsjfkhgash.example.jalsjkfhaldjkhfalkjdhflakjdshflkjahdflkjhadlkjfhaljdshflajshdflkahdsflkjhdaljfhasljdkfhlaksjdhfkjalsdhfljkh.de");});}

    @Test
    public void invalid(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.validInput("");});}
    @Test
    public void invalid2(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.validInput(null);});}

    @Test
    public void invalidDot(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.validInput(".maximilian@test.example");});}

    @Test
    public void invalidNumbericTLD(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.validInput("test@test.123");});}

    @Test
    public void invalidHyphens(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.validInput("test@test.-test-");});}

    @Test
    public void tooShort(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.validInput("t@es.t");});}

    @Test
    public void notAnAddress(){ assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.validInput("hello");});}

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
    @Test void testInvalidMain2(){
        String[] args = null;
        assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.main(args);});
    }
    @Test void testInvalidMain3(){
        String[] args = {"test@test.example", "t@t", "hello!@t.es", "+49 150 123 456"};
        assertThrows(IllegalArgumentException.class, () -> { MailAddressValidator.main(args);});
    }
}
