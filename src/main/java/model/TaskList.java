package main.java.model;

import java.util.*;

import main.java.model.Task;

public class TaskList implements Iterable<Task> {
    protected final List<Task> internalList = new ArrayList<Task>();

    static String line = "____________________________________________________________\n";
    static String addTaskStart = "Got it. I've added this task:\n";
    static String deleteTaskStart = "Noted. I have removed this task:\n";
    static String viewListGreeting = "Here are the tasks in your list:\n";
    static String taskCompleteCongrats = "Nice! I've marked this task as done:\n";

    public TaskList() {
    }

    public TaskList(Task... tasks) {
        final List<Task> initials = Arrays.asList(tasks);
        internalList.addAll(initials);
    }

    public TaskList(Collection<Task> tasks) {
        internalList.addAll(tasks);
    }

    public void add(Task task) {
        this.internalList.add(task);
        StringBuilder addTaskEnd = new StringBuilder("Now you have  tasks in the list.\n");

        String addTaskEndStr = addTaskEnd.insert(13, this.internalList.size()).toString();
        System.out.println(line + addTaskStart + task.toString() + "\n" +
                addTaskEndStr + line);
    }

    public void remove(Integer position) {
        Task deletedTask = this.internalList.get(position);
        this.internalList.remove(position-1);
        System.out.println(line + deleteTaskStart + " " + deletedTask.toString() + "\n" +
                "Now you have " + Integer.toString(this.internalList.size()) + " tasks in the list.\n" + line);
    }

    public void markTaskAsDone(Integer position) {
        Task finishedTask = this.internalList.get(position);
        finishedTask.markAsDone();

        System.out.println(line + taskCompleteCongrats + " " + finishedTask.toString() + "\n" + line);
    }

    public String toString() {
        String listOverView = line + viewListGreeting;
        for (int i = 0; i < this.internalList.size(); i++) {
            if (this.internalList.get(i) == null) {
                continue;
            }
            listOverView = listOverView + Integer.toString(i + 1) + "."
                    + this.internalList.get(i).toString() + "\n";
        }
        listOverView = listOverView + line;
        return listOverView;
    }

    @Override
    public Iterator<Task> iterator() {
        return this.internalList.iterator();
    }
}
