public class Deadline extends Task {
    private String timing;
    public Deadline(String task, String timing){
        super(task);
        this.timing = timing;
    }

    public Deadline(String task, String timing, boolean bool){
        super(task, bool);
        this.timing = timing;
    }

    public String getTiming(){
        return timing;
    }

    @Override
    public String toString(){
        return  ". " + "[D] " + complete + task + "(" + timing + ")";
    }
}