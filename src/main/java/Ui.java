/**
 * Ui class handles user input.
 */
public class Ui {

    /**
     * Returns string with welcome message.
     * @return string containing welcome message.
     */
    public static String showWelcomeString() {
        String tinga = "       _    _  \n"
                + "     _| |_ (_)\n"
                + "    |_   _\\ _  _ __   ____   __ _\n"
                + "      | |  | \\| '  \\ / _  \\ / _` |\n"
                + "      | |  | || || || |_| || (_| |\n"
                + "      |_|  |_||_||_| \\__  | \\__,_|\n"
                + "                     /\\_| |\n"
                + "                     \\____/\n";
        tinga += "Hey there! I'm tinga :)\n";
        tinga += "What can I do for you?\n";
        return tinga;
    }

    /**
     * Returns loading error where file is not loaded.
     */
    public String showLoadingError() {
        return "     ☹ OOPS!!! File not loaded.";
    }

    /**
     * Returns a string containing error.
     * @param error error message.
     */
    public String showError(String error) {
        return "     ☹ OOPS!!! " + error;
    }
}