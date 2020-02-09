package main.java;

import main.java.Duke;
import main.java.Task;
import main.java.TaskList;

import java.util.ArrayList;

public class Command {
    protected String command;
    protected ArrayList<Task> tasks;
    Duke duke = new Duke();
    TaskList taskList = new TaskList();



    public Command(String command) {
        this.command = command;
        tasks =  duke.TL;
    }

    public String doTodo() {
        Ui ui = new Ui(command);
        Todo todo = new Todo(ui.getDescription());
        taskList.TL.add(todo);
        return ui.addedTask(todo);
    }

}
