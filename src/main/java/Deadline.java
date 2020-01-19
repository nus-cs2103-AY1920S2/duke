public class Deadline extends Task {
    public String date;
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        type = "D";
    }

    @Override
    public String toString(){
        return super.toString() + "(by: " + date + ")";
    }


    @Override
    public String print(){
        return getType() + " | " + (isDone ? String.valueOf(1) : String.valueOf(0)) + " | " + getDescription()
                + (!date.equals("") ? " | " + date : "");
    }


    public static Deadline parse(String taskString){
        String[] parts = taskString.split("\\|");
        String type = parts[0];
        String desc = parts[2];
        Task t = new Deadline(desc, "");
        if (parts.length > 3){
            t = new Deadline(desc, parts[3]);
        }
        t.isDone = parts[1].trim().equals("1") ? true : false;

        return (Deadline) t;
    }
}
