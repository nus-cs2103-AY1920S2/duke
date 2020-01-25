package main;

import commands.*;
import exceptions.DukeException;
import tasks.Task;
import tasks.TaskListHandler;

import java.util.ArrayList;
import java.util.List;

public class DukeProcessor {

    private List<Task> taskList;
    private boolean isActive;
    private TaskListHandler taskListHandler;

    public DukeProcessor() {
        init();
    }

    private void init() {
        taskList = new ArrayList<Task>();
        taskListHandler = new TaskListHandler(this);
        isActive = true;
        sayHello();

        try {
            taskList = taskListHandler.loadTasks();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("It looks like you have no previously saved tasks! Starting a new list for you...");
        }
    }

    public void processInput(String input) {
        String[] inputArgs = input.split(" ", 2);
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
                break;
            case "todo":
                command = createCommand(CommandType.TODO);
                break;
            case "deadline":
                command = createCommand(CommandType.DEADLINE);
                break;
            case "event":
                command = createCommand(CommandType.EVENT);
                break;
            case "delete":
                command = createCommand(CommandType.DELETE);
                break;
            default:
                command = createCommand(CommandType.INVALID);
        }

        try {
            command.execute(this, input);
        } catch(DukeException e) {
            System.out.println(e);
        }
    }

    private void sayHello() {
        DukeCommand sayHello = createCommand(CommandType.HI);
        try {
            sayHello.execute(this, "");
        } catch(DukeException e) {
            e.printStackTrace();
        }
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
            case TODO:
                command = new CommandTodo();
                break;
            case DEADLINE:
                command = new CommandDeadline();
                break;
            case EVENT:
                command = new CommandEvent();
                break;
            case DELETE:
                command = new CommandDelete();
                break;
            default:
                command = new CommandInvalid();
        }

        return command;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public TaskListHandler getTaskListHandler() {
        return taskListHandler;
    }

    public void disable() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }
}
