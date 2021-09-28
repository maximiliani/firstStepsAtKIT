package edu.kit.scc.dem.first_steps.validators;

import edu.kit.scc.dem.first_steps.validators.impl.DomainValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class testValidatorInterface {

    @Test
    void askForInputAndValidate() {
        ValidatorInterface validatorInterface = new DomainValidator();
        assertThrows(ValidatorInterface.ValidationException.class, validatorInterface::askForInputAndValidate);
    }

    @Test
    void ValidatorExceptionWithoutMessage(){
        assertThrows(ValidatorInterface.ValidationException.class, () ->{throw new ValidatorInterface.ValidationException();});
    }

    @Test
    void ValidatorExceptionWithMessage(){
        assertThrows(ValidatorInterface.ValidationException.class, () ->{throw new ValidatorInterface.ValidationException("test",new Exception());});
    }
}