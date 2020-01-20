import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> listOfTasks;

    public DukeList() {
        listOfTasks = new ArrayList<>();
    }

    public void add_task(Task S) {
        System.out.println("    Added:" + S);
        listOfTasks.add(S);
    }

    public void markTaskAsDone(int taskIndex) {
        Task curr = listOfTasks.get(taskIndex - 1);
        curr.done();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println(curr);
    }

    public void view_task() {
        int numOfTasks = listOfTasks.size();
        System.out.println("    Here are the tasks in your list:");
        for (int x = 0; x < numOfTasks; x++) {
            String output = String.format("    %d.%s", x + 1, listOfTasks.get(x).toString());
            System.out.println(output);
        }
    }
}
