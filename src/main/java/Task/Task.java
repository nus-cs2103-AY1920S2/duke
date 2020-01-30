package Task;

public class Task {

    int index;
    public String content;
    boolean done;
    public DukeDate date;


    public Task ( String content, int index, DukeDate date ){
        this.index = index;
        this.content = content;
        this.done = false;
        this.date = date;
    }

    public Task( String content, int index ){
        this.index = index;
        this.content = content;
        this.done = false;
    }

    @Override
    public String toString() {

        String done = this.done?"Y": "N";

        return "[" + done + "] " + this.content;
    }

    public void markAsDone(){
        this.done = true;
    }

}
