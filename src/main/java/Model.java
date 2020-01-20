import java.util.ArrayList;

public class Model {

    private static Model model=null;

    private ArrayList<Task> taskList;

    private Model(){
        taskList =new ArrayList<>();
    }

    public static Model getInstance() throws SingletonException{
        if(model==null){
            model=new Model();
            return model;
        }
        throw new SingletonException("There should only be one model.");
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public ArrayList<String> formatList() throws DukeException {
        if(taskList.isEmpty()){
            throw new DukeException("The task list is empty.");
        }

        ArrayList<String> s=new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            s.add((i + 1) + ". " + taskList.get(i));
        }
        return s;
    }


    public void markDone(int index) throws DukeException {
        getTask(index).setDone();
    }

    public Task getTask(int index) throws DukeException {
        if(index<0 || index>=taskList.size()){
            throw new DukeException("Index out of bound.");
        }
        return taskList.get(index);
    }

    public int getSize(){
        return taskList.size();
    }

}
