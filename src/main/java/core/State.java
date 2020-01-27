package core;

import task.Task;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * State store the data of the current system.
 */
public class State implements Serializable {

    private ArrayList<Task> taskList;

    public State(ArrayList<Task> taskList){
        this.taskList=taskList;
    }

    public ArrayList<Task> getTaskList(){
        return taskList;
    }


}
