package core;

import dukexception.DukeException;
import task.Task;

import java.util.ArrayList;

/**
 * Interact with model and storage to execute the command.
 */
public class Common {

    private Model model;
    private StateHolder stateHolder=null;
    private Storage storage;

    /**
     * Constructor to initialize the storage and external storage, load the external data if there is.
     * @throws DukeException
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
    public String[] addTask(Task task) {
        model.addTask(task);
        updateState();
        ArrayList<String> s=new ArrayList<>();
        s.add("Got it. I've added this task: :");
        s.add(""+task);
        s.add("Now you have "+model.getSize()+" tasks in the list.");
        return s.toArray(new String[0]);
    }

    /**
     * Prints the list of task.
     * @return list of task in string array.
     * @throws DukeException when task list is empty.
     */
    public String[] printList() throws DukeException {
        ArrayList<String> s=model.formatList();
        updateState();
        s.add(0,"Here are the tasks in your list:");
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
        updateState();
        ArrayList<String> s=new ArrayList<>();
        s.add("Nice! I've marked this task as done: ");
        s.add(""+model.getTask(index));
        return s.toArray(new String[0]);
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
        updateState();
        ArrayList<String> s=new ArrayList<>();
        s.add("Noted. I've removed this task: ");
        s.add(""+task);
        s.add("Now you have "+model.getSize()+" tasks in the list.");
        return s.toArray(new String[0]);
    }


    public String[] findTask(String keyword) throws DukeException{
        updateState();
        ArrayList<String> s=new ArrayList<>();
        s.add("Here are the matching tasks in your list:");
        s.addAll(model.findTask(keyword));
        return s.toArray(new String[0]);
    }


    /**
     * Updates the state holder of the current state.
     */
    private void updateState(){
        stateHolder.addNewState(new State(model.getTaskList()));
    }

    /**
     * Saves the current state holder to external file.
     * @throws DukeException when saving data is unsuccessful.
     */
    public void saveData() throws DukeException {
        storage.save(stateHolder);
    }

    /**
     * Loads the saved state to the system.
     * @throws DukeException when loading is unsuccessful.
     */
    private void loadData() throws DukeException{
        stateHolder=storage.load();
        if(stateHolder==null) {
            stateHolder = new StateHolder();
        }else{
            model.load(stateHolder.getCurrentState().getTaskList());
        }
    }

    /**
     * Resets the current data and the external data.
     * @throws DukeException clear data is unsuccessful.
     */
    public void reset() throws DukeException {
        model.clearData();
        storage.clearData();
    }

}
