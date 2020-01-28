package Tasks;

public class Todo extends Task{
    private static final String todoTaskCode = "T";

    public Todo(String taskName) {
        super(taskName, todoTaskCode);
    }

    public Todo(String taskName, String iDS) {
        this(taskName);
        if(iDS.equals("O")) {
            this.done();
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.taskCode, super.toString());
    }
}
