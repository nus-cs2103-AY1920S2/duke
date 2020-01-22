package duke.task;

public class EventObj extends Task {
    public EventObj(String description, String datetime) {
        super(description, datetime);
        type = "E";
    }

    @Override
    public String toString(){
        return super.toString() + "(at: " + printDateTime() + ")";
    }

    @Override
    public String print(){
        return getType() + " | " + (isDone ? String.valueOf(1) : String.valueOf(0)) + " | " + getDescription()
                + (!userKeyedDateString.equals("") ? " | " + printDateTime() : "");
    }

    public static EventObj parse(String taskString){
        String[] parts = taskString.split("\\|");
        String desc = parts[2];
        Task t = new EventObj(desc, "");
        if (parts.length > 3){
            t = new EventObj(desc, String.join("", java.util.Arrays.stream(parts).skip(3).toArray(String[]::new)));
        }
        t.isDone = parts[1].trim().equals("1") ? true : false;

        return (EventObj )t;
    }
}
