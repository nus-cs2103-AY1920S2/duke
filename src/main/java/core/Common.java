package core;

import dukexception.DukeException;
import task.Task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Interact with model and storage to execute the command,
 * construct response text to be display.
 */
public class Common {

    private Model model;
    private Storage storage;

    /**
     * Constructor to initialize the storage and external storage, load the external data if there is.
     * @throws DukeException when there is error loading data.
     */
    public Common() throws DukeException {
        storage=new Storage();
        model = new Model();
        loadData();
    }

    /**
     * Adds new task to the model, update the state.
     * @param task new task.
     * @return the response text to the ui.
     */
    public String[] addTask(Task task) throws DukeException {
        model.addTask(task);
        saveData();
        return responseFormat("Got it. I've added this task: ",
                ""+task,
                "Now you have "+model.getSize()+" tasks in the list.");
    }

    /**
     * Prints the list of task.
     * @return list of task in string array.
     * @throws DukeException when task list is empty.
     */
    public String[] printList() throws DukeException {
        ArrayList<Task> taskList=model.getTaskList();
        if(taskList.isEmpty()){
            throw new DukeException(ErrorMessage.EMPTY_LIST.toString());
        }
        ArrayList<String> s=new ArrayList<>();
        s.add(0,"Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            s.add((i + 1) + ". " + taskList.get(i));
        }
        return s.toArray(new String[0]);
    }

    /**
     * Marks the specific task as done, update the state.
     * @param index indicates the specific task.
     * @return the response text of marking the task.
     * @throws DukeException when the index is invalid.
     */
    public String[] markAsDone(int index) throws DukeException {
        model.markDone(index);
        saveData();
        return responseFormat("Nice! I've marked this task as done: ",
                ""+model.getTask(index));
    }

    /**
     * Deletes the task.
     * @param index indicates the specific task.
     * @return the response text of deleting the task.
     * @throws DukeException when the index is invalid.
     */
    public String[] deleteTask(int index) throws DukeException {
        Task task=model.getTask(index);
        model.deleteTask(index);
        saveData();
        return responseFormat("Noted. I've removed this task: ",
                ""+task,
                "Now you have "+model.getSize()+" tasks in the list.");
    }


    public String[] findTask(String keyword) throws DukeException{
        ArrayList<Task> matchingTasks=model.findTask(keyword);
        ArrayList<String> s=new ArrayList<>();
        s.add("Here are the matching tasks in your list:");
        for(Task task:matchingTasks){
            s.add(task.toString());
        }
        return s.toArray(new String[0]);
    }

    /**
     * Resets the current data and the external data.
     * @throws DukeException clear data is unsuccessful.
     */
    public void reset() throws DukeException {
        model.clearData();
        storage.clearData();
    }

    /**
     * Saves the current state holder to external file.
     * @throws DukeException when saving data is unsuccessful.
     */
    public void saveData() throws DukeException {
        storage.save(model.getTaskList());
    }


    /**
     * Loads the saved state to the system.
     * @throws DukeException when loading is unsuccessful.
     */
    private void loadData() throws DukeException{
        model.load(storage.load());
    }


    /**
     * Formats the Varargs into array.
     * @param text text to be displayed.
     * @return text in string array.
     */
    private String[] responseFormat(String... text){
        ArrayList<String> outputText=new ArrayList<>();
        outputText.addAll(Arrays.asList(text));
        return outputText.toArray(new String[0]);
    }

}
