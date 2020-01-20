import java.util.ArrayList;

public class Model {

    private static Model model=null;

    private ArrayList<Task> taskList;

    private Model(){
        taskList =new ArrayList<>();
    }

    public static Model getInstance(){
        if(model==null){
            model=new Model();
            return model;
        }
        return null;
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public ArrayList<String> formatList(){
        if(taskList.isEmpty()){
            return null;
        }

        ArrayList<String> s=new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            s.add((i + 1) + ". " + taskList.get(i));
        }
        return s;
    }


    public void markDone(int index){
        Task task=getTask(index);
        if(task!=null){
            task.setDone();
        }
    }

    public Task getTask(int index){
        if(index<0 || index>=taskList.size()){
            return null;
        }
        return taskList.get(index);
    }

    public int getSize(){
        return taskList.size();
    }

}
