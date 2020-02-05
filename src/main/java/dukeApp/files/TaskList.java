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
        Task t;
        System.out.println("Here are the tasks in your list:");
        for (int i=1; i <= aList.size(); i++) {
            t = aList.get(i-1);
            System.out.println(i + ". [" +t.getType()+ "][" +t.getStatusIcon()+ "]" +t.toString());
        }
        System.out.println("");
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
        Task t;
        System.out.println("Here are the matching tasks in your list:");
        for (int i=1; i <= matchTask.size(); i++) {
            t = matchTask.get(i-1);
            System.out.println(i + ". [" +t.getType()+ "][" +t.getStatusIcon()+ "]" +t.toString()+"\n");
        }
        System.out.println("");
    }

    //call to actions for different task type
    public void add(String taskType, String statement) {
        Task t;
        statement = statement.substring(statement.indexOf(" "));
        if (!taskType.equals("todo")) {
            String description = statement.split("\\(")[0];
            String date = statement.split("\\(")[1];
            String dateLine = date.substring(date.indexOf(" ") + 1, date.length()-1);
            String newDate = dateLine.split(" ")[0];
            String time = dateLine.split(" ")[1];
            try {
                checkDate(newDate);
                if (taskType.equals("event")) {
                    t = new Event(description, newDate, time);
                    aList.add(t);
                    printAdded(t, aList); //print msg task added
                } else {
                    t = new Deadline(description, newDate, time);
                    aList.add(t);
                    printAdded(t, aList); //print msg task added
                }
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
