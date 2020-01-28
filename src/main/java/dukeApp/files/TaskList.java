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

    //prints task in the list
    public void printList() {
        Task t;
        System.out.println("Here are the tasks in your list:");
        for (int i=1; i <= aList.size(); i++) {
            t = aList.get(i-1);
            System.out.println(i + ". [" +t.getType()+ "][" +t.getStatusIcon()+ "]" +t.getTask());
        }
        System.out.println();
    }

    //for delete and done task
    public void deleteDone(int rank, String dd) {
        Action action = new Action(rank, aList);

        if (action.checkNum() == 0) {
            DukeException error = new DukeException();
            System.out.println(error.outOfBound());
            System.out.println("");
        }
        else {
            if (dd.equals("delete")) {
                deleteAction(rank, aList);
            } else {
                doneAction(rank, aList);
            }
        }
    }

    public void find(String statement) {
        Find f = new Find(statement, aList);
        ArrayList<Task> matchTask = f.match();
        Task t;
        System.out.println("Here are the matching tasks in your list:");
        for (int i=1; i <= matchTask.size(); i++) {
            t = matchTask.get(i-1);
            System.out.println(i + ". [" +t.getType()+ "][" +t.getStatusIcon()+ "]" +t.getTask());
        }
        System.out.println();
    }

    //call to actions for different task type
    public void add(String taskType, String statement) {
        Task t;
        statement = statement.substring(statement.indexOf(" "));
        if (!taskType.equals("todo")) {
            if (taskType.equals("event")) {
                t = new Event(statement);
            } else {
                t = new Deadline(statement);
            }
        }
        else {
            t = new Todo(statement);
        }
        aList.add(t);
        printAdded(t, aList); //print msg task added
    }

    public void deleteAction(int rank, ArrayList<Task> tempList) {
        Delete delete = new Delete(rank, tempList);
        String deleteAction = delete.printAction();
        System.out.println(delete.deleteTask());
        System.out.println(deleteAction);
        System.out.println("Now you have " + tempList.size() + " tasks in the list.");
        System.out.println("");
    }

    public void doneAction(int rank, ArrayList<Task> tempList) {
        Done done = new Done(rank, tempList);
        System.out.println(done.markDone());
        System.out.println(done.printAction());
        System.out.println("Now you have " + tempList.size() + " tasks in the list.");
        System.out.println("");
    }

    public void printAdded(Task t, ArrayList<Task> tempList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [" + t.getType() + "][" + t.getStatusIcon() + "]" + t.getTask());
        System.out.println("Now you have " + tempList.size() + " tasks in the list.");
        System.out.println();
    }
}
