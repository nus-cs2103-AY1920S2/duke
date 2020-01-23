public class Todo extends Task {
    public Todo(String task){
        super(task);
    }

    @Override
    public String toString(){
        return ". " + "[T] " + complete + task;
    }
}