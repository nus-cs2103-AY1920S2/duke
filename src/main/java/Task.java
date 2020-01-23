public class Task {
    private int done;
    private String task_name;
    Task() {
    }

    Task(String task_name)
    {
        this.done=0;
        this.task_name=task_name;
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

    @Override
    public String toString() {
        String yes_or_no=(this.done==0)?"[N]":"[Y]";
        return yes_or_no+" "+this.task_name;
    }
}