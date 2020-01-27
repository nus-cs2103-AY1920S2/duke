public class Todo extends Task {
    public Todo(String task){
        super(task);
    }
    public Todo(String task, boolean bool){
        super(task, bool);
    }

    @Override
    public String toString(){
        return ". " + "[T] " + complete + task;
    }
}