package command;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    public ToDos(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString(){
        return "[T]" + "[" + getStatusIcon() + "] " + this.description;
    }

    @Override
    public String fileString(){
        return "T|" + getStatusIcon() + "|" + this.description;
    }

    @Override
    public boolean containsString(String keyword){
        return this.toString().contains(keyword);
    }
}
