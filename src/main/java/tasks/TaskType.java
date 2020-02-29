package tasks;

public class TaskType {
    public boolean is_finished;
    public String Type;
    public String TaskName;
    public String Date;

    TaskType(boolean is_finished, String TaskName, String Type,  String Date){
        this.is_finished = is_finished;
        this.Type = Type;
        this.TaskName = TaskName;
        this.Date = Date;
    }

    public void mark(){
        this.is_finished = true;
    }

    public void unmark(){
        this.is_finished = false;
    }

    public String print_task(){
        String out_string = "[N]["+Type+"] " + TaskName + " / " + Date;

        if (this.is_finished){
            out_string = "[Y]["+Type+"] " + TaskName + " / " + Date;
        }
       return out_string;
    }

}
