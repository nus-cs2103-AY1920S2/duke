import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> storageData;
    String line = "    ____________________________________________________________";
    String list_Header = "     Here are the tasks in your list:";
    String task_Done_Message = "     Nice! I've marked this task as done:";
    String task_Add_Message = "     Got it. I've added this task:";
    String delete_Message = "     Noted. I've removed this task:";

    public TaskList(ArrayList<Task> storageData) throws DukeException{
        this.storageData = storageData;
    }

    public ArrayList<Task> getData() {
        return this.storageData;
    }

    public void list() {
        System.out.println(line);
        System.out.println(list_Header);
        for (int i = 0; i < storageData.size(); i++) {
            String output = "     " + (i + 1) + "." + storageData.get(i);
            System.out.println(output);
        }
        System.out.println(line);
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
        } catch(DukeException dukeException) {
            System.out.println(dukeException);
        } catch (Exception e) {
            System.out.println(e);
        }

        // Putting the output string together
        String output = line + "\n" +
                task_Add_Message + "\n" +
                "       " + this.storageData.get(this.storageData.size() - 1) + "\n";
        output += "     Now you have " + this.storageData.size() + " task(s) in the list." + "\n" + line;
        System.out.println(output);
    }

    public void delete(String position) {

        // Identify index to remove task from
        int indexPosition = Integer.parseInt(position);

        // Putting output string together
        try {
            String output = line + "\n" + delete_Message + "\n" + "       " + this.storageData.get(indexPosition - 1) + "\n";
            output += "     Now you have " + (this.storageData.size() - 1) + " task(s) in the list." + "\n" + line;
            System.out.println(output);
            this.storageData.remove(indexPosition - 1);
        } catch(IndexOutOfBoundsException e) {
            System.out.println(line + "\n     :(  OOPS! That number is not valid. You have " +
                    this.storageData.size() + " task(s) in your list." + "\n" + line);
        }
    }

    public void done(String position) {
        int task_Done = Integer.parseInt(position);

        // Checks the task as 'Done'
        this.storageData.get(task_Done - 1).taskDone();

        System.out.println(line);
        System.out.println(task_Done_Message);
        System.out.println("       " + this.storageData.get(task_Done - 1));
        System.out.println(line);
    }
}
