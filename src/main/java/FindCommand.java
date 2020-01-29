
import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String response, String keyword) {

        super(response);
        this.keyword = keyword;

    }

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
