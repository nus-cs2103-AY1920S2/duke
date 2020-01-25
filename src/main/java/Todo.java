public class Todo extends Task {
    public Todo(String description){
        super (description);
    }

    @Override
    public String toStringTaskstxt(){
        return "T/" + getStatusIconInBin() + "/" + description + "\n";
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}