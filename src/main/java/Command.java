import java.io.IOException;

abstract class Command {

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    abstract boolean isExit();

}

/*
public class Command {
    String type;
    String description;
    int index;
    boolean isTask;
    boolean isMarking;


    public Command(String type) {
        this.type = type;
        this.isTask = false;
        this.isMarking = false;
    }

    public Command(String type, String description) {
        this.type = type;
        this.description = description;
        this.isTask = true;
        this.isMarking = false;
    }

    public Command(String type, int index) {
        this.type = type;
        this.description = description;
        this.index = index;
        this.isTask = false;
        this.isMarking = true;
    }

    public void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (!isTask && !isMarking) {
            if (this.type.equals("bye")) {
                bye();
            } else if (this.type.equals("list")) {
                tasks.list();
            }
        } else if (!isTask && isMarking) {
            if (this.type.equals("done")) {
                tasks.done(this.index, storage);
            } else if (this.type.equals("delete")) {
                tasks.delete(this.index, storage);
                tasks.printSize();
            }
        } else if (isTask && !isMarking) {
            Task t = new Task(description);
            if (this.type.equals("todo")) {
                t = new Todo(description);
                tasks.addTask(t);
            } else if (this.type.equals("deadline")) {
                checkDate("deadline");
                t = new Deadline(description);
                tasks.addTask(t);
            } else if (this.type.equals("event")) {
                checkDate("event");
                t = new Event(description);
                tasks.addTask(t);
            }
            storage.writeToFile(tasks.saveList());
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + t);
            tasks.printSize();
        }
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
*/