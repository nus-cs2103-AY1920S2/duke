import java.util.List;

public class findAction implements Action {
    private String keyword;

    public findAction(String keyword) {
        this.keyword = keyword;
    }

    public String doSomething(TaskList tasks) {
        if (keyword.equals("")) {
            return "I need a keyword";
        }
        assert(!keyword.isEmpty());

        List<Task> tasksWithKeyword = tasks.find(keyword);
        String myResponse = "The tasks matching the keyword are:\n";
        int numTasks = 1;
        for (Task task : tasksWithKeyword) {
            myResponse += (numTasks + ". " + task + "\n");
            numTasks++;
        }
        return myResponse;

    }

    public boolean hasNextAction() {
        return true;
    }
}
