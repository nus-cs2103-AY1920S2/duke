public class Task_ToDo extends Task{

    private String time;

    public Task_ToDo(String description){
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }

}
