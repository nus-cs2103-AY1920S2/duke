package dude.component;

public interface IUserInterface {
    /**
     * Obtains input from user.
     *
     * @return command user gives to Dude as a String to be parsed by Parser.
     */
    String readCommand();

    /**
     * Displays Dude's response to the user.
     * Accepts a Runnable that can execute arbitrary code to give maximum flexibility in calling code,
     * such as being able to have a for loop for indeterminate number of lines of responses.
     * However, the only primitive exposed to actually display output within respond is speak(String str).
     *
     * @param r Runnable that allows caller to execute arbitrarily complex code while creating a response.
     */
    void respond(Runnable r);

    /**
     * Displays variable number of sentences to the user.
     * Exposes a more pleasant API than respond(Runnable r) when full flexibility is not needed.
     *
     * @param responses a variable number of sentences for Dude to tell the user.
     */
    default void respond(String... responses) {
        respond(() -> {
            for (String response : responses) {
                speak(response);
            }
        });
    }

    /**
     * Displays an error message to the user when an incorrect command is given.
     * Tells the user what was wrong and gives the proper usage of the command.
     *
     * @param errorMsg a message describing the problem with the user's input.
     * @param usageMsg a string describing the correct format of input.
     */
    default void respondParsingError(String errorMsg, String usageMsg) {
        respond(errorMsg,
                "Maybe you could try this:" + System.lineSeparator(),
                "  " + usageMsg);
    }

    /**
     * Displays an error message to the user when an invalid command is given.
     *
     * @param errorMsg a message describing the problem with the user's input.
     */
    default void respondError(String errorMsg) {
        respond(errorMsg);
    }

    /**
     * Speaks a single sentence to the user.
     * The only primitive exposed to construct a response to pass to respond(Runnable r).
     *
     * @param str the sentence to speak to the user.
     */
    void speak(String str);

    /**
     * Closes resources associated with the UI.
     */
    void close();
}
