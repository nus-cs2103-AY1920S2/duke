package duke.commands;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.commands.Command;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;

import java.io.IOException;

public class AddCommand extends Command {
    String type;
    String description;

    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task t = new Task(description);
        if (this.type.equals("todo")) {
            t = new Todo(description);
            tasks.add(t);
        } else if (this.type.equals("deadline")) {
            checkDate("deadline");
            t = new Deadline(description);
            tasks.add(t);
        } else if (this.type.equals("event")) {
            checkDate("event");
            t = new Event(description);
            tasks.add(t);
        }
        storage.writeToFile(tasks.saveList());
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + t);
        tasks.printSize();
    }

    public boolean isExit() {
        return false;
    }

    public void checkDate(String deadlineOrEvent) throws DukeException {
        if (deadlineOrEvent.equals("deadline")) {
            String[] arr = description.split(" /by ");
            // error: task is missing deadline
            if (arr.length <= 1) {
                throw new DukeException("     ☹ OOPS!!! Deadline of a task cannot be empty.\n");
            }
        } else if (deadlineOrEvent.equals("event")) {
            String[] arr = description.split(" /at ");
            // error: event is missing time
            if (arr.length <= 1) {
                throw new DukeException("     ☹ OOPS!!! Time of an event cannot be empty.\n");
            }
        }
    }
}