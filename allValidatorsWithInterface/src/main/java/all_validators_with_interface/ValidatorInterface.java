package all_validators_with_interface;

/**
 * This interface provides two methods which are necessary for validating things.
 * @author maximilianiKIT
 */
public interface ValidatorInterface {
    public void processIsValid(String[] input) throws Exception;

    public boolean isValidInput(String[] input) throws Exception;
}
