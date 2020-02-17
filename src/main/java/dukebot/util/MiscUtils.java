package dukebot.util;

/**
 * Class containing static misc utilities.
 */
public class MiscUtils {

    /**
     * Checks if a string is a valid integer.
     * Source:
     * stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java
     *
     * @param str String to check.
     * @return Whether the string can be parsed as integer safely.
     */
    // @@author gerhean-reused
    // Reused from:
    // stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
    // @@author
}
