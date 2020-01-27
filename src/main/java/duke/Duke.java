package duke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeFileNotFoundException;
import duke.exception.DukeReadFileException;
import duke.exception.DukeNoCommandException;
import duke.exception.DukeNoSuchInputException;
import duke.exception.DukeProgramTerminatedException;
import duke.ui.Ui;
import duke.utils.TaskList;

public class Duke {
    private Ui ui;
    private TaskList tasks;

    public Duke() {
        this.ui = new Ui();
        try {
            FileInputStream fis = new FileInputStream("data/data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.tasks = (TaskList) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            ui.printException(new DukeFileNotFoundException(e));
            this.tasks = new TaskList();
        } catch (IOException | ClassNotFoundException e) {
            ui.printException(new DukeReadFileException(e));
            this.tasks = new TaskList();
        }
    }

    public TaskList getTaskList() {
        return tasks;
    }

    private void run() {
        ui.printWelcome();
        while (true) {
            try {
                String input = ui.readInput();
                Command command = Command.createCommand(input);
                String message = command.execute(this);
                ui.print(message);
            } catch (DukeNoSuchInputException | DukeProgramTerminatedException e) {
                break;
            } catch (DukeNoCommandException e) {
                continue;
            } catch (DukeException e) {
                ui.printException(e);
            }
        }
        ui.printGoodbye();
        ui.close();
    }

    public static void main(String[] args) {
        new Duke().run();
        System.exit(0);
    }
}
