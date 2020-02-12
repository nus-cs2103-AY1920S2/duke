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
        }
        return output;
    }

    /**
     * Marks the task that has been completed
     *
     * @param taskNum The task that has been done
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
     */
    public String delete(int taskNum) {
        String output = "";
        try {
            if (taskNum <= this.size) {
                Task deletedTask = this.taskList.get(taskNum - 1);
                String status = deletedTask.getStatus();
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
     */
    public String addTask(String type, String input) {
        if (type.equals("T")) {
            try {
                String task1 = input.substring(5);
                if (task1.isEmpty()) {
                    return "OOOPS!! Cannot have empty todo request!!!";
                } else {
                    Todo todo = new Todo(task1);
                    this.taskList.add(todo);
                    Duke.pendingTask++;
                    this.size++;
                    return "Got it. I've added the following task:\n" +
                            todo + "\nYou now have " + Duke.pendingTask + " task in the list";
                }
            } catch (Exception e) {
                return "Huh? I do not understand this todo request:/";
            }
        } else if (type.equals("D")) {
            //deadline request format: deadline<space><task></<yyyy-mm-dd>"
            try {
                int taskIndex = input.indexOf("/");
                int byIndex = taskIndex + 1;
                LocalDate date = LocalDate.parse(input.substring(byIndex));
                Deadline deadline = new Deadline(input.substring(9, taskIndex), date);
                this.taskList.add(deadline);
                Duke.pendingTask++;
                this.size++;
                return "Got it. I've added the following task:\n" +
                        deadline + "\nYou now have " + Duke.pendingTask + " task in the list";
            } catch (Exception e) {
                return "Huh? This deadline request does not make sense";
            }
        } else if (type.equals("E")) {
            //event request format: event<space><task></><yyyy-mm-dd><T><hh:mm-hh:mm>
            try {
                int taskIndex = input.indexOf("/");
                int atIndex = taskIndex + 1;
                int timeIndex = atIndex + 11;
                LocalDate date = LocalDate.parse(input.substring(atIndex, timeIndex - 1));
                LocalTime start = LocalTime.parse(input.substring(timeIndex, timeIndex + 5));
                LocalTime end = LocalTime.parse(input.substring(timeIndex + 6));
                Event event = new Event(input.substring(6, taskIndex), date, start, end);
                this.taskList.add(event);
                Duke.pendingTask++;
                this.size++;
                return "Got it. I've added the following task:\n" +
                        event + "\nYou now have " + Duke.pendingTask + " task in the list";
            } catch (Exception e) {
                return "What? What event is this??";
            }
        } else {
            return "I do not understand this request:/";
        }
    }

    /**
     * Prints out all task that contain a particular keyword
     *
     * @param keyWord keyword in task that you are looking for
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

    /**
     * Gives the arraylist storing all the task
     *
     * @return An arraylist of Task
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += taskList.get(i) + " ";
        }
        return output;
    }
}
