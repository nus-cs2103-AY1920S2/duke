package duke.utils;

public class Utils {

    /**
     * Static method to check if string is numerical.
     * @param strNum number in string format
     * @return
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
