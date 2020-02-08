package model;

import java.util.*;

public class TaskList implements Iterable<Task> {
    protected final List<Task> internalList = new ArrayList<Task>();

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

    public String add(Task task) {
        this.internalList.add(task);
        StringBuilder addTaskEnd = new StringBuilder("Now you have  tasks in the list.\n");

        String addTaskEndStr = addTaskEnd.insert(13, this.internalList.size()).toString();
        return addTaskStart + task.toString() + "\n" + addTaskEndStr;
    }

    public String remove(int position) {
        Task deletedTask = internalList.get(position);
        internalList.remove(position);
        return deleteTaskStart + " " + deletedTask.toString() + "\n" +
                "Now you have " + Integer.toString(this.internalList.size()) + " tasks in the list.\n";
    }

    public String markTaskAsDone(Integer position) {
        Task finishedTask = this.internalList.get(position);
        finishedTask.markAsDone();

        return taskCompleteCongrats + " " + finishedTask.toString() + "\n";
    }

    public String toString() {
        String listOverView = viewListGreeting;
        for (int i = 0; i < this.internalList.size(); i++) {
            if (this.internalList.get(i) == null) {
                continue;
            }
            listOverView = listOverView + Integer.toString(i + 1) + "."
                    + this.internalList.get(i).toString() + "\n";
        }
        return listOverView;
    }

    @Override
    public Iterator<Task> iterator() {
        return this.internalList.iterator();
    }
}
