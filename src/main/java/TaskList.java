import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public ArrayList<Task> taskArrList = new ArrayList<>();

    /**
     * loads saved date into list
     * @param xs - an list of String of input
     */
    public TaskList() {
    }

    public TaskList(List<String> xs) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        for(String input : xs) {
            String[] inputs = input.split(" - ");
            String command = inputs[0];
            String status = inputs[1];
            if (command.equals("D")) {
                LocalDate date = LocalDate.parse(inputs[3], formatter);
                Task t = new Deadline(inputs[2], date.toString());
                taskArrList.add(t);
                Task.taskCounter++;
                if (status.equals("1")) {
                    t.isDone = true;
                }
            } else if (command.equals("E")) {
                LocalDate date = LocalDate.parse(inputs[3], formatter);
                Task t = new Event(inputs[2], date.toString());
                taskArrList.add(t);
                Task.taskCounter++;
                if (status.equals("1")) {
                    t.isDone = true;
                }
            } else if (command.equals("T")) {
                Task t = new Todo(inputs[2]);
                taskArrList.add(t);
                Task.taskCounter++;
                if (status.equals("1")) {
                    t.isDone = true;
                }
            }
        }
    }

    public ArrayList<Task> getTaskArrList() {
        return this.taskArrList;
    }

    public void loadExistingList(List<String> xs) {

    }

    /**
     * Adds a task to the task array list
     */
    public void addTask(Task t) {
        taskArrList.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.toString());
        Task.taskCounter++;
        if (taskArrList.size() == 1) {
            System.out.println("Now you have " + taskArrList.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + taskArrList.size() + " tasks in the list.");
        }
    }

    /**
     * Prints out string representation of task array list
     */
    public String showTasks() {
        String output = "";
        if (taskArrList.size() == 1) {
            output += "Here is the task in your list:\n";
        } else if (taskArrList.size() == 0) {
            output += "You have no tasks in your list :(";
        } else {
            output += "Here are the tasks in your list:\n";
        }
        int i = 1;
        for (Task t : taskArrList) {
            if(i < taskArrList.size()) {
                output += ((i) + "." + taskArrList.get(i - 1).toString()) + "\n";
                i += 1;
            } else {
                output += ((i) + "." + taskArrList.get(i - 1).toString());
            }
        }
        return output;
    }

    /**
     * complete a task by changing boolean isDone to true
     * @param n - int representing task number
     */
    public String taskDone(int n) {
        String output = "";
        n -= 1;
        Task t = taskArrList.get(n);
        t.doTask();
        output += "Nice! I've marked this task as done: \n  [" + taskArrList.get(n).getStatusIcon() + "] " + taskArrList.get(n).getDescription();
        return output;
    }

    /**
     * removes task of index (n - 1) from task list
     * @param n - int representing task number in task list
     */
    public String deleteTask(int n) {
        String output = "";
        n -= 1;
        Task t = taskArrList.get(n);
        output += "Noted. I've removed this task:\n [" + t.getStatusIcon() + "] " + t.getDescription() + "\n";
        taskArrList.remove(n);
        Task.taskCounter--;
        if (taskArrList.size() == 1) {
            output += "Now you have " + taskArrList.size() + " task in the list.";
        } else {
            output += "Now you have " + taskArrList.size() + " tasks in the list.";
        }
        return output;
    }

    /**
     * displays all instances of tasks matching or containing user's search description
     * @param desc - a string representing partial/complete data of user's search
     */
    public String find(String desc) {
        String output = "";
        int counter = 1;
        for(Task t : taskArrList) {
            if(t.description.contains(desc)) {
                output += (counter + "." + t.toString() + "\n");
            }
            counter++;
        }
        return output.stripTrailing();
    }

    public String viewSchedule(String input) {
        String output = "";
        LocalDate date = LocalDate.parse(input);
        int counter = 1;
        for(Task t : taskArrList) {
            if(date.equals(t.getDate())) {
                output += (counter + "." + t.toString()) + "\n";
            }
            counter++;
        }
        return output.stripTrailing();
    }

    public String numOfTasks() {
        if(taskArrList.size() == 1) {
            return "Now you have " + taskArrList.size() + " task in the list.";
        } else {
            return "Now you have " + taskArrList.size() + " tasks in the list.";
        }
    }
}
