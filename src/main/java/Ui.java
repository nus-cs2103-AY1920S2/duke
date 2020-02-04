/**
 * Represents a ui object that deals with interactions with the user.
 */
public class Ui {

    /**
     * Generates intro message.
     * @return A string containing the intro message
     */
    public String showWelcome() {
        String intro = "--------------------------------------------------\n" +
                "Wassup! Wo shi bot \n" +
                "You want what? \n" +
                "--------------------------------------------------\n";

        return intro;
    }

    /**
     * Generates goodbye message.
     * @return A string containing the goodbye message
     */
    public String goodBye() {
        String goodbye = "--------------------------------------------------\n" +
                "Why you so ba...bot has been killed\n" +
                "--------------------------------------------------\n";

        return goodbye;
    }

    /**
     * Generates loading error message.
     * @return A string containing the loading error message
     */
    public String showLoadingError() {
       return "Error loading file";
    }

    /**
     * Generates exception error message.
     * @return A string containing exception error message
     */
    public String showError(Exception e) {
       return e.toString();
    }

}
