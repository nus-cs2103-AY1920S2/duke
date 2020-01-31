import java.util.List;

public class findAction implements Action {
    public void doSomething(TaskList tasks) {
        String keyword = GlobalScanner.sc.next();
        List<Task> tasksWithKeyword = tasks.find(keyword);
        System.out.println("The tasks matching the keyword are:");
        int numTasks = 1;
        for(Task task : tasksWithKeyword) {
            System.out.println(numTasks + ". " + task);
            numTasks++;
        }

    }

    public boolean hasNextAction() {
        return true;
    }
}
