public class Task {
    private int done;
    private String task_name;
    Task()
    {
        done=0;
        task_name="";
    }
    void setDone(){
        done=1;
    }
    void setTaskName(String s){
        task_name=s;
    }
    int getDone(){
        return done;
    }
    String getTaskName(){
        return task_name;
    }
}