package Backend.Objects.Task;

import Backend.Constants.TaskChars;

public class Todo extends Task {

    public Todo(String content){
        super(content, TaskChars.TODO_CHAR);
    }

}
