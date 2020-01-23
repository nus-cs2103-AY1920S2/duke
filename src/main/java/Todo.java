public class Todo extends Task {
    public Todo(String task, int id){
        super(task, id);
    }

    @Override
    public String toString(){
        return id + ". " + "[T] " + complete + task;
    }
}