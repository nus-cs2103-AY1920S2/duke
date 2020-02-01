package dukelist;

import duketasks.*;
import dukeexceptions.EmptyListException;
import dukeexceptions.InvalidEntryException;

import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> listOfTasks;
    int numOfTasks;


    public DukeList() {
        listOfTasks = new ArrayList<>();
        numOfTasks = 0;
    }

    /**
     * @param currTask Takes in a Task and adds it into the list of Tasks
     */
    public void addTask(Task currTask) {
        listOfTasks.add(currTask);
        numOfTasks++;
    }

    public int getNumOfTasks() {
        return this.numOfTasks;
    }

    public void loadAdd(Task addTask) {
        this.listOfTasks.add(addTask);
        numOfTasks++;
    }

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

    public ArrayList<String> getListForUI() {
        ArrayList<String> toBePrint = new ArrayList<>();

        for (int x = 0; x < numOfTasks; x++) {
            String output = String.format("    %d.%s", x + 1, listOfTasks.get(x).toString());
            toBePrint.add(output);
        }

        return toBePrint;
    }

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
