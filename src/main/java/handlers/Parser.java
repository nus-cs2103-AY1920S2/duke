package handlers;

import commands.*;

import exceptions.DeleteException;
import exceptions.DoneException;
import exceptions.EmptyException;
import exceptions.UnknownException;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

/**
 * Represents the class which parses each command given by the user.
 * It see which command is given, and returns the corresponding command.
 */
public class Parser {
    public Parser() {}

    public static Command parse(String command) throws UnknownException, DoneException, EmptyException, DeleteException {
        assert command != null : "Command should not be null!";

        String[] str = command.split(" ");
        if (command.equals("list")) {
            ListCommand listCommand = new ListCommand(command);
            return listCommand;
        } else if (command.equals("bye")) {
            ByeCommand byeCommand = new ByeCommand(command);
            return byeCommand;
        } else if (str[0].equals("done")) {
            if (command.length() <= 4) {
                throw new DoneException();
            } else {
                int num = Integer.parseInt(str[1]);

                DoneCommand doneCommand = new DoneCommand(command, num);
                return doneCommand;
            }
        } else if (str[0].equals("deadline")) {
            if (command.length() <= 8) {
                throw new EmptyException();
            } else {
                // removes the word "deadline" from the front of the command
                String[] removeDeadline = command.split(" ", 2);
                // separates the description of deadline from the time and date of deadline
                String[] separator = removeDeadline[1].split("/", 2);
                // removes the /by from the time and date of deadline command
                String[] timeDate = separator[1].split(" ", 2);

                Deadline deadline = new Deadline(separator[0], timeDate[1]);
                AddCommand addCommand = new AddCommand(command, deadline);
                return addCommand;
            }
        } else if (str[0].equals("event")) {
            if (command.length() <= 5) {
                throw new EmptyException();
            } else {
                // removes the word "event" from the front of the command
                String[] removesEvent = command.split(" ", 2);
                // separates the description of event from the time and date of event
                String[] separator = removesEvent[1].split("/", 2);
                // removes the /at from the time and date of event command
                String[] timeDate = separator[1].split(" ", 2);

                Event event = new Event(separator[0], timeDate[1]);
                AddCommand addCommand = new AddCommand(command, event);
                return addCommand;
            }
        } else if (str[0].equals("todo")) {
            if (command.length() <= 4) {
                throw new EmptyException();
            } else {
                // removes the word "todo" from the front of the command
                String[] removesTodo = command.split(" ", 2);

                Todo todo = new Todo(removesTodo[1]);
                AddCommand addCommand = new AddCommand(command, todo);
                return addCommand;
            }
        } else if (str[0].equals("delete")) {
            if (command.length() <= 6) {
                throw new DeleteException();
            } else {
                int num = Integer.parseInt(str[1]);

                DeleteCommand deleteCommand = new DeleteCommand(command, num);
                return deleteCommand;
            }
        } else if (str[0].equals("find")) {
            if (command.length() <= 4) {
                throw new EmptyException();
            } else {
                // str[1] is the keyword to be found
                FindCommand findCommand = new FindCommand(command, str[1]);
                return findCommand;
            }
        } else if (str[0].equals("addNote")) {
            if (command.length() <= 7) {
                throw new EmptyException();
            } else {
                String[] stringSplit = command.split(" ", 3);
                AddNoteCommand addNoteCommand = new AddNoteCommand(command, stringSplit[1], stringSplit[2]);
                return addNoteCommand;
            }
        } else if (str[0].equals("note")) {
            if (command.length() <= 4) {
                throw new EmptyException();
            } else {
                NoteCommand noteCommand = new NoteCommand(command, str[1]);
                return noteCommand;
            }
        } else {
            throw new UnknownException();
        }
    }
}
