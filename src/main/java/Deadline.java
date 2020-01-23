public class Deadline extends AbstractTask {
    String date;

    public Deadline(String taskName, String date){
        super(taskName);
        this.date = date.replaceFirst(" ", ": ");
    }

    @Override
    public String toString(){
        return "[D]" + taskStateString() + " " + this.taskName + "(" + this.date + ")";
    }

}
