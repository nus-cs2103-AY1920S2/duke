package duke.main;

import duke.constants.Constants;

public class Duke {

    private UiHandler uiHandler;
    private DukeWorker worker;

    /**
     * Constructor for Duke object.
     */
    public Duke() {
        uiHandler = new UiHandler();
        worker = new DukeWorker(Constants.DUKE_FILE_PATH);
        worker.initializeWorker();
    }

    /**
     * Run instance of duke without GUI.
     */
    public void run() {
        uiHandler.welcomeMessage();
        String request = "";
        while (!uiHandler.isExit()) {
            request = uiHandler.requestInput().trim().toLowerCase();
            worker.handleRequest(request, uiHandler);
            uiHandler.displayResponse();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String request = input;
        worker.handleRequest(request.toLowerCase().trim(), uiHandler);
        return uiHandler.getResponse();
    }
}
