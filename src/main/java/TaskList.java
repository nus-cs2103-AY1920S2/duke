import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Represents a list of Task object
 */
public class TaskList {

    /** An list to store the task*/
    private ArrayList<Task> taskList;
    /** Number of task in the list*/
    private int size = 0;

    /**
     * Creates a TaskList object
     *
     * @param taskList List of task
     */
    public TaskList (ArrayList <Task> taskList) {
        this.taskList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            this.taskList.add(taskList.get(i));
            this.size++;
        }
    }

    /**
     * Prints out all task in the list
     */
    public void list () {
        //List out task
        int num = this.taskList.size();
        for (int i = 0; i < num; i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i));
        }
        if (num == 0) {
            System.out.println("You have no task!");
        }
    }

    /**
     * Marks the task that has been completed
     *
     * @param taskNum The task that has been done
     */
    public void done (int taskNum) {
        try {
//            int taskNum = Integer.parseInt(input.substring(5));
            if (taskNum <= this.size) {
                Task completedTask = this.taskList.get(taskNum - 1);
                if (completedTask.getStatus().equals("Done")) {
                    System.out.println("You have already completed this task!");
                } else {
//                    for (int i = 0; i < this.taskList.size(); i++) {
//                        Task savedTask = this.taskList.get(i);
//                        bw.write(savedTask.saveFile() + "\n");
//                    }
                    completedTask.markAsDone();
                    Duke.pendingTask--;
                }
                if (Duke.pendingTask == 0) {
                    System.out.println("Yay! You have no more task remaining!");
                } else {
                    System.out.println("You have " + Duke.pendingTask + " tasks remaining!");
                }
            } else {
                System.out.println("Sorry, there is no such task!");
            }
        } catch (Exception e) {
            System.out.println("Sorry, I dont understand your request!");
        }
    }

    /**
     * Removes task from the list
     *
     * @param taskNum Task to be removed
     */
    public void delete (int taskNum) {
        try {
            if (taskNum <= this.taskList.size()) {
                Task deletedTask = this.taskList.get(taskNum - 1);
                String status = deletedTask.getStatus();
                if (status.equals("Not Done")) {
                    //Pending task count drops only if deleted task not completed
                    Duke.pendingTask--;
                }
                System.out.println("Noted. I've removed this task:\n" + deletedTask
                        + "\nNow you have " + Duke.pendingTask + " tasks in the list.");
                this.taskList.remove(taskNum - 1);
            } else {
                System.out.println("Sorry, there is no such task!");
            }
        } catch (Exception e) {
            System.out.println("Sorry, there is no such task!");
        }
    }

    /**
     * Adds a new task into the list
     *
     * @param type Whether it is a todo,event of deadline task
     * @param input Describes the task to be added to the list
     */
    public void addTask (String type, String input) {
        if (type.equals ("T")) {
            try {
                String task1 = input.substring(5);
                if (task1.isEmpty()) {
                    System.out.println("OOOPS!! Cannot have empty todo request!!!");
                } else {
                    Todo todo = new Todo(task1);
                    this.taskList.add(todo);
                    Duke.pendingTask++;
                    System.out.println("Got it. I've added the following task:\n" +
                            todo + "\nYou now have " + Duke.pendingTask + " task in the list");
                }
            } catch (Exception e) {
                System.out.println("Huh? I do not understand this todo request:/");
            }
        } else if (type.equals ("D")) {
            //deadline request format: deadline<space><task></<yyyy-mm-dd>"
            try {
                int taskIndex = input.indexOf("/");
                int byIndex = taskIndex + 1;
                LocalDate date = LocalDate.parse(input.substring(byIndex));
                Deadline deadline = new Deadline(input.substring(9, taskIndex), date);
                this.taskList.add(deadline);
                Duke.pendingTask++;
                System.out.println("Got it. I've added the following task:\n" +
                        deadline + "\nYou now have " + Duke.pendingTask + " task in the list");
            } catch (Exception e) {
                System.out.println("Huh? This deadline request does not make sense");
            }
        } else if (type.equals ("E")) {
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
                System.out.println("Got it. I've added the following task:\n" +
                        event + "\nYou now have " + Duke.pendingTask + " task in the list");
            } catch (Exception e) {
                System.out.println("What? What event is this??");
            }
        }
    }

    /**
     * Prints out all task that contain a particular keyword
     *
     * @param keyWord keyword in task that you are looking for
     */
    public void find (String keyWord) {
        System.out.println("Here are the matching tasks in your list:\n");
        int j = 0;
        for (int i = 0; i < this.taskList.size(); i++) {
            String description = this.taskList.get(i).toString();
            if (description.contains (keyWord)) {
                System.out.println((i + 1) + "." + description);
                j++;
            }
        }
        if (j == 0) {
            System.out.println ("No task with such keyword:(");
        }
    }

    /**
     * Gives the arraylist storing all the task
     *
     * @return An arraylist of Task
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }
}
