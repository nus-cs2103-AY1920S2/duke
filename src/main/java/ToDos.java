public class ToDos extends Task{
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + "[" + getStatusIcon() + "] " + this.description;
    }
}
