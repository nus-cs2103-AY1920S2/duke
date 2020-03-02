package tasks;

import exception.InvalidFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * A data structure serving Duke by storing a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;
    private boolean isLoadedList;
    private Stack<Task> deletedTasks = new Stack<>();

    /**
     * Constructor to create TaskList if we have a set of tasks for it.
     * @param tasks List of Task objects.
     * @param isLoadedList Boolean indicating if TaskList was loaded from a file.
     */
    public TaskList(List<Task> tasks, boolean isLoadedList) {
        this.tasks = tasks;
        this.isLoadedList = isLoadedList;
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
     * @return String representing task that was completed.
     */
    public String setDone(String num) {
        try {
            Task completedTask = this.tasks.get(Integer.parseInt(num) - 1);
            completedTask.isDone = true;
            return "Nice, I've marked this as done:\n" + completedTask;
        } catch (Exception e) {
            throw new InvalidFormatException("Enter \"done number\", make sure number exists in list!");
        }
    }

    /**
     * Sets the done attribute of a task to not done.
     * @param num String representing position of task in list.
     * @return String that reminds user a task needs to be done.
     */
    public String setNotDone(String num) {
        Task completedTask = this.tasks.get(Integer.parseInt(num) - 1);
        completedTask.isDone = false;
        return "You need to do this again:\n" + completedTask;
    }

    /**
     * Removes an existing task from our list.
     * @param num Position of a task in the list.
     * @return String representing deleted task.
     */
    public String deleteTask(String num) {
        try {
            Task deletedTask = this.tasks.remove(Integer.parseInt(num) - 1);
            deletedTasks.push(deletedTask);
            return "Deleted:\n" + deletedTask + "\nYou now have " + getTaskCount() + " tasks in the list";
        } catch (Exception e) {
            throw new InvalidFormatException("Enter \"delete number\", make sure number exists in list!");
        }
    }

    /**
     * Undoes the last deleted task by adding it back into tasks.
     * @return String saying task has been added back.
     */
    public String undoDelete() {
        Task restoredTask = deletedTasks.pop();
        tasks.add(restoredTask);
        return "I have restored:\n" + restoredTask;
    }

    /**
     * Prints all our existing tasks, and their details.
     * Behavior when tasks is empty depends on whether the list was created from loading file.
     * @return String representing all tasks in list.
     */
    public String getTasksAsString() {
        if (this.tasks.isEmpty()) {
            return (
                    isLoadedList
                    ? "Nothing at the moment. The sith control everything, you just don't know it."
                    : "No existing tasks has description that fits the keyword.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.tasks.size(); i++) {
                sb.append(String.valueOf(i + 1) + " " + this.tasks.get(i));
                if (i != this.tasks.size() - 1) {
                    sb.append("\n");
                }
            }
            return sb.toString();
        }
    }

    /**
     * Adds a new task based on instructions given.
     * @param instArr Array that represents the instruction.
     * @return String representing the added task.
     */
    public String addNewTask(String[] instArr) {
        List instList = Arrays.asList(instArr);
        String addTaskMessage = "";
        if (instArr[0].equals("todo")) {
            if (instArr.length == 1) {
                throw new InvalidFormatException("Description should not be empty");
            }
            String description = String.join(" ", Arrays.copyOfRange(instArr, 1, instArr.length));
            Task newTask = new ToDo(description);
            addTaskMessage = addTaskHelper(newTask);
        } else if (instArr[0].equals("deadline")) {
            // exception to handle non existence of /by and correspondingly /at
            int separator = instList.indexOf("/by");
            if (separator == -1) {
                throw new InvalidFormatException("correct format: deadline task /by date");
            } else if (separator == 1) {
                throw new InvalidFormatException("Description should not be empty");
            }
            String description =  String.join(" ", Arrays.copyOfRange(instArr, 1, separator));
            String dateTime = String.join(" ", Arrays.copyOfRange(instArr, separator + 1, instArr.length));
            try {
                LocalDateTime by = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                Task newTask = new Deadline(description, by);
                addTaskMessage = addTaskHelper(newTask);
            } catch (DateTimeParseException e) {
                throw new InvalidFormatException("Make sure you entered valid date: yyyy-MM-dd HH:mm");
            }
        } else if (instArr[0].equals("event")) {
            int separator = instList.indexOf("/at");
            if (separator == -1) {
                throw new InvalidFormatException("correct format: event task /at place");
            } else if (instArr.length == separator
                    || separator == 1) {
                throw new InvalidFormatException("Description and place cannot be empty");
            }
            String description =  String.join(" ", Arrays.copyOfRange(instArr, 1, separator));
            String at = String.join(" ", Arrays.copyOfRange(instArr, separator + 1, instArr.length));
            Task newTask = new Event(description, at);
            addTaskMessage = addTaskHelper(newTask);
        }
        return addTaskMessage;
    }

    private String addTaskHelper(Task addedTask) {
        this.tasks.add(addedTask);
        return "Added:\n" + addedTask + String.format("\nYou now have %d tasks in the list", getTaskCount());
    }

    /**
     * Returns a TaskList, tasks with description that contains keyword are included.
     * @param keyword String which we want to compare description against.
     * @return TaskList containing tasks that match.
     */
    public TaskList getListOfMatch(String keyword) {
        List<Task> tasks = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.getDescription().contains(keyword)) {
                tasks.add(task);
            }
        }
        return new TaskList(tasks, false);
    }
}
