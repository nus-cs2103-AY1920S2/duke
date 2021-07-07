package dukeApp.files;

import dukeApp.action.Action;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> aList;

    public TaskList(ArrayList<Task> aList) {
        this.aList = aList;
    }

    public TaskList() {}

    public int getSize() {
        return aList.size();
    }

    /**
     * Prints task in the list
     * @return message to list task
     */
    public String printList() {
        return "Here are the tasks in your list:\n" + print(aList);
    }

    /**
     * Prints reminders for deadlines in the list
     * @return message to show reminders
     */
    public String reminderList() {
        ArrayList<Task> reminderList = new ArrayList<>();
        for (int i = 0; i < aList.size(); i++) {
            if ((aList.get(i).getType()).equals("D") && (aList.get(i).getStatusIcon()).equals("\u2718")) {
                reminderList.add(aList.get(i));
            }
        }
        if (reminderList.size() != 0) {
            return "Here are the upcoming deadlines in your list:\n" + print(reminderList);
        }
        else {
            return "There are no upcoming deadlines.";
        }
    }

    /**
     * Actions to be done for delete & done tasks
     * @param rank task number in the list
     * @param dd string indicating delete or done action
     * @return message to show a task has been marked as done or deleted
     */
    public String deleteDone(int rank, String dd) throws DukeException {
        Action action = new Action(rank, aList);

        if (!action.checkNum()) {
            return new DukeException("No such task exist.\n").toString();
        }
        else {
            if (dd.equals("delete")) {
                return deleteAction(rank, aList);
            } else {
                return doneAction(rank, aList);
            }
        }
    }

    /**
     * Call to action for the different task type
     * @param statement task description
     * @return message to show matching tasks
     */
    public String find(String statement) {
        Find f = new Find(statement, aList);
        ArrayList<Task> matchTask = f.match();
        if (matchTask.size() > 0) {
            return "Here are the matching tasks in your list:\n" + print(matchTask);
        }
        else {
            return "There are no matching tasks in your list.";
        }
    }

    /**
     * Prints the corresponding list required
     * @param listOfTask the required list of task
     * @return list of tasks
     */
    private String print(ArrayList<Task> listOfTask){
        Task t;
        String msg = "";
        for (int i=1; i <= listOfTask.size(); i++) {
            t = listOfTask.get(i-1);
            msg += i + ". [" +t.getType()+ "][" +t.getStatusIcon()+ "]" +t.toString() + "\n";
        }
        return msg + "\n";
    }

    /**
     * Add a new task to the list
     * @param taskType the type of task to be added (todo, event, deadline)
     * @param statement task description
     * @return message to show successful adding of task
     */
    public String add(String taskType, String statement) {
        Task t;
        String msg = "";
        if (!taskType.equals("todo")) {
            ArrayList<String> details = breakStatement(statement);
            String description = details.get(0);
            String date = details.get(1);
            String time = details.get(2);
            try {
                if (!date.contains("-") || date.split("-")[0].length()<4
                        || Integer.parseInt(date.split("-")[1])>12) {
                    throw new DukeException("Invalid date format. Format should be YYYY-MM-DD.\n");
                }
                //print msg task added
                if (taskType.equals("event")) {
                    t = new Event(description, date, time);
                } else {
                    t = new Deadline(description, date, time);
                }
                aList.add(t);
                msg = printAdded(t, aList); //print msg task added
            } catch(DukeException e) {
                msg = e.toString();
            }
        }
        else {
            t = new Todo(statement);
            aList.add(t);
            msg = printAdded(t, aList); //print msg task added
        }
        return msg;
    }

    /**
     * To breakdown the task description stated by user into parts
     * @param s task description
     * @return an arraylist containing the details splitted
     */
    public ArrayList<String> breakStatement(String s) {
        ArrayList<String> details = new ArrayList<>();
        details.add(s.split("\\(")[0]); //store description string
        String deadline = s.split("\\(")[1];
        String dateStr = deadline.substring(4, deadline.length()-1); //includes date and time
        details.add(dateStr.split(" ")[0]); //store date string
        details.add(dateStr.split(" ")[1]); //store time string

        return details;
    }

    /**
     * Actions to be executed if delete is called
     * @param rank task number in the list
     * @param tempList task list
     * @return the message to show task successfully deleted
     */
    public String deleteAction(int rank, ArrayList<Task> tempList) {
        String msg = "";
        Delete delete = new Delete(rank, tempList);
        msg += "Noted. I've removed this task:\n";
        msg += delete;
        delete.deleteTask();
        return msg += "\nNow you have " + tempList.size() + " tasks in the list.\n";
    }

    /**
     * Actions to be executed if done is called
     * @param rank task number in the list
     * @param tempList task list
     * @return message to show task successfully marked as done
     */
    public String doneAction(int rank, ArrayList<Task> tempList) {
        String msg = "";
        Done done = new Done(rank, tempList);
        msg += "Nice! I've marked this task as done:\n";
        done.markDone();
        msg += done;
        return msg += "\nNow you have " + tempList.size() + " tasks in the list.\n";
    }

    /**
     * Print statements when a new task is added
     * @param t the specific task in the list
     * @param tempList task list
     * @return message showing successful adding of task
     */
    public String printAdded(Task t, ArrayList<Task> tempList) {
        return "Got it. I've added this task:\n"
                + "  [" + t.getType() + "][" + t.getStatusIcon() + "]" + t.toString()
                + "\nNow you have " + tempList.size() + " tasks in the list.\n";
    }
}
