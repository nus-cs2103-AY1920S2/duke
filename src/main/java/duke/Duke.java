package duke;

import java.io.IOException;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isLastGuiCmd;

    /**
     * Constructs the Duke class object.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage();
            this.tasks = storage.loadTasksFile();
        } catch (Exception e) {
            ui.printError(e);
        }
        this.isLastGuiCmd = false;
    }


    /**
     * Process commands in the CLI version of Duke.
     */
    private void interact() {

        boolean byebye = false;
        System.out.println(initialise());

        while (!byebye) {
            ui.printLine1();
            try {
                byebye = Parser.parseNextCmd(tasks, ui);
                storage.writeTasksFile(tasks);
            } catch (DukeException e) {
                ui.printError(e);
            } catch (IOException e) {
                ui.printError(e);
                break;
            }
            ui.printLine2();
            System.out.println(ui.cathulhuSaysItOut());
        }
        ui.showGoodbye();
        System.out.println(ui.cathulhuSaysItOut());
    }


    /**
     * Process commands in the GUI version of Duke, based on user input.
     */
    private String interactGui(String input) {
        ui.printLine1();
        try {
            this.isLastGuiCmd = Parser.parseNextCmd(tasks, ui, input);
            storage.writeTasksFile(tasks);
        } catch (Exception e) {
            ui.printError(e);
        }
        ui.printLine2();

        if (this.isLastGuiCmd) {
            ui.flush();
            ui.showGoodbye();
        }

        return ui.cathulhuSaysItOut();
    }


    /**
     * Displays the welcome message for Cathulhu.
     *
     * @return String welcome message.
     */
    public String initialise() {
        ui.showWelcome();
        return ui.cathulhuSaysItOut();
    }


    /**
     * Wraps the handler method for inputs from the GUI.
     *
     * @param input String input from the user on the GUI.
     * @return String output to be displayed on the GUI.
     */
    public String getResponse(String input) {
        if (this.isLastGuiCmd) {
            return "I am done with you, mortal. Do not disturb me nyaa~ ";
        }
        return interactGui(input);
    }


    public static void main(String[] args) {
        new Duke().interact();
    }
}
