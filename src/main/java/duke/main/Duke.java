package duke.main;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.TooManyTasksException;
import duke.exception.UnknownCommandException;
import duke.processor.Processor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList tasks;

    // Empty constructor required for duke.main.Launcher
    public Duke() {
    }

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromSaveFile());

            // Since we can successfully load it, we save all in duke.main.TaskList
            storage.writeTasks(tasks);
        } catch (IOException e) {
            tasks = new TaskList();
            Ui.printLine();
            Ui.indent("It seems that there is no save file in: " + filePath);
            Ui.printLine();
        }
    }

    public void run() {
        Ui.greet();
        try {
            Processor.process(tasks, storage);
        } catch (DukeException e) {
            String message = Ui.indentWithNewline(e.toString(), 1);
            Ui.printSection(message);
        } catch (DateTimeParseException e) {
            String message = "";
            message += Ui.indentWithNewline("It seems that you have entered a format we don't understand",
                    1);
            message += Ui.indentWithNewline("Please use the YYYY-MM-DD format.", 1);
            Ui.printSection(message);
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    // TODO: Refactor this part to be integrated with CLI UI
    public String getResponse(String input) {
        String toReturn;
        try {
            if (Parser.commandEquals("bye", input)) {
                // Ui.sayGoodbye();
                toReturn = "Bye!";
            } else if (Parser.commandEquals("list", input)) {
                Ui.displayList(tasks);
                toReturn = tasks.toString();
            } else if (Parser.commandEquals("done", input)) {
                int taskNo = Parser.getTaskNo(input);
                tasks.markTaskAsDone(taskNo);

                toReturn = "I have marked that task as done!";
            } else if (Parser.commandEquals("delete", input)) {
                int taskNo = Parser.getTaskNo(input);
                tasks.deleteTask(taskNo);

                toReturn = "I have deleted that task!";
            } else if (Parser.commandEquals("deadline", input)) {
                if (Parser.hasNoArgs(input)) throw new EmptyDescriptionException();
                if (tasks.isFull()) throw new TooManyTasksException();

                tasks.addDeadline(Parser.getArgs(input));
                toReturn = "I have added that deadline!";
            } else if (Parser.commandEquals("event", input)) {
                if (Parser.hasNoArgs(input)) throw new EmptyDescriptionException();
                if (tasks.isFull()) throw new TooManyTasksException();

                tasks.addEvent(Parser.getArgs(input));

                toReturn = "I have added that event!";
            } else if (Parser.commandEquals("todo", input)) {
                if (Parser.hasNoArgs(input)) throw new EmptyDescriptionException();
                if (tasks.isFull()) throw new TooManyTasksException();

                tasks.addTodo(Parser.getArgs(input));
                toReturn = "I have added that todo!";
            } else if (Parser.commandEquals("find", input)) {
                if (Parser.hasNoArgs(input)) throw new EmptyDescriptionException();

                toReturn = tasks.findToString(Parser.getArgs(input));
            } else {
                throw new UnknownCommandException();
            }

            // Rewrites the entire file for every update you make here
            // Probably O(n^2) time where n is the number of tasks but this is the simplest change we can make
            storage.writeTasks(tasks);
            return toReturn;
        } catch (DukeException e) {
            toReturn = e.toString();
            return toReturn;
        }
    }
}
