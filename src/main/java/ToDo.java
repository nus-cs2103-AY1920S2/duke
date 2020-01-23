import java.util.ArrayList;
import java.util.List;

public class ToDo extends AbstractTask {

    public ToDo(String taskName){
        super(taskName);
    }

    @Override
    public String toString(){
        return "[T]" + taskStateString() + " " + this.taskName;
    }

}
