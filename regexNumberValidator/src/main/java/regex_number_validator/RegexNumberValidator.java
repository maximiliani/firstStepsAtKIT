package regex_number_validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Add java documentation here
 *
 */
public class RegexNumberValidator {

  /**
   * And here...
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("Dieses Programm validiert deutsche Telefonnummern");
    System.out.println("Bitte nutzen Sie ausschließlich die deutsche nationale Schreibweise und benutzen Sie keine Sonder-/Leerzeichen.");
    // Check if any argument is given.
    // If no argument is given you may return with an error exit code and an usage output.
    // If multiple arguments are given you may validate one after another or return an error and usage
    String number = args[0];
    // Gültig <-> Ungültig looks quite similar. In case of an error it should be obvious.
    // If a method should return true most times it might be easier to throw
    // an exception instead of if - else. If number is invalid all further 
    // steps are not necessary. 
    // try {
    // ....
    // validateNumber
    // ... further steps if number is valid
    // catch (...) {
    // errorhandlich 
    // return errorcode
    //}
    if (validInput(number)) {
      System.out.println("Gültige Nummer!");
    } else {
      System.out.println("Ungültige Nummer!");
    }
  }

  /**
   * And here...
   *
   * @param args
   */
  public static boolean validInput(String inputNumber) {
//        String regex = "^(010[1-9][0-9]|0100[0-9][0-9]|110|112|115|116[01][0-9][0-9]|118[1-9][0-9]|11800[1-9]|0137[1-9]|015[0-9]{2,3}|016[23489]|017[0-9]|018|019[1-4]|02[0-9]{1,3}|031[01]|03[02-9][0-9]{1,3}|04[0-9]{1,4}|050[3-9][0-9]{1,2}|05[1-9][0-9]{1,3}|06[0-9]{1,4}|070[02-9][0-9]{1,2}|07[1-9][0-9]{1,3}|080[02-9][0-9]{1,2}|08[1-9][0-9]{1,3}|0900[1359]|09[1-9][0-9]{1,3})([1-9][0-9]{2,7})?";
    // great! But what the fuck you are doing here? ;-)
    // Maybe add a link to an external site which explains the meaning of this pattern
    // or explain it yourself in detail. 
    // In case of an error or an extension it might be easier to fix this pattern.
    String regex = "^(010[1-9]\\d|0100\\d\\d|110|112|115|116[01]\\d\\d|118[1-9]\\d|11800[1-9]|0137[1-9]|015\\d{2,3}|016[23489]|017\\d|018|019[1-4]|02\\d{1,3}|031[01]|03[02-9]\\d{1,3}|04\\d{1,4}|050[3-9]\\d{1,2}|05[1-9]\\d{1,3}|06\\d{1,4}|070[02-9]\\d{1,2}|07[1-9]\\d{1,3}|080[02-9]\\d{1,2}|08[1-9]\\d{1,3}|0900[1359]|09[1-9]\\d{1,3})([1-9]\\d{2,7})?";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(inputNumber);

    if (matcher.find()) {
      System.out.println("Telefonnummer: " + matcher.group(0));
      System.out.println("Vorwahl: " + matcher.group(1));
      System.out.println("Teilnehmer: " + matcher.group(2));
      if (matcher.group(2) != null && matcher.group(0).length() > 2 && matcher.group(0).length() <= 13) {
        return true;
      } else if (matcher.group(0).equals("110")) {
        return true;
      } else if (matcher.group(0).equals("112")) {
        return true;
      } else if (matcher.group(0).equals("115")) {
        return true;
      } else {
        return matcher.group(0).equals("0310") || matcher.group(0).equals("0311");
      }
    }
    return false;
  }
}
