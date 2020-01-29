package tasks;

import exception.InvalidFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A data structure serving Duke by storing a list of tasks.
 */
public class TaskList {
    // contains the task list e.g., it has operations to add/delete tasks in the list
    private List<Task> tasks = new ArrayList<>(100);

    /**
     * Set our task to what was loaded from our file.
     * @param tasks Tasks that were loaded from file.
     */
    public void importTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a list of tasks.
     * @return Tasks.
     */
    public List<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in our list.
     * @return Number of tasks.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Marks a task as done.
     * @param num Position of a task in the list.
     */
    public void setDone(String num) {
        try {
            Task completedTask = this.tasks.get(Integer.parseInt(num) - 1);
            completedTask.isDone = true;
            System.out.print("Nice, I've marked this as done:");
            System.out.println(completedTask);
        } catch (Exception e) {
            throw new InvalidFormatException("Enter \"done number\", make sure number exists in list!");
        }
    }

    /**
     * Removes an existing task from our list.
     * @param num Position of a task in the list.
     */
    public void deleteTask(String num) {
        try {
            Task deletedTask = this.tasks.remove(Integer.parseInt(num) - 1);
            System.out.print("Deleted: ");
            System.out.println(deletedTask);
            System.out.format("You now have %d tasks in the list\n", getTaskCount());
        } catch (Exception e) {
            throw new InvalidFormatException("Enter \"delete number\", make sure number exists in list!");
        }
    }

    /**
     * Prints all our existing tasks, and their details.
     */
    public void printTaskList() {
        if (this.tasks.isEmpty()) {
            System.out.println("Nothing at the moment, you're all good.");
        } else {
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.format("%s." + this.tasks.get(i) + '\n', String.valueOf(i + 1));
            }
        }
    }

    /**
     * Adds a new task based on instructions given.
     * @param instArr Array that represents the instruction.
     */
    public void addNewTask(String[] instArr) {
        List instList = Arrays.asList(instArr);
        if (instArr[0].equals("todo")) {
            String description = String.join(" ", Arrays.copyOfRange(instArr, 1, instArr.length));
            Task newTask = new ToDo(description);
            addTaskHelper(newTask);
        } else if (instArr[0].equals("deadline")) {
            // exception to handle non existence of /by and correspondingly /at
            int seperator = instList.indexOf("/by");
            if (seperator == -1) {
                throw new InvalidFormatException("correct format: deadline task /by date");
            }
            String description =  String.join(" ", Arrays.copyOfRange(instArr, 1, seperator));
            String dateTime = String.join(" ", Arrays.copyOfRange(instArr, seperator + 1, instArr.length));
            try {
                LocalDateTime by = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                Task newTask = new Deadline(description, by);
                addTaskHelper(newTask);
            } catch (DateTimeParseException e) {
                throw new InvalidFormatException("Make sure you entered valid date: yyyy-MM-dd HH:mm");
            }
        } else if (instArr[0].equals("event")) {
            int separator = instList.indexOf("/at");
            if (separator == -1) {
                throw new InvalidFormatException("correct format: event task /at place");
            }
            String description =  String.join(" ", Arrays.copyOfRange(instArr, 1, separator));
            String at = String.join(" ", Arrays.copyOfRange(instArr, separator + 1, instArr.length));
            Task newTask = new Event(description, at);
            addTaskHelper(newTask);
        }
    }

    private void addTaskHelper(Task addedTask) {
        this.tasks.add(addedTask);
        System.out.print("Added: ");
        System.out.println(addedTask);
        System.out.format("You now have %d tasks in the list\n", getTaskCount());
    }
}
