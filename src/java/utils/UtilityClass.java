package utils;

public class UtilityClass {

    public static boolean stringIsEmpy(String str) {
        return str == null || str.trim().equals("");
    }// stringIsEmpty

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }// end of try block
    }// end of method   
    
    
}// end of UtilityClas
