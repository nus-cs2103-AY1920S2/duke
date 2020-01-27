package tool;

import command.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> toDo;
    public TaskList(){
        this.toDo = new ArrayList<>();
    }

    public void add(Task task){
        this.toDo.add(task);
    }

    public Task remove(int index){
        return this.toDo.remove(index);
    }

    public Task get(int index){
        return this.toDo.get(index);
    }

    public int size(){
        return this.toDo.size();
    }

    public void clear(){
        this.toDo.clear();
    }

    public List<Task> getToDo(){
        return this.toDo;
    }
}
