import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> listOfTasks;
    int numOfTasks;


    public DukeList() {
        listOfTasks = new ArrayList<>();
        numOfTasks = 0;
    }

    /**
     * @param S Takes in a Task and adds it into the list of Tasks
     */
    public void addTask(Task S) {
        System.out.println("    Got it I've added this task:\n      " + S);
        listOfTasks.add(S);
        numOfTasks++;
        System.out.printf("    Now you have %d tasks in the list.\n", numOfTasks);
    }

    public void markTaskAsDone(int taskIndex) {
        Task curr = listOfTasks.get(taskIndex - 1);
        curr.done();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + curr);
    }

    public void view_task() {
        System.out.println("    Here are the tasks in your list:");
        for (int x = 0; x < numOfTasks; x++) {
            String output = String.format("    %d.%s", x + 1, listOfTasks.get(x).toString());
            System.out.println(output);
        }
    }
}
