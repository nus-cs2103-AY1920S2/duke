package Task;

public class Task_ToDo extends Task{

    public Task_ToDo(String description){
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }

}
