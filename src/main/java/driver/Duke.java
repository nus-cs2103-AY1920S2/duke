package driver;

import utilities.TaskHandler;
import greetings.WelcomeGoodbye;

public class Duke {

    /**
     * Main driver method
     *
     * @param args standard argument
     */
    public static void main(String[] args) {

        // Welcomes the user
        WelcomeGoodbye wm = new WelcomeGoodbye();
        wm.welcome();

        // Begins accepting input commands
        TaskHandler taskHandler = new TaskHandler();
        taskHandler.serveUser();

        // User is done
        wm.goodbye();

    }

}
