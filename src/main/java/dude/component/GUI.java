package dude.component;

public class GUI implements IUserInterface {

    private MainWindow mainWindow;
    private StringBuilder responseBuilder;

    public GUI(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.responseBuilder = new StringBuilder();
    }

    /**
     * Obtains input from user.
     *
     * @return command user gives to Dude as a String to be parsed by Parser.
     */
    @Override
    public String readCommand() {
        return mainWindow.getInput();
    }

    /**
     * Displays Dude's response to the user.
     * Accepts a Runnable that can execute arbitrary code to give maximum flexibility in calling code,
     * such as being able to have a for loop for indeterminate number of lines of responses.
     * However, the only primitive exposed to actually display output within respond is speak(String str).
     *
     * @param r Runnable that allows caller to execute arbitrarily complex code while creating a response.
     */
    @Override
    public void respond(Runnable r) {
        r.run();
        mainWindow.displayResponse(responseBuilder.toString());
        responseBuilder.setLength(0); // resets the buffer
    }

    /**
     * Displays variable number of sentences to the user.
     * Exposes a more pleasant API than respond(Runnable r) when full flexibility is not needed.
     *
     * @param responses a variable number of sentences for Dude to tell the user.
     */
    @Override
    public void respond(String... responses) {
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
     * @param errorMsg A message describing the problem with the user's input.
     * @param usageMsgs variable number of strings describing the correct format of input.
     */
    @Override
    public void respondParsingError(String errorMsg, String... usageMsgs) {
        respond(() -> {
            speak(errorMsg);
            speak("Just tell me what you want to do like this:" + System.lineSeparator());
            for (String usageMsg : usageMsgs) {
                speak("  " + usageMsg);
            }
            speak("");
            speak("Then we're chill");
        });
    }

    /**
     * Speaks a single sentence to the user.
     * The only primitive exposed to construct a response to pass to respond(Runnable r).
     * In GUI, speak adds the sentence to a buffer which is cleared when one of the respond methods are called
     * so that multiple lines of input can be spoken at once.
     *
     * @param str the sentence to speak to the user.
     */
    @Override
    public void speak(String str) {
        responseBuilder.append(str).append('\n');
    }

    /**
     * No Op. GUI holds on to no extra resources.
     */
    @Override
    public void close() {
    }
}
