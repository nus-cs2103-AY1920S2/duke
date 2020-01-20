package main;

import commands.*;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class DukeProcessor {

    private List<Task> taskList;
    private boolean isActive;

    public DukeProcessor() {
        taskList = new ArrayList<Task>();
        isActive = true;

        DukeCommand sayHello = createCommand(CommandType.HI);
        sayHello.execute(this, "");
    }

    public void processInput(String input) {
        String[] inputArgs = input.split(" ", 2);
        String args = input;
        DukeCommand command;

        switch(inputArgs[0]) {
            case "bye":
                command = createCommand(CommandType.BYE);
                break;
            case "list":
                command = createCommand(CommandType.LIST);
                break;
            case "done":
                command = createCommand(CommandType.DONE);
                args = inputArgs[1];
                break;
            default:
                command = createCommand(CommandType.ADD);
        }

        command.execute(this, args);
    }

    private DukeCommand createCommand(CommandType commandType) {

        DukeCommand command;

        switch(commandType) {
            case HI:
                command = new CommandHi();
                break;
            case BYE:
                command = new CommandBye();
                break;
            case LIST:
                command = new CommandList();
                break;
            case DONE:
                command = new CommandDone();
                break;
            default:
                command = new CommandAdd();
        }

        return command;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void disable() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }
}
