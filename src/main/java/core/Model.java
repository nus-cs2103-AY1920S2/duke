package core;

import dukexception.DukeException;
import dukexception.SingletonException;
import task.Task;

import java.util.ArrayList;

public class Model {

    private static Model model=null;

    private ArrayList<Task> taskList;

    private Model(){
        taskList =new ArrayList<>();
    }

    public static Model getInstance() throws SingletonException {
        if(model==null){
            model=new Model();
            return model;
        }
        throw new SingletonException(ErrorMessage.SINGLETON.ofType("model"));
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public ArrayList<String> formatList() throws DukeException {
        ArrayList<String> s=new ArrayList<>();
        if(taskList.size()==0){
            throw new DukeException(ErrorMessage.EMPTY_LIST.toString());
        }
        for (int i = 0; i < getSize(); i++) {
            s.add((i + 1) + ". " + taskList.get(i));
        }
        return s;
    }


    public void markDone(int index) throws DukeException {
        getTask(index).setDone();
    }

    public void deleteTask(int index) throws DukeException{
        taskList.remove(getTask(index));
    }

    public Task getTask(int index) throws DukeException {
        if(taskList.size()==0){
            throw new DukeException(ErrorMessage.EMPTY_LIST.toString());
        }
        if(index<0 || index>=taskList.size()){
            throw new DukeException(ErrorMessage.INDEX_OUT_OF_BOUND.toString());
        }
        return taskList.get(index);
    }

    public int getSize(){
        return taskList.size();
    }

    public ArrayList<Task> getTaskList(){
        return taskList;
    }

    public void load(ArrayList<Task> taskList){
        this.taskList=taskList;
    }

    public void clearData(){
        taskList.clear();
    }
}
