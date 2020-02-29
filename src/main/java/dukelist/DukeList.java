package dukelist;

import dukeexceptions.EmptyListException;
import dukeexceptions.InvalidEntryException;
import duketasks.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks that the user needs to complete
 */

public class DukeList {
    private ArrayList<Task> listOfTasks;
    int numOfTasks;


    public DukeList() {
        listOfTasks = new ArrayList<>();
        numOfTasks = 0;
    }

    /**
     * Takes in a Task and adds it into the list of Tasks
     * Increments the total number of tasks by 1
     *
     * @param currTask Task to be added to the list
     */
    public void addTask(Task currTask) {
        listOfTasks.add(currTask);
        numOfTasks++;
    }

    /**
     * Returns number of tasks in the list
     *
     * @return Total number of tasks in the list
     */
    public int getNumOfTasks() {
        assert this.numOfTasks >= 0 : "Number of tasks cannot be negative";
        return this.numOfTasks;
    }

    /**
     * Marks a certain task in the list as done according to the taskIndex
     *
     * @param taskIndex The current index of the tasks to be marked as done. Is 1-indexed.
     * @return int representing numOfTasks
     * @throws EmptyListException Thrown when list is empty
     * @throws InvalidEntryException Thrown when index < 1 and when index > numOfTasks + 1
     */
    public Task markTaskAsDone(int taskIndex) throws EmptyListException, InvalidEntryException {
        if (numOfTasks == 0) {
            throw new EmptyListException("List is empty! Which task can you complete?!");
        } else if (taskIndex > numOfTasks) {
            String exceptionOutput = String.format("List only has %d item(s), "
                    + "how to complete item no. %d", numOfTasks, taskIndex);
            throw new InvalidEntryException(exceptionOutput);
        } else if (taskIndex < 1) {
            throw new InvalidEntryException("Can only complete positive indexes!");
        } else {
            Task completedTask = listOfTasks.get(taskIndex - 1);
            completedTask.done();
            return completedTask;
        }
    }

    /**
     * Returns a formatted string of the list of tasks to be printed in the ListCommand
     *
     * @return Formatted string of the lists of tasks
     */
    public ArrayList<String> getListForUI() {
        ArrayList<String> toBePrint = new ArrayList<>();

        for (int x = 0; x < numOfTasks; x++) {
            String output = String.format("    %d.%s", x + 1, listOfTasks.get(x).toString());
            toBePrint.add(output);
        }

        return toBePrint;
    }

    /**
     * Deletes the task from the list if available and decrements the numOfTasks
     *
     * @param index Index of tasks in the list to be deleted. 1-indexed.
     * @return The deleted task
     * @throws InvalidEntryException Thrown when index < 1 or index > numOfTasks + 1
     * @throws EmptyListException Thrown when list is empty
     */
    public Task deleteTask(int index) throws InvalidEntryException, EmptyListException {
        if (numOfTasks == 0) {
            throw new EmptyListException("List is empty! How to delete?!");
        } else if (index > numOfTasks) {
            String exceptionOutput = String.format("List only has %d item(s), "
                    + "how to delete item no. %d", numOfTasks, index);
            throw new InvalidEntryException(exceptionOutput);
        } else if (index < 1) {
            throw new InvalidEntryException("Can only remove positive indexes!");
        } else {
            Task removedTask = listOfTasks.remove(index - 1);
            numOfTasks--;
            return removedTask;
        }
    }

    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /**
     * Returns a boolean for whether the list is empty
     *
     * @return true if list is empty, else false
     */
    public boolean isEmpty() {
        if(this.getNumOfTasks() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
