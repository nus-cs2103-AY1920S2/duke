package duke.util;

/**
 * The {@code StringCleaner} class is a helper class that cleans {@code String}s
 * according to the usage requirements.
 */
public class StringCleaner {
    /**
     * Cleans and returns input string.
     *
     * @param string Raw string.
     * @return Cleaned string with leading and trailing whitespaces removed.
     */
    public static String cleanString(String string) {
        return string.trim();
    }

    /**
     * Cleans and returns input string in lowercase.
     *
     * @param string Raw string.
     * @return Cleaned lowercase string with leading and trailing whitespaces removed.
     */
    public static String cleanAndLowerString(String string) {
        return string.trim().toLowerCase();
    }
}
