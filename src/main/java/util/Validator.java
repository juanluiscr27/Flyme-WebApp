package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    /**
     * Validate the data input from the user
     * @param name User name
     * @return True if it is valid, false otherwise
     */
    public static boolean isValidName(String name) {
        return name != null && !name.trim().equals("");
    }

    /**
     * Validate if the User Phone Number is in a correct format
     * @param phoneNumberString User phone number
     * @return True if it is valid, false otherwise
     */
    public static boolean isValidPhone(String phoneNumberString) {
        boolean isValid = false;
        try {
            long phoneNumber =  Long.parseLong(phoneNumberString);
            if (phoneNumber > 999999999L && phoneNumber <= 9999999999L) {
                isValid = true;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return isValid;
    }

    /**
     * Validate if the User Email is in a correct format
     * @param email User email
     * @return True if it is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("[a-zA-Z\\d._-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,4}?$",
                Pattern.CASE_INSENSITIVE
        );
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
    /**
     * Validate if the User Password is in a correct format
     * @param password User password
     * @return True if it is valid, false otherwise
     */
    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$",
                Pattern.CASE_INSENSITIVE
        );
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
