package duke.pack;

import java.util.ArrayList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Creates a list command.
     */
    public ListCommand() {

    }

    /**
     * Executes the list command and prints all tasks from TaskList.
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }


    /**
     * Executes list command and gets response for the GUI
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     * @return String response for GUI
     */
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getList();
        String resp = "Here are your tasks: \n";

        for (int i = 1; i <= list.size(); i++) {
            resp = resp +  i + ". " + list.get(i - 1) + "\n";
        }

        return resp;
    }

    /**
     * Indicates whether command is exit.
     * @return boolean true if it is an exit command, else false
     */
    public Boolean isExit() {
        return false;
    }

}
