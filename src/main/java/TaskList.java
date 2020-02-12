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
    /**
     * Number of task in the list
     */
    private int size = 0;

    /**
     * Creates a TaskList object
     *
     * @param taskList List of task
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            this.taskList.add(taskList.get(i));
            this.size++;
        }
    }

    /**
     * Prints out all task in the list
     *
     * @return A String of elements in the tasklist
     */
    public String list() {
        //List out task
        String output = "";
        int num = this.taskList.size();
        for (int i = 0; i < num; i++) {
            output += (i + 1) + ". " + this.taskList.get(i) + "\n";
        }
        if (num == 0) {
            output += "\n You have no task!";
        } else {
            output += "\n You have " + Duke.pendingTask + " pending task!";
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
        try {
            if (taskNum <= this.size) {
                Task completedTask = this.taskList.get(taskNum - 1);
                if (completedTask.getStatus().equals("Done")) {
                    output+= "You have already completed this task!";
                } else {
                    completedTask.markAsDone();
                    Duke.pendingTask--;
                    output+= "Nice! I've marked this task as done:\n"
                            + "[" + completedTask.getStatus() + "] " + completedTask.getDescription();
                }
                if (Duke.pendingTask == 0) {
                    output+= "\nYay! You have no more task remaining!";
                } else {
                    output += "\nYou have " + Duke.pendingTask + " tasks remaining!";
                }
                return output;
            } else {
                return "Sorry, there is no such task!";
            }
        } catch (Exception e) {
            return "Sorry, I dont understand your request!";
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
        try {
            if (taskNum <= this.size) {
                Task deletedTask = this.taskList.get(taskNum - 1);
                String status = deletedTask.getStatus();
                assert status.equals("Done") || status.equals("Not Done") : "Only Done or Not Done";
                if (status.equals("Not Done")) {
                    //Pending task count drops only if deleted task not completed
                    Duke.pendingTask--;
                }
                output += "Noted. I've removed this task:\n" + deletedTask
                        + "\nNow you have " + Duke.pendingTask + " tasks in the list.";
                this.taskList.remove(taskNum - 1);
                this.size--;
                return output;
            } else {
                return "Sorry, there is no such task!";
            }
        } catch (Exception e) {
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
    public String addTask(String type, String input) throws Exception {
        try {
            Task task = taskConvertor(type, input);
            this.taskList.add(task);
            Duke.pendingTask++;
            this.size++;
            return "Got it. I've added the following task:\n" +
                    task + "\nYou now have " + Duke.pendingTask + " task in the list";
        } catch (Exception e){
            return "Invalid task request:(";
        }
    }

    public Task taskConvertor (String type, String input) throws Exception {
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
        for (int i = 0; i < this.size; i++) {
            String description = this.taskList.get(i).toString();
            if (description.contains(keyWord)) {
                output+= (i + 1) + "." + description + "\n";
                j++;
            }
        }
        if (j == 0) {
            output += "No task with such keyword:(";
        }
        return output;
    }

    public boolean containsDup (String taskName) throws Exception {
        for (int i = 0; i < this.size; i++) {
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
        int count = 0;
        for (int i = 0; i< taskList.size(); i++) {
            count++;
        }
        assert count == taskList.size() : "count represents size of taskList";
        return count;
    }
}
