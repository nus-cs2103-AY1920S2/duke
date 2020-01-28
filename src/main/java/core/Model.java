package core;

import dukexception.DukeException;
import task.Task;

import java.util.ArrayList;


public class Model {

    private ArrayList<Task> taskList;

    public Model(){
        taskList =new ArrayList<>();
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


    public ArrayList<String> findTask(String keyword) throws DukeException{
        if(getSize()==0){
            throw new DukeException(ErrorMessage.EMPTY_LIST.toString());
        }
        ArrayList<String> matchingTask=new ArrayList<>();
        taskList.stream().filter(t->t.containKeyword(keyword)).forEach(x->matchingTask.add(x.toString()));
        if(matchingTask.isEmpty()){
            throw new DukeException("The matching list is empty.");
        }
        return matchingTask;
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
