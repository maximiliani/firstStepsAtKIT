package edu.kit.scc.dem.regex_number_validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class validates German telephone numbers.
 *
 */
public class RegexNumberValidator {

  /**
   * This main method calls the validInput method and prints information to the console.
   *
   * @param args phonenumber to validate
   */
  public static void main(String[] args) throws IllegalArgumentException {
    System.out.println("Dieses Programm validiert deutsche Telefonnummern");
    System.out.println("Bitte nutzen Sie ausschließlich die deutsche nationale Schreibweise und benutzen Sie keine Sonder-/Leerzeichen.");
    System.out.println();

    if ( args == null || args.length!=1 || args[0] == null) {
      System.out.println("ERROR: Ungültige Eingabe! ");
      System.out.println("       Bitte achten Sie darauf, dass Sie keine Sonder- und Leerzeichen verwenden!");
      System.out.println();
      throw new IllegalArgumentException();
    }
    else{
      try{
        String number = args[0];
        validInput(number);
        System.out.println("Die Nummer ist gültig!");
      } catch (Exception e) {
        System.out.println("FEHLER:   Die Nummer ist ungültig!");
        System.out.println();
        e.printStackTrace();
        throw e;
      }
    }
  }

  /**
   * This method validates German phone numbers and throws an IllegalArgumentException if the number isn't valid.
   *
   * @param inputNumber has to be a german national number without special characters or spaces
   * @return true, if the number is valid
   */
  public static boolean validInput(String inputNumber) throws IllegalArgumentException {

    if(inputNumber == null) throw new IllegalArgumentException();
    /**
     * This regex pattern was built based on the information about the german phone number system provided by the Bundesnetzagentur (Federal Grid Agency).
     * It seperates the area code from the participants number by the position in the number.
     * Emergency numbers like 110, 112 or other special short numbers are interpreted as an area code by this regex, so there is no participants number.
     * In this case it must be verified that the area code matches one of these special numbers. This is done in the if-else-construct below.
     */

    String regex = "^(010[1-9]\\d|0100\\d\\d|110|112|115|116[01]\\d\\d|118[1-9]\\d|11800[1-9]|0137[1-9]|015\\d{2,3}|016[23489]|017\\d|018|019[1-4]|02\\d{1,3}|031[01]|03[02-9]\\d{1,3}|04\\d{1,4}|050[3-9]\\d{1,2}|05[1-9]\\d{1,3}|06\\d{1,4}|070[02-9]\\d{1,2}|07[1-9]\\d{1,3}|080[02-9]\\d{1,2}|08[1-9]\\d{1,3}|0900[1359]|09[1-9]\\d{1,3})([1-9]\\d{2,7})?";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(inputNumber);

    if (matcher.find()) {
      System.out.println("Erkannte Telefonnummer: " + matcher.group(0));
      System.out.println("Vorwahl: " + matcher.group(1));
      System.out.println("Teilnehmer: " + matcher.group(2));
      if (matcher.group(2) != null && matcher.group(0).length() > 2 && matcher.group(0).length() <= 13) return true;
      else if (matcher.group(0).equals("110") && inputNumber.equals("110")) return true;
      else if (matcher.group(0).equals("112") && inputNumber.equals("112")) return true;
      else if (matcher.group(0).equals("115") && inputNumber.equals("115")) return true;
      else if (matcher.group(0).equals("0310") && inputNumber.equals("0310")) return true;
      else if (matcher.group(0).equals("0311") && inputNumber.equals("0311")) return true;
    }
    throw new IllegalArgumentException();
  }
}
