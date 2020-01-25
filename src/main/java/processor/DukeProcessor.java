package processor;

import commands.*;
import exceptions.DukeException;
import tasks.TaskList;

public class DukeProcessor {

    private boolean isActive;
    private TaskList taskList;

    public DukeProcessor() {
        init();
    }

    private void init() {
        taskList = new TaskList(this);
        isActive = true;
        sayHello();
    }

    public void processInput(String input) {
        Command command = Parser.parseInput(input);

        try {
            command.execute(this, input);
        } catch(DukeException e) {
            Ui.print(e.toString());
        }
    }

    private void sayHello() {
        Command sayHello = Commander.createCommand(CommandType.HI);
        try {
            sayHello.execute(this, "");
        } catch(DukeException e) {
            e.printStackTrace();
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void disable() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }
}
