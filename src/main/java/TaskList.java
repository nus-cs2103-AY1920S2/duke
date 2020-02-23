import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Represents a list of Task object
 */
public class TaskList {

    /**
     * An list to store the task
     */
    private ArrayList<Task> taskList;

    private int pendingTask;

    /**
     * Creates a TaskList object
     *
     * @param taskList List of task
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task taskToBeAdded = taskList.get(i);
            this.taskList.add(taskToBeAdded);
            if (taskToBeAdded.getStatus().equals ("Not Done")) {
                this.pendingTask++;
            }
        }
    }

    public String commands() {
        return "Command list" +
                "\n\n_______________________________" +
                "\n\n 1) list: Returns list of all tasks" +
                "\n\n 2) clear list: Clears list" + "" +
                "\n\n 3) done: Marks a task as done" +
                "\n\n 4) find: Returns all tasks that contains keyword" +
                "\n\n 5) delete: Deletes tasks" +
                "\n\n 6) todo: Adds a new task to the list" +
                "\n\n 7) deadline: Adds a new deadline task to the list" +
                "\n\n 8) event: Adds a new event task to the list";
    }

    /**
     * Prints out all task in the list
     *
     * @return A String of elements in the tasklist
     */
    public String list() {
        //List out task
        String output = "";
        int num = taskList.size();
        for (int i = 0; i < num; i++) {
            output += (i + 1) + ". " + this.taskList.get(i) + "\n";
        }
        if (num == 0) {
            output += "\n You have no task!";
        } else {
            output += "\n You have " + this.pendingTask + " pending task!";
        }
        return output;
    }

    /**
     * Marks the task that has been completed
     *
     * @param taskNum The task that has been done
     * @return A string to be printed on duke application GUI to indicated status of done task
     */
    public String done(int taskNum) {
        String output = "";
        if (taskNum <= taskList.size()) {
            Task completedTask = this.taskList.get(taskNum - 1);
            if (completedTask.getStatus().equals("Done")) {
                output+= "You have already completed this task!";
            } else {
                completedTask.markAsDone();
                this.pendingTask--;
                output+= "Nice! I've marked this task as done:\n"
                        + "[" + completedTask.getStatus() + "] " + completedTask.getDescription();
            }
            if (this.pendingTask == 0) {
                output+= "\nYay! You have no more task remaining!";
            } else {
                output += "\nYou have " + this.pendingTask + " tasks remaining!";
            }
            return output;
        } else {
            return "Sorry, there is no such task!";
        }
    }

    /**
     * Removes task from the list
     *
     * @param taskNum Task to be removed
     * @return A string to be printed on duke application GUI to indicate status of deleted task
     */
    public String delete(int taskNum) {
        String output = "";
        if (taskNum <= taskList.size()) {
            Task deletedTask = this.taskList.get(taskNum - 1);
            String status = deletedTask.getStatus();
            assert status.equals("Done") || status.equals("Not Done") : "Only Done or Not Done";
            if (status.equals("Not Done")) {
                //Pending task count drops only if deleted task not completed
                this.pendingTask--;
            }
            output += "Noted. I've removed this task:\n" + deletedTask
                    + "\nNow you have " + this.pendingTask + " tasks in the list.";
            this.taskList.remove(taskNum - 1);
            return output;
        } else {
            return "Sorry, there is no such task!";
        }
    }

    /**
     * Adds a new task into the list
     *
     * @param type  Whether it is a todo,event of deadline task
     * @param input Describes the task to be added to the list
     * @return A string to be printed out on duke application GUI on the status of the added task
     */
    public String addTask(String type, String input) {
        Task task = taskConvertor(type, input);
        if (task.getDescription().equals("HELP THIS IS WRONG!")) {
            return "Invalid Task Request:(";
        }
        this.taskList.add(task);
        this.pendingTask++;
        return "Got it. I've added the following task:\n" +
                task + "\nYou now have " + this.pendingTask + " task in the list";

    }

    public Task taskConvertor (String type, String input) {
        try {
            if (type.equals("T")) {
                String task1 = input.substring(5);
                Todo todo = new Todo(task1);
                return todo;
            } else if (type.equals("D")) {
                int taskIndex = input.indexOf("/");
                int byIndex = taskIndex + 1;
                LocalDate date = LocalDate.parse(input.substring(byIndex));
                Deadline deadline = new Deadline(input.substring(9, taskIndex), date);
                return deadline;
            } else if (type.equals("E")) {
                int taskIndex = input.indexOf("/");
                int atIndex = taskIndex + 1;
                int timeIndex = atIndex + 11;
                LocalDate date = LocalDate.parse(input.substring(atIndex, timeIndex - 1));
                LocalTime start = LocalTime.parse(input.substring(timeIndex, timeIndex + 5));
                LocalTime end = LocalTime.parse(input.substring(timeIndex + 6));
                Event event = new Event(input.substring(6, taskIndex), date, start, end);
                return event;
            } else {
                return null;
            }
        } catch (StringIndexOutOfBoundsException e) {
            return new Todo("HELP THIS IS WRONG!");
        }
    }


    /**
     * Prints out all task that contain a particular keyword
     *
     * @param keyWord keyword in task that you are looking for
     * @return A string to be printed on duke application GUI to indicate the task that matches the keyword
     */
    public String find(String keyWord) {
        String output = "";
        output += "Here are the matching tasks in your list:\n";
        int j = 0;
        for (int i = 0; i < taskList.size(); i++) {
            String task = this.taskList.get(i).toString();
            int indexOfDescription = task.indexOf("Done]") + 6;
            String description = task.substring(indexOfDescription);
            if (description.contains(keyWord)) {
                output+= (i + 1) + "." + task + "\n";
                j++;
            }
        }
        if (j == 0) {
            output += "No task with such keyword:(";
        }
        return output;
    }

    public boolean containsDup (String taskName) {
        for (int i = 0; i < taskList.size(); i++) {
            String description = this.taskList.get(i).toString();
            String task;
            if (taskName.contains("todo")) {
                 task = taskConvertor("T", taskName).toString();
            } else if (taskName.contains ("deadline")) {
                 task = taskConvertor ("D",taskName).toString();
            } else {
                 task = taskConvertor ("E",taskName).toString();
            }
            if (description.equals(task)) {
                return true;
            }
        }
        return false;
    }

    public String clearList() {
        int size  = taskList.size();
        for (int i = 0; i < size; i++) {
            taskList.remove(0);
        }
        this.pendingTask = 0;
        return "Done! You have no more task!";
    }

    /**
     * Gives the arraylist storing all the task
     *
     * @return An arraylist of Task
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Replaces .size() of arrayList to work around private access issues
     *
     * @return size of tasklist
     */
    public int size() {
        return taskList.size();
    }
}
