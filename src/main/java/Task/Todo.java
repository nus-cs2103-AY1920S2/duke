package Task;

import Task.Task;

public class Todo extends Task {

    public Todo(String desc, int index){
        super(desc, index);
    }

    @Override
    public String toString() {
        return index + "." +"[T]" + super.toString();
    }
}
