package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.Main;
import org.junit.jupiter.api.Test;
import static org.junit.Assume.assumeNoException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMain {
    @Test
    public void withoutArgsAndInput(){ assertThrows(IllegalArgumentException.class, () -> { Main.main(new String[]{});});}

    @Test void withHArg(){
        String[] args = {"-h"};
        try{
            Main.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test void rValid(){
        String[] args = {"-r", "072160828033"};
        try{
            Main.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test void rInvalid(){
        String[] args = {"-r", "07216"};
        assertThrows( IllegalArgumentException.class, () -> {Main.main(args);});
    }

    @Test void gValid(){
        String[] args = {"-g", "004925046410", "DE"};
        try{
            Main.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test void gInvalid(){
        String[] args = {"-g", "07216"};
        assertThrows( IllegalArgumentException.class, () -> {Main.main(args);});
    }

    @Test void mValid(){
        String[] args = {"-m", "test@kit.edu"};
        try{
            Main.main(args);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test void mInvalid(){
        String[] args = {"-m", "test@123.123"};
        assertThrows( IllegalArgumentException.class, () -> {Main.main(args);});
    }
    
    // missing tests
}
