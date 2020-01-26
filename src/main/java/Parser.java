package main.java;

import java.text.ParseException;
import java.util.Scanner;

public class Parser {

    protected Scanner sc;

    public Parser(Scanner sc) {
        this.sc = sc;
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public String getCommandType(String command) {
        if (command.contains("list")) {
            return "list";
        } else if (command.contains("bye")) {
            return "bye";
        } else if (command.contains("done")) {
            return "done";
        } else if (command.contains("delete")) {
            return "delete";
        } else if (command.contains("deadline")) {
            return "deadline";
        } else if (command.contains("event")) {
            return "event";
        } else if (command.contains("todo")) {
            return "todo";
        } else return "Command not understood. Please try again";
    }

    public Deadline getDeadline(String str) throws DukeException, ParseException {
        String[] splitStr = str.split("/by ");

        if (splitStr.length < 2) {
            throw (new DukeException("The deadline requires a date it is due by."));
        }

        String description = splitStr[0];
        String timing = splitStr[1];
        String[] splitCommand = description.split(" ");

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < splitCommand.length; i ++) {
            builder.append(splitCommand[i]);
            builder.append(" ");
            description = builder.toString();
        }
        return new Deadline(description, timing);

    }

    public Event getEvent(String str) throws DukeException, ParseException {
        String[] splitStr = str.split("/at ");

        if (splitStr.length < 2) {
            throw (new DukeException("The event requires a specific date."));
        }

        String description = splitStr[0];
        String timing = splitStr[1];
        String[] splitCommand = description.split(" ");

        StringBuilder builder = new StringBuilder();
        for(int i = 1; i < splitCommand.length; i ++) {
            builder.append(splitCommand[i]);
            builder.append(" ");
            description = builder.toString();
        }
        return new Event(description, timing);
    }

    public ToDo getToDo(String str) throws DukeException {
        String[] splitStr = str.split("todo ");

        if (splitStr.length == 1) {
            throw (new DukeException("The To-Do requires a description."));
        }

        String description = splitStr[1];
        return new ToDo(description);
    }

    public int getTaskNo(String command) {
        String[] str = command.split(" ");
        return (Integer.parseInt(str[1]) - 1);
    }


}
