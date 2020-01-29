import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.tasks = lst;
    }

    public void doTask(int idx) {
        Task task = this.tasks.get(idx);
        task.setIsDone(true);
        Duke.printLines("Good job, you completed a task!\n" + "     " + task.toString());
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        Duke.printLines("Added: " + task.toString() + "\n     Now you have " + this.tasks.size() + " task(s) in the list.");
    }

    public void deleteTask(int idx) {
        try {
            Task task = this.tasks.get(idx);
            this.tasks.remove(idx);
            Duke.printLines("Noted. I've removed this task:\n     "
            + task.toString()
            + "\n     Now you have " + this.tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            Duke.printLines("Task index is invalid. Try again!");
        }
    }

    @Override
    public String toString() {
        String result = "";
        result += "    ____________________________________________________________";
        if (this.tasks.isEmpty()) {
            result += "\n     Sorry, your list is currently empty!";
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            String str = "\n     " + (i+1) + ".";
            str += task.toString();
            result += str;
        }
        result += "\n    ____________________________________________________________";
        return result;
    }
}