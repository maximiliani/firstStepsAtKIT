package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.validators.impl.DomainValidator;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

public class TestDomainValidator{

    private DomainValidator validator = new DomainValidator();

    @Test
    public void noRecord(){ assertThrows(UnknownHostException.class, () -> { validator.isValid(new String[]{"test.test.test.kit.edu"});});}

    @Test
    public void recordAvailable(){
        try{
            assertTrue(validator.isValid(new String[]{"kit.edu"}));
        } catch (Exception e){
            fail(e);
        }
    }
}
