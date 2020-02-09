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
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        print(aList);
    }

    public void reminderList() {
        ArrayList<Task> reminder = new ArrayList<>();
        for (int i = 0; i < aList.size(); i++) {
            if ((aList.get(i).getType()).equals("D")) {
                reminder.add(aList.get(i));
            }
        }
        System.out.println("Here are the upcoming deadlines in your list:");
        print(reminder);
    }

    /**
     * Actions to be done for delete & done tasks
     * @param rank task number in the list
     * @param dd string indicating delete or done action
     */
    public void deleteDone(int rank, String dd) throws DukeException {
        Action action = new Action(rank, aList);

        if (!action.checkNum()) {
            throw new DukeException("No such task exist.\n");
        }
        else {
            if (dd.equals("delete")) {
                deleteAction(rank, aList);
            } else {
                doneAction(rank, aList);
            }
        }
    }

    /**
     * Call to action for the different task type
     * @param statement task description
     */
    public void find(String statement) {
        Find f = new Find(statement, aList);
        ArrayList<Task> matchTask = f.match();
        System.out.println("Here are the matching tasks in your list:");
        print(matchTask);
    }

    /**
     * Prints the corresponding list required
     * @param listOfTask the required list of task
     */
    private void print(ArrayList<Task> listOfTask){
        Task t;
        for (int i=1; i <= listOfTask.size(); i++) {
            t = listOfTask.get(i-1);
            System.out.println(i + ". [" +t.getType()+ "][" +t.getStatusIcon()+ "]" +t.toString());
        }
        System.out.println("");
    }

    //call to actions for different task type
    public void add(String taskType, String statement) {
        Task t;
        if (!taskType.equals("todo")) {
            ArrayList<String> details = breakStatement(statement);
            String description = details.get(0);
            String date = details.get(1);
            String time = details.get(2);
            try {
                checkDate(date);
                //print msg task added
                if (taskType.equals("event")) {
                    t = new Event(description, date, time);
                } else {
                    t = new Deadline(description, date, time);
                }
                aList.add(t);
                printAdded(t, aList); //print msg task added
            } catch(DukeException e) {
                System.out.println(e);
            }
        }
        else {
            t = new Todo(statement);
            aList.add(t);
            printAdded(t, aList); //print msg task added
        }
    }

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
     * Checks if the date string is in the correct format
     * @param date user input date
     * @throws DukeException if date is in wrong format
     */
    public void checkDate(String date) throws DukeException{
        if (!date.contains("-") || date.split("-")[0].length()<4
                || Integer.parseInt(date.split("-")[1])>12) {
            throw new DukeException("Invalid date format. Format should be YYYY-MM-DD.\n");
        }
    }

    /**
     * Actions to be executed if delete is called
     * @param rank task number in the list
     * @param tempList task list
     */
    public void deleteAction(int rank, ArrayList<Task> tempList) {
        Delete delete = new Delete(rank, tempList);
        System.out.println("Noted. I've removed this task:");
        System.out.println(delete);
        delete.deleteTask();
        System.out.println("Now you have " + tempList.size() + " tasks in the list.\n");
    }

    /**
     * Actions to be executed if done is called
     * @param rank task number in the list
     * @param tempList task list
     */
    public void doneAction(int rank, ArrayList<Task> tempList) {
        Done done = new Done(rank, tempList);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(done);
        done.markDone();
        System.out.println("Now you have " + tempList.size() + " tasks in the list.\n");
    }

    /**
     * Print statements when a new task is added
     * @param t the specific task in the list
     * @param tempList task list
     */
    public void printAdded(Task t, ArrayList<Task> tempList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [" + t.getType() + "][" + t.getStatusIcon() + "]" + t.toString());
        System.out.println("Now you have " + tempList.size() + " tasks in the list.\n");
    }
}
