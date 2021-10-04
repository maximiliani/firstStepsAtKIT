package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.Main;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assume.assumeNoException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMain {

    @Test
    void newMain(){
        Main test = new Main();
    }

    @Test
    void validGoogleValidatorType() {
        LogCaptor logCaptor = LogCaptor.forClass(Main.class);
        InputStream stdin = System.in;
        String testString = "googleValidator" + System.getProperty("line.separator");
        String expectedMessage = "googleValidator input recognized - starting PhoneNumberValidator";
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            Main.main(new String[]{});
        } catch (ValidatorInterface.ValidationException e) {
            assertTrue(logCaptor.getDebugLogs().contains(expectedMessage));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void validMailValidatorType() {
        LogCaptor logCaptor = LogCaptor.forClass(Main.class);
        InputStream stdin = System.in;
        String testString = "mailValidator" + System.getProperty("line.separator");
        String expectedMessage = "mailValidator input recognized - starting MailAddressValidator";
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            Main.main(new String[]{});
        } catch (ValidatorInterface.ValidationException e) {
            assertTrue(logCaptor.getDebugLogs().contains(expectedMessage));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void validDomainValidatorType() {
        LogCaptor logCaptor = LogCaptor.forClass(Main.class);
        InputStream stdin = System.in;
        String testString = "domainValidator" + System.getProperty("line.separator");
        String expectedMessage = "domainValidator input recognized - starting DomainValidator";
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            Main.main(new String[]{});
        } catch (ValidatorInterface.ValidationException e) {
            assertTrue(logCaptor.getDebugLogs().contains(expectedMessage));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void validRegexValidatorType() {
        LogCaptor logCaptor = LogCaptor.forClass(Main.class);
        InputStream stdin = System.in;
        String testString = "regexValidator" + System.getProperty("line.separator");
        String expectedMessage = "regexValidator input recognized - starting RegexNumberValidator";
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            Main.main(new String[]{});
        } catch (ValidatorInterface.ValidationException e) {
            assertTrue(logCaptor.getDebugLogs().contains(expectedMessage));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void invalidValidatorType() {
        LogCaptor logCaptor = LogCaptor.forClass(Main.class);
        InputStream stdin = System.in;
        String testString = "t" + System.getProperty("line.separator");
        String expectedMessage = "Invalid user input given!";
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            Main.main(new String[]{});
        } catch (ValidatorInterface.ValidationException e) {
            assertTrue(logCaptor.getErrorLogs().contains(expectedMessage));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void noValidatorType() {
        LogCaptor logCaptor = LogCaptor.forClass(Main.class);
        InputStream stdin = System.in;
        String testString = System.getProperty("line.separator");
        String expectedMessage = "Invalid user input given!";
        try {
            System.setIn(new ByteArrayInputStream(testString.getBytes()));
            Main.main(new String[]{});
        } catch (ValidatorInterface.ValidationException e) {
            assertTrue(logCaptor.getErrorLogs().contains(expectedMessage));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void withoutArgsAndInput() {
        assertThrows(ValidatorInterface.ValidationException.class, () -> Main.main(new String[]{}));
    }

    @Test
    void withHArg() {
        String[] args = {"-h"};
        try {
            Main.main(args);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            assumeNoException(e);
        }
    }

    @Test
    void withHLongArg() {
        String[] args = {"-help"};
        try {
            Main.main(args);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            assumeNoException(e);
        }
    }

    @Test
    void withHLongAndDoubleHyphenArg() {
        String[] args = {"--help"};
        try {
            Main.main(args);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            assumeNoException(e);
        }
    }

    @Test
    void rValid() {
        String[] args = {"-t", "regex", "-i", "072160828033"};
        try {
            Main.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    void rInvalid() {
        String[] args = {"-t", "regex", "-i", "07216"};
        assertThrows(ValidatorInterface.ValidationException.class, () -> Main.main(args));
    }

    @Test
    void gValid() {
        String[] args = {"-t", "google", "-i", "00497216082804", "-c", "DE"};
        try {
            Main.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    void gWithoutC() {
        String[] args = {"-t", "google", "-i", "00497216082804"};
        assertThrows(ValidatorInterface.ValidationException.class, () -> Main.main(args));
    }

    @Test
    void gInvalid() {
        String[] args = {"-t", "google", "-i", "+91", "-c", "DE"};
        assertThrows(ValidatorInterface.ValidationException.class, () -> Main.main(args));
    }

    @Test
    void mValid() {
        String[] args = {"-t", "mail", "-i", "test@kit.edu"};
        try {
            Main.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    void mInvalid() {
        String[] args = {"-t", "mail", "-i", "test@123.123"};
        assertThrows(ValidatorInterface.ValidationException.class, () -> Main.main(args));
    }

    @Test
    void dValid() {
        String[] args = {"-t", "domain", "-i", "kit.edu"};
        try {
            Main.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    void dInvalid() {
        String[] args = {"-t", "domain", "-i", "test.test.dem.scc.kit"};
        assertThrows(ValidatorInterface.ValidationException.class, () -> Main.main(args));
    }
}
