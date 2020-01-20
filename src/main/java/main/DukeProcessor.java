package main;

import commands.*;

import java.util.ArrayList;
import java.util.List;

public class DukeProcessor {

    private List<String> textList;
    private boolean isActive;

    public DukeProcessor() {
        textList = new ArrayList<String>();
        isActive = true;

        DukeCommand sayHello = createCommand(CommandType.HI, "");
        sayHello.execute(this, "");
    }

    public void processInput(String input) {
        DukeCommand command;

        switch(input) {
            case "bye":
                command = createCommand(CommandType.BYE, "");
                break;
            case "list":
                command = createCommand(CommandType.LIST, "");
                break;
            default:
                command = createCommand(CommandType.ADD, input);
        }

        command.execute(this, input);
    }

    public DukeCommand createCommand(CommandType commandType, String content) {

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
            default:
                command = new CommandAdd();
        }

        return command;
    }

    public List<String> getTextList() {
        return textList;
    }

    public void disable() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }
}
