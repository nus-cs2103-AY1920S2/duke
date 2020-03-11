package dude.component;

import javafx.application.Platform;

/**
 * A class to handle dependencies/ownership between Duke which contains our application logic,
 * and the JavaFX Application/actual GUI components.
 * Duke has ownership of the Gui class, which holds reference to MainWindow which exposes methods to read input
 * and display output but does not own it (MainWindow is owned by JavaFX Application).
 * Duke and MainWindow thus contain references to each other without owning each other.
 */
public class Gui implements IUserInterface {
    private MainWindow mainWindow;
    private StringBuilder responseBuilder;

    /**
     * Initializes the Gui component of the Duke class that handles communication with the actual GUI from
     * within the Duke class.
     * Greets the user.
     *
     * @param mainWindow a reference to the MainWindow controller class so Gui can perform input/output by calling
     *                   the public methods exposed by the controller class.
     */
    public Gui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.responseBuilder = new StringBuilder();
        respond("Wassup dude!");
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
        assert responseBuilder.length() == 0 : "Buffer should be empty";
        r.run();
        mainWindow.displayResponse(responseBuilder.toString());
        responseBuilder.setLength(0); // resets the buffer
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
     * Closes resources associated with the UI.
     */
    @Override
    public void close() {
        Platform.exit();
    }
}
