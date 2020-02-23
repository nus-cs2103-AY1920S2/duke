package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeNoCommandException;
import duke.exception.DukeNoSuchInputException;
import duke.exception.DukeProgramTerminatedException;
import duke.storage.Storage;
import duke.ui.Gui;
import duke.ui.TextUi;
import duke.ui.Ui;
import duke.utils.TaskList;

public class Duke {
    public static final String NAME = "DUKE";

    private static Duke program;

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private boolean hasTerminated;

    private Duke(boolean useCli) {
        this.ui = (useCli) ? new TextUi() : new Gui();
        this.storage = new Storage();
        this.hasTerminated = false;
        try {
            this.tasks = storage.loadTaskList();
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    public static Duke getProgram() {
        return Duke.getProgram(new String[0]);
    }

    private static Duke getProgram(String[] args) {
        if (Duke.program == null) {
            Duke.program = new Duke(args.length == 1 && args[0].equals("-t"));
        }
        return Duke.program;
    }

    public Ui getUi() {
        return ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public TaskList getTaskList() {
        return tasks;
    }

    public Storage getStorage() {
        return storage;
    }

    public boolean hasTerminated() {
        return hasTerminated;
    }

    public void setAsTerminated() {
        hasTerminated = true;
    }

    public void handleUserInput(String input) {
        try {
            Command command = Command.createCommand(input);
            command.execute();
        } catch (DukeProgramTerminatedException e) {
            setAsTerminated();
        } catch (DukeNoCommandException e) {
            return;
        } catch (DukeException e) {
            ui.printException(e);
        }
    }

    private void run() {
        ui.begin();
        while (!hasTerminated) {
            try {
                String input = ui.readInput();
                handleUserInput(input);
            } catch (DukeNoSuchInputException e) {
                setAsTerminated();
                break;
            }
        }
        ui.end();
    }

    public static void main(String[] args) {
        Duke.getProgram(args).run();
        System.exit(0);
    }
}
