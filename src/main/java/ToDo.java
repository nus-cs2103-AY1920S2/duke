import java.util.ArrayList;
import java.util.List;

public class ToDo implements Task {
    String taskName;
    boolean isDone;

    public ToDo(String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }

    private String taskStateString(){
        return (this.isDone) ? "[✓]" : "[✗]";
    }

    public void markDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[T]" + taskStateString() + " " + this.taskName;
    }

}
