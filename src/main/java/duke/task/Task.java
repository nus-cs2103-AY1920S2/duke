package duke.task;

import duke.command.Operation;

import java.time.format.DateTimeFormatter;

public class Task implements TaskPrintable, Parseable {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String userKeyedDateString;
    protected java.time.LocalDate datetime;

    public Task(String description) {
        this.type = "T";
        this.description = description;

        this.isDone = false;
    }

    public Task(String description, String datetime){
        this.userKeyedDateString = datetime;
        setDatetime(datetime);
        this.description = description;
    }

    private Task(boolean isDone, String description){
        this.type = "T";
        this.description = description;
        this.isDone = isDone;
    }

    protected void setDatetime(String dt){
        dt = dt.replaceAll("\\s","");
        try {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
            datetime = java.time.LocalDate.parse(dt, formatter);
        } catch (Exception ex){
            try {
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm");
                datetime = java.time.LocalDate.parse(dt, formatter);
            }catch (Exception ex2){

            }

        }
    }

    protected String printDateTime(){
        DateTimeFormatter outputformatter = java.time.format.DateTimeFormatter.ofPattern("MMM d yyyy");
        try{
            String output  = (datetime).format(outputformatter);
            return output;
        }catch(Exception ex){
            return userKeyedDateString;
        }

    }

    public void setType(String type){
        if (type.equalsIgnoreCase(Operation.TODO.toString())){
            this.type = "T";
        } else if (type.equalsIgnoreCase(Operation.DEADLINE.toString())) {
            this.type = "D";
        } else if (type.equalsIgnoreCase(Operation.EVENT.toString())) {
            this.type = "E";
        } else {
            this.type = "";
        }
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription(){
        return description;
    }

    public String getType(){
        return type;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + getType() + "]"
                + " [" + getStatusIcon() + "]  " + getDescription();
    }

    public String print(){
        return getType() + " | " + (isDone ? String.valueOf(1) : String.valueOf(0)) + " | " + getDescription();
    }


    public static Task parse(String taskString){

        String[] parts = taskString.split("\\|");
        boolean d = parts[1].trim().equals("1") ? true : false;
        String desc = parts[2];
        return new Task(d, desc);
    }
}