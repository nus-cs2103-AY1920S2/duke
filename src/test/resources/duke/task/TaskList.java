package duke.task;

import java.util.ArrayList;

import duke.DukeException;

public class TaskList {

    private ArrayList<Task> storageData;
    private final static String LINE = "    ____________________________________________________________";
    private final static String LIST_HEADER = "     Here are the tasks in your list:";
    private final static String TASK_DONE_MESSAGE = "     Nice! I've marked this task as done:";
    private final static String TASK_ADD_MESSAGE = "     Got it. I've added this task:";
    private final static String DELETE_MESSAGE = "     Noted. I've removed this task:";

    public TaskList(ArrayList<Task> storageData) throws DukeException{
        this.storageData = storageData;
    }

    public ArrayList<Task> getData() {
        return this.storageData;
    }

    public void list() {
        System.out.println(LINE);
        System.out.println(LIST_HEADER);
        for (int i = 0; i < storageData.size(); i++) {
            String output = "     " + (i + 1) + "." + storageData.get(i);
            System.out.println(output);
        }
        System.out.println(LINE);
    }

    public void add(String type, String inputCommand) {

        try {
            
            if (type.equals("todo")) {
                Task new_Task = new Todo(type, inputCommand);
                this.storageData.add(new_Task);
            } else if (type.equals("deadline")) {
                Task new_Task = new Deadline(type, inputCommand);
                this.storageData.add(new_Task);
            } else {
                Task new_Task = new Event(type, inputCommand);
                this.storageData.add(new_Task);
            }
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        } catch (Exception e) {
            System.out.println(e);
        }

        // Putting the output string together
        String output = LINE + "\n" +
                TASK_ADD_MESSAGE + "\n" +
                "       " + this.storageData.get(this.storageData.size() - 1) + "\n";
        output += "     Now you have " + this.storageData.size() + " task(s) in the list." + "\n" + LINE;
        System.out.println(output);
    }

    public void delete(String position) {

        // Identify index to remove task from
        int indexPosition = Integer.parseInt(position);

        // Putting output string together
        try {
            String output = LINE + "\n" + DELETE_MESSAGE + "\n" + "       " + this.storageData.get(indexPosition - 1) + "\n";
            output += "     Now you have " + (this.storageData.size() - 1) + " task(s) in the list." + "\n" + LINE;
            System.out.println(output);
            this.storageData.remove(indexPosition - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINE + "\n     :(  OOPS! That number is not valid. You have " +
                    this.storageData.size() + " task(s) in your list." + "\n" + LINE);
        }
    }

    public void done(String position) {
        int task_Done = Integer.parseInt(position);

        // Checks the task as 'Done'
        this.storageData.get(task_Done - 1).taskDone();

        System.out.println(LINE);
        System.out.println(TASK_DONE_MESSAGE);
        System.out.println("       " + this.storageData.get(task_Done - 1));
        System.out.println(LINE);
    }
}
