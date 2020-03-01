import java.util.ArrayList;

/**
 * CS2103 Individual Project.
 * The TaskList class is to encapsulates the methods of task operations. It contains an
 * ArrayList of Task.
 * @author Chiang Shun De
 */

public class TaskList {

    private ArrayList<Task> taskArray;

    /**
     * Constructor method to create an instance of TaskList using an existing ArrayList of Tasks.
     * @param taskArray ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    /**
     * Constructor method to create an instance of TaskList.
     */
    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

    /**
     * Prints out all the tasks in the ArrayList in a readable format.
     */
    public void printlist(){
        int index = 1;
        for (Task t : this.taskArray) {
            System.out.println(index + "." + t);
            index++;
        }
    }

    /**
     * Adds a Task into the ArrayList based on user input and type of task.
     * @param command The type of task to add into the ArrayList.
     * @param description The description of the task to add into the ArrayList.
     * @return A new TaskList based on the modified ArrayList.
     */
    public TaskList addTask (String command, String description) {
        if (command.equals("deadline") && description.contains("/by")) {
            if (description.split(" /by ")[1].split(" ").length == 1) {
                String date = description.split(" /by ")[1].split(" ")[0];
                if (date.contains("/")) {
                    String day = date.split("/")[0];
                    String month = date.split("/")[1];
                    String year = date.split("/")[2];
                    date = year + "-" + month + "-" + day;
                }
                this.taskArray.add(new Deadline(description.split(" /by ")[0], date));
            } else {
                String date = description.split(" /by ")[1].split(" ")[0];
                String time = description.split(" /by ")[1].split(" ")[1];
                if (date.contains("/")) {
                    String day = date.split("/")[0];
                    String month = date.split("/")[1];
                    String year = date.split("/")[2];
                    date = year + "-" + month + "-" + day;
                }
                this.taskArray.add(new Deadline(description.split(" /by ")[0], date, time));
            }
        } else if (command.startsWith("todo")) {
            this.taskArray.add(new Todo(description));
        } else if (command.startsWith("event")) {
            this.taskArray.add(new Event(description.split("/at")[0], description.split("/at")[1]));
        }
        return new TaskList(taskArray);
    }

    /**
     * Deletes a task in the ArrayList based on user input.
     * @param target The position chosen by the user.
     * @return A new TaskList created from the modified ArrayList.
     */
    public TaskList deleteTask (int target) {
        this.taskArray.remove(target);
        return new TaskList(taskArray);
    }

    /**
     * Changes the completion status of the chosen task to completed, based on user input.
     * @param target The position chosen by the user.
     * @return A new TaskList with the modified ArrayList.
     */
    public TaskList markDone(int target) {
        this.taskArray.get(target).markDone();
        return new TaskList(this.taskArray);
    }

    /**
     * Searches for tasks containing the user input keyword.
     * @param keyWord The user input to search for tasks containing this word.
     */
    public void searchTask(String keyWord) {
        int index = 1;
        for (Task t: this.taskArray) {
            if (t.getDescription().contains(keyWord)){
                System.out.println(index + ". " + t) ;
                index++;
            }
        }
    }


    /**
     * Mass deletes tasks containing the user input keyword.
     * @param keyWord The user input to search for tasks containing this word.
     * @return A new TaskList created from the modified ArrayList with the required tasks deleted.
     */
    public TaskList massDelete(String keyWord) {
        ArrayList<Task> temp = new ArrayList<>();
        if (keyWord.equals("all")){
            this.taskArray.clear();
        } else {
            for (Task t: this.taskArray) {
                if (t.getDescription().contains(keyWord)) {
                    System.out.println(t);
                    temp.add(t);
                }
            }
            this.taskArray.removeAll(temp);
        }
        return new TaskList(this.taskArray);
    }

    /**
     * Mass mark tasks containing the user input keyword as done.
     * @param keyWord The user input to search for tasks containing this word.
     * @return A new TaskList created from the modified ArrayList with the required tasks marked as done.
     */
    public TaskList massDone(String keyWord) {
        if (keyWord.equals("all")) {
            for (Task t : this.taskArray) {
                t.markDone();
            }
        } else {
            for (Task t: this.taskArray) {
                if (t.getDescription().contains(keyWord)) {
                    t.markDone();
                    System.out.println(t);
                }
            }
        }
        return new TaskList(this.taskArray);
    }

    /**
     * Returns the task chosen by the user.
     * @param position The position of the chosen task.
     * @return The chosen task.
     */
    public Task get(int position) {
        return this.taskArray.get(position);
    }

    /**
     * Method to return the number of tasks present in the ArrayList.
     * @return the number of tasks inside the ArrayList.
     */
    public int getSize(){
        return this.taskArray.size();
    }

    /**
     * Accesses the ArrayList of tasks.
     * @return the ArrayList of tasks.
     */
    public ArrayList<Task> getTaskArray() {
        return this.taskArray;
    }

}
