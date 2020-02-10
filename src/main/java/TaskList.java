import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public String addTask(Task task, Storage storage) {
        tasks.add(task);
        String addMessage = "Got it. I've added this task:\n" + task
                + String.format("\nnow you have %d tasks in the list.\n", tasks.size());
        storage.updateTaskData(this);
        return addMessage;
    }

    public String deleteTask(int index, Storage storage) {
        Task task = getTask(index);
        tasks.remove(task);
        String deleteMessage = "Noted. I've removed this task:\n" + task
                + String.format("\nnow you have %d tasks in the list.\n", tasks.size());
        storage.updateTaskData(this);
        return deleteMessage;
    }

    public String listTask() {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("There is currently no pending task. " +
                    "Please add some tasks by using the \"todo\", \"event\" or \"deadline\" command\n");
        } else {
            int index = 0;
            sb.append("Here are all the tasks in your list:\n");
            for (Task task : tasks) {
                sb.append(String.format("%d. ", ++index)).append(task.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public String doneTask(int index, Storage storage) {
        Task task = getTask(index);
        task.markAsDone();
        String doneMessage = "Nice! Congratulations for completing this task:\n" + task + "\n";
        storage.updateTaskData(this);
        return doneMessage;
    }
}
