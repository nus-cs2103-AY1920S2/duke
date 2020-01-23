public class Deadline implements Task {
    String taskName;
    boolean isDone;
    String date;

    public Deadline(String taskName){
        String[] in = taskName.split("/");
        this.taskName = in[0];
        this.date = in[1];
        this.isDone = false;
    }

    private String taskStateString(){
        return (this.isDone) ? "[✓]" : "[✗]";
    }

    public void markDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[D]" + taskStateString() + " " + this.taskName + "(" + this.date + ") ";
    }

}
