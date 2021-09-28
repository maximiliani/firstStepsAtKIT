package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.Main;
import org.junit.jupiter.api.Test;

import static org.junit.Assume.assumeNoException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMain {
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
    void rValid() {
        String[] args = {"-r", "-i", "072160828033"};
        try {
            Main.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    void rInvalid() {
        String[] args = {"-r", "-i", "07216"};
        assertThrows(ValidatorInterface.ValidationException.class, () -> Main.main(args));
    }

    @Test
    void gValid() {
        String[] args = {"-g", "-i", "00497216082804", "-c", "DE"};
        try {
            Main.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    void gWithoutC() {
        String[] args = {"-g", "-i", "00497216082804"};
        assertThrows(ValidatorInterface.ValidationException.class, () -> Main.main(args));
    }

    @Test
    void gInvalid() {
        String[] args = {"-g", "-i", "+91", "-c", "DE"};
        assertThrows(ValidatorInterface.ValidationException.class, () -> Main.main(args));
    }

    @Test
    void mValid() {
        String[] args = {"-m", "-i", "test@kit.edu"};
        try {
            Main.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    void mInvalid() {
        String[] args = {"-m", "-i", "test@123.123"};
        assertThrows(ValidatorInterface.ValidationException.class, () -> Main.main(args));
    }

    @Test
    void dValid() {
        String[] args = {"-d", "-i", "kit.edu"};
        try {
            Main.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    void dInvalid() {
        String[] args = {"-d", "-i", "test.test.dem.scc.kit"};
        assertThrows(ValidatorInterface.ValidationException.class, () -> Main.main(args));
    }
}
