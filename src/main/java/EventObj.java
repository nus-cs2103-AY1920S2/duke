public class EventObj extends Task {
    protected String datetime;
    public EventObj(String description, String datetime) {
        super(description);
        this.datetime = datetime;
        type = "E";
    }

    @Override
    public String toString(){
        return super.toString() + "(at: " + datetime + ")";
    }

    @Override
    public String print(){
        return getType() + " | " + (isDone ? String.valueOf(1) : String.valueOf(0)) + " | " + getDescription()
                + (!datetime.equals("") ? " | " + datetime : "");
    }

    public static EventObj parse(String taskString){
        String[] parts = taskString.split("\\|");
        String desc = parts[2];
        Task t = new EventObj(desc, "");
        if (parts.length > 3){
            t = new EventObj(desc, parts[3]);
        }
        t.isDone = parts[1].trim().equals("1") ? true : false;

        return (EventObj )t;
    }
}
