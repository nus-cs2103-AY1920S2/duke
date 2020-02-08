import java.io.IOException;

public class AddCommand extends Command {

    public AddCommand(String command) {
        super(command);
    }

    boolean isExit() {
        return false;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String arr[] = command.split(" ", 2);
        String firstWord = arr[0];

        if (firstWord.equals("todo")) {
            if (arr.length > 1) { //check for errors

                //CALL TASKLIST: add task
                tasks.addTodo(arr[1]);
                //CALL UI: print output
                ui.printAddTask(tasks);
                //CALL STORAGE: write new file
                storage.addTodo(tasks);
            } else {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (firstWord.equals("deadline")) {
            if (arr.length > 1) { //check for errors
                //separate task and deadline
                String arr2[] = arr[1].split("/", 2);
                if (arr2.length > 1) { //check that deadline is specified
                    String arr3[] = arr2[1].split(" ", 2);

                    //CALL TASKLIST: add task
                    tasks.addDeadline(arr2[0], arr3[1]);
                    //CALL UI: print output
                    ui.printAddTask(tasks);
                    //CALL STORAGE: write new file
                    storage.addDeadline(tasks);
                } else {
                    throw new DukeException("☹ OOPS!!! Please specify the deadline.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (firstWord.equals("event")) {
            if (arr.length > 1) { //check for errors
                //separate task and time
                String arr2[] = arr[1].split("/", 2);
                if (arr2.length > 1) {
                    String arr3[] = arr2[1].split(" ", 2);

                    //CALL TASKLIST: add task
                    tasks.addEvent(arr2[0], arr3[1]);
                    //CALL UI: print output
                    ui.printAddTask(tasks);
                    //CALL STORAGE: write new file
                    storage.addEvent(tasks);
                } else {
                    throw new DukeException("☹ OOPS!!! Please specify event time.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        }

    }
}