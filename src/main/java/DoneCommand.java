import java.io.IOException;

public class DoneCommand extends Command {

    public DoneCommand(String command) {
        super(command);
    }

    boolean isExit() {
        return false;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String arr[] = command.split(" ", 2);
        String firstWord = arr[0];

        if (arr.length > 1) { //check for errors
            //which task done
            String secNum = arr[1];
            int doneTask = Integer.parseInt(secNum) - 1;

            //CALL TASKLIST: set that task to done
            tasks.setDone(doneTask);
            //CALL UI: print output
            ui.printDone(tasks, doneTask);
            //CALL STORAGE: write new file
            storage.updateTask(doneTask, tasks);

        } else {
            throw new DukeException("☹ OOPS!!! Please specify which task you've done.");
        }
    }
}