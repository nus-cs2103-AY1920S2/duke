public class Deadline extends Task {
    private String timing;
    public Deadline(String task, int id, String timing){
        super(task, id);
        this.timing = timing;
    }

    @Override
    public String toString(){
        return id + ". " + "[D] " + complete + task + "(" + timing + ")";
    }
}