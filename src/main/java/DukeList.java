import DukeExceptions.EmptyListException;
import DukeExceptions.InvalidEntryException;

import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> listOfTasks;
    private DukeStorage storage;
    int numOfTasks;


    public DukeList() {
        listOfTasks = new ArrayList<>();
        numOfTasks = 0;
        storage = new DukeStorage();
    }

    /**
     * @param S Takes in a Task and adds it into the list of Tasks
     */
    public void addTask(Task S) {
        System.out.println("    Got it I've added this task:\n      " + S);
        listOfTasks.add(S);
        numOfTasks++;
        System.out.printf("    Now you have %d tasks in the list.\n", numOfTasks);
        storage.save(this);
    }

    public void markTaskAsDone(int taskIndex) throws EmptyListException, InvalidEntryException{
        if(numOfTasks == 0) {
            throw new EmptyListException("List is empty! Which task can you complete?!");
        } else if(taskIndex > numOfTasks) {
            String exceptionOutput = String.format("List only has %d item(s), how to complete item no. %d", numOfTasks, taskIndex);
            throw new InvalidEntryException(exceptionOutput);
        } else if(taskIndex < 1) {
            throw new InvalidEntryException("Can only complete positive indexes!");
        } else {
            Task curr = listOfTasks.get(taskIndex - 1);
            curr.done();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("    " + curr);
        }
    }

    public void view_task() {
        System.out.println("    Here are the tasks in your list:");
        for (int x = 0; x < numOfTasks; x++) {
            String output = String.format("    %d.%s", x + 1, listOfTasks.get(x).toString());
            System.out.println(output);
        }
    }

    public void delete_task(int index) throws InvalidEntryException, EmptyListException {
        if(numOfTasks == 0) {
            throw new EmptyListException("List is empty! How to delete?!");
        } else if(index > numOfTasks) {
            String exceptionOutput = String.format("List only has %d item(s), how to delete item no. %d", numOfTasks, index);
            throw new InvalidEntryException(exceptionOutput);
        } else if(index < 1) {
            throw new InvalidEntryException("Can only remove positive indexes!");
        } else{
            Task removedTask = listOfTasks.remove(index - 1);
            numOfTasks--;
            System.out.println("    The task requested has been successfully removed:");
            System.out.println("      " + removedTask);
            if(numOfTasks == 1) {
                System.out.println("    There is " + numOfTasks + " task left.");
            } else {
                System.out.println("    There are " + numOfTasks + " tasks left.");
            }
        }
    }

    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }
}
