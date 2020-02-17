import java.util.NoSuchElementException;
import java.util.Scanner;

public class newTaskAction implements Action {
    private String input;

    public newTaskAction(String input) {
        this.input = input;
    }

    /**
     * Attempts to create a new Task if possible and add it to the taskList if there is one
     *
     * @param tasks taskList to add Task to
     */
    public String doSomething(TaskList tasks) {
        try {
            Scanner sc = new Scanner(this.input);
            String command = sc.next();
            String taskDescription = "";
            try {
                taskDescription = sc.nextLine();
            } catch (NoSuchElementException e) {

            }
            Task currentTask = TaskHandler.taskHandler(command, taskDescription);
            return tasks.add(currentTask);
        } catch (InvalidInputException e) {
            return e.toString();
        }
    }

    public boolean hasNextAction() {
        return true;
    }
}
