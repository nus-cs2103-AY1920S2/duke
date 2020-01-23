public class Deadline extends Task {
    private String timing;
    public Deadline(String task, String timing){
        super(task);
        this.timing = timing;
    }

    @Override
    public String toString(){
        return  ". " + "[D] " + complete + task + "(" + timing + ")";
    }
}