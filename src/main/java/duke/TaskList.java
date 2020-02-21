package duke;

import java.security.spec.ECField;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Contains the task list which contains the tasks created by the user.
 */
public class TaskList {

    ArrayList<Task> tasks;
    String taskMessage;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskMessage = "";
    }

    /**
     * Displays all tasks in the task list.
     */
    protected void listTask() {
        taskMessage = "Here are the tasks in your list:\n";
        int i = 1;
        for (Task t : tasks) {
            this.taskMessage += i + "." + t + "\n";
            i++;
        }
    }

    /**
     * Marks the status of one or more tasks in the task list to be done.
     *
     * @param indexArr the array of the index of the tasks to be marked as done in the task list.
     */
    protected void markTaskDone(String[] indexArr) {
        taskMessage = "";
        String tempMessage = "Nice! I've marked the following task as done:\n";
        String errorMessage = "Task ";
        boolean hasValidIndex = false;
        boolean hasInvalidIndex = false;
        for (String str : indexArr) {
            int index = Integer.parseInt(str.trim());
            if (index > tasks.size() || index < 0) {
                hasInvalidIndex = true;
                errorMessage += index + " ";
                continue;
            }
            hasValidIndex = true;
            Task task = tasks.get(index - 1);
            task.isDone = true;
            tempMessage += task + "\n";
        }
        if (hasValidIndex)
            taskMessage = tempMessage;

        if (hasInvalidIndex)
            taskMessage += errorMessage + "not exist in the list.";
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param description the description of the new task.
     */
    protected void addToDo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        taskMessage = "Got it. I've added this task: \n";
        taskMessage += todo + "\n";
        taskMessage += "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Adds a new deadline task to the task list.
     *
     * @param description the description of the new task.
     * @param date the date of the new task.
     */
    protected void addDeadline(String description, LocalDate date) {
        Deadline deadline = new Deadline(description, date);
        tasks.add(deadline);
        taskMessage = "Got it. I've added this task: \n";
        taskMessage += deadline + "\n";
        taskMessage += "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Adds a new event task to the task list.
     *
     * @param description the description of the new task.
     * @param date the date of the new task.
     */
    protected void addEvent(String description, LocalDate date) {
        Event event = new Event(description, date);
        tasks.add(event);
        taskMessage = "Got it. I've added this task: \n";
        taskMessage += event + "\n";
        taskMessage += "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Removes one or more tasks from the task list.
     *
     * @param indexArr the array of the index of the tasks to be deleted from the task list.
     */
    protected void deleteTask(String[] indexArr) {
        taskMessage = "";
        ArrayList<Task> deleteTaskList = new ArrayList<>();
        String tempMessage = "Got it. I've removed the following tasks: \n";
        String errorMessage = "Task ";
        boolean hasValidIndex = false;
        boolean hasInvalidIndex = false;
        for (String str : indexArr) {
            int index = Integer.parseInt(str.trim());
            if (index > tasks.size() || index < 0) {
                hasInvalidIndex = true;
                errorMessage += index + " ";
                continue;
            }
            hasValidIndex = true;
            Task task = tasks.get(index - 1);
            deleteTaskList.add(task);
            tempMessage += task + "\n";
        }
        if (hasValidIndex) {
            for (Task task : deleteTaskList)
                tasks.remove(task);
            taskMessage = tempMessage;
        }
        if (hasInvalidIndex)
            taskMessage += errorMessage + "not exist in the list.\n";
        taskMessage += "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Searches the tasks that contains the keyword and displays these tasks.
     *
     * @param keyword the keyword used to search for the tasks.
     */
    protected void findTask(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keyword))
                result.add(task);
        }
        if (result.size() == 0) {
            taskMessage = "Sorry! I can't find any task with the keyword " + keyword + "\n";
            return;
        }
        taskMessage = "Here are the matching tasks in your list:\n";
        for (Task task : result) {
            taskMessage += task + "\n";
        }
    }
}

