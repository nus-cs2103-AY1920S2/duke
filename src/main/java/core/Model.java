package core;

import dukexception.DukeException;
import task.Task;

import java.util.ArrayList;

/**
 * To handle operation on current task list.
 */
public class Model {

    private ArrayList<Task> taskList;

    public Model(){
        taskList =new ArrayList<>();
    }

    /**
     * Adds new task to the model.
     * @param task new task.
     */
    public void addTask(Task task){
        taskList.add(task);
    }

    /**
     * Marks the task as done.
     * @param index indicate the specific task.
     * @throws DukeException when index is invalid.
     */
    public void markDone(int index) throws DukeException {
        getTask(index).setDone();
    }

    /**
     * Deletes the task.
     * @param index indicate the specific task.
     * @throws DukeException when index is invalid.
     */
    public void deleteTask(int index) throws DukeException{
        taskList.remove(getTask(index));
    }

    /**
     * Finds the task that contains the specified keyword.
     * @param keyword to be matched by the description of the task.
     * @return the list of matching tasks.
     * @throws DukeException when the task list is empty
     * or there is no matching task.
     */
    public ArrayList<Task> findTask(String keyword) throws DukeException{
        if(taskList.isEmpty()){
            throw new DukeException(ErrorMessage.EMPTY_LIST.toString());
        }
        ArrayList<Task> matchingTasks=new ArrayList<>();
        taskList.stream().filter(t->t.hasKeyword(keyword)).forEach(x->matchingTasks.add(x));
        if(matchingTasks.isEmpty()){
            throw new DukeException("The matching list is empty.");
        }
        return matchingTasks;
    }


    /**
     * Obtains the task.
     * @param index indicate the specific task.
     * @return the specific task.
     * @throws DukeException when task list is empty or index is invalid.
     */
    public Task getTask(int index) throws DukeException {
        if(taskList.size()==0){
            throw new DukeException(ErrorMessage.EMPTY_LIST.toString());
        }
        if(index<0 || index>=taskList.size()){
            throw new DukeException(ErrorMessage.INDEX_OUT_OF_BOUND.toString());
        }
        return taskList.get(index);
    }

    /**
     * Obtains the size of the task list.
     * @return size of task list.
     */
    public int getSize(){
        return taskList.size();
    }

    public ArrayList<Task> getTaskList(){
        return taskList;
    }

    public void load(ArrayList<Task> taskList){
        this.taskList=taskList;
    }

    /**
     * Clears all the task in the list.
     */
    public void clearData(){
        taskList.clear();
    }
}
