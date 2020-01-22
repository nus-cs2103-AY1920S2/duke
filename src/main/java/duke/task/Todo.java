package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        type = "T";
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public String print(){
        return getType() + " | " + (isDone ? String.valueOf(1) : String.valueOf(0)) + " | " + getDescription();
    }


    public static Todo parse(String taskString){
        String[] parts = taskString.split("\\|");
        boolean d = parts[1].trim().equals("1");
        String desc = parts[2].trim();
        return new Todo(desc);
    }
}
