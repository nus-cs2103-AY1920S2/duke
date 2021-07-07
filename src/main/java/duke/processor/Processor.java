package duke.processor;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.TooManyTasksException;
import duke.exception.UnknownCommandException;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class Processor {
    public static void process(TaskList tasks, Storage storage) throws DukeException {
        while (Parser.stillHasInputs()) {
            String input = Parser.readLine();
            String toReturn;
            if (Parser.commandEquals("bye", input)) {
                toReturn = Ui.getGoodbyeMessage();

                // The other print sections are after the series of if-elses, so by inserting a break in this block
                // I have to print section here
                Ui.printSection(toReturn);
                break;
            } else if (Parser.commandEquals("list", input)) {
                toReturn = Ui.displayListInUi(tasks, "");
            } else if (Parser.commandEquals("done", input)) {
                int taskNo = Parser.getTaskNo(input);
                toReturn = tasks.markTaskAsDone(taskNo);
            } else if (Parser.commandEquals("delete", input)) {
                int taskNo = Parser.getTaskNo(input);
                toReturn = tasks.deleteTask(taskNo);
            } else if (Parser.commandEquals("deadline", input)) {
                if (Parser.hasNoArgs(input)) {
                    throw new EmptyDescriptionException();
                }
                if (tasks.isFull()) {
                    throw new TooManyTasksException();
                }

                toReturn = tasks.addDeadline(Parser.getArgs(input));
            } else if (Parser.commandEquals("event", input)) {
                if (Parser.hasNoArgs(input)) {
                    throw new EmptyDescriptionException();
                }
                if (tasks.isFull()) {
                    throw new TooManyTasksException();
                }

                toReturn = tasks.addEvent(Parser.getArgs(input));
            } else if (Parser.commandEquals("todo", input)) {
                if (Parser.hasNoArgs(input)) {
                    throw new EmptyDescriptionException();
                }
                if (tasks.isFull()) {
                    throw new TooManyTasksException();
                }

                toReturn = tasks.addTodo(Parser.getArgs(input));
            } else if (Parser.commandEquals("find", input)) {
                if (Parser.hasNoArgs(input)) {
                    throw new EmptyDescriptionException();
                }

                toReturn = tasks.find(Parser.getArgs(input));
            } else if (Parser.commandEquals("sort", input)) {
                toReturn = tasks.sort();
            } else {
                throw new UnknownCommandException();
            }

            Ui.printSection(toReturn);
            // Rewrites the entire file for every update you make here
            // Probably O(n^2) time where n is the number of tasks but this is the simplest change we can make
            storage.writeTasks(tasks);
        }
    }
}