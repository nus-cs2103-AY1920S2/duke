import java.util.ArrayList;

/**
 * Find Command allows a user to find tasks that are similar to the keyword specified.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Creates an instance find command to be executed.
     * @param response full instruction given by the user.
     * @param keyword keyword that was parsed by Parser class.
     */
    public FindCommand(String response, String keyword) {

        super(response);
        this.keyword = keyword;

    }

    /**
     * Executes the find command, allowing the program find the task with the keyword specified.
     * @param tasksStorage storage for the task, deals with storing data and retrieving data from hard disk.
     * @param taskList the list where all the tasks is being stored.
     * @param ui ui that is responsible for interaction with the user.
     * @throws DukeException if no item matching the keyword is found.
     */
    public void execute(Storage tasksStorage, TaskList taskList, Ui ui) throws DukeException {

        ArrayList<Task> foundTask = new ArrayList<>();
        ArrayList<Integer> taskNum = new ArrayList<>();



        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.toString().contains(this.keyword)) {
                taskNum.add(i);
                foundTask.add(task);
            }
        }

        if (foundTask.isEmpty()) {
            throw new DukeException("     ☹ OOPS!!! There is no such item in the list");
        }

        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < foundTask.size(); i++) {
            System.out.println("     " + (taskNum.get(i) + 1) + ". " + foundTask.get(i));
        }
    }

}
