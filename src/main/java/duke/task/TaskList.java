package duke.task;

import duke.DukeException;

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

    public void addTask(Task task) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        tasks.add(task);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int taskNo) throws DukeException {
        try {
            Task task = tasks.get(taskNo);
            tasks.remove(task);
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + task);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Do you have this task number?");
        }
    }
}
