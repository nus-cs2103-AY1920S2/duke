package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Parser {
    private Command command;
    private String parameter;
    private String description;
    private LocalDate date;

    public Parser(String input) throws DukeException {
        this.command = extractCommand(input);

        if (this.command == Command.DONE || this.command == Command.DELETE) {
            this.parameter = getFirstParameter(input);
            try {
                Integer.valueOf(this.parameter);
            } catch (NumberFormatException e) {
                throw new DukeException("Task number is not an integer.");
            }
        } else if (this.command == Command.TODO) {
            this.description = getFirstParameter(input);
        } else if (this.command == Command.DEADLINE || this.command == Command.EVENT) {
            this.description = getFirstParameter(input);
            this.date = getDate(input);
        }
    }

    public Command getCommand() {
        return this.command;
    }

    public String getParameter() {
        return this.parameter;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public static Command extractCommand(String input) throws DukeException {
        // if command doesnt exist
        String command = input.split(" ")[0].toLowerCase();

        switch (command) {
            case "bye":
                return Command.BYE;
            case "done":
                return Command.DONE;
            case "todo":
                return Command.TODO;
            case "deadline":
                return Command.DEADLINE;
            case "event":
                return Command.EVENT;
            case "list":
                return Command.LIST;
            case "delete":
                return Command.DELETE;
            default:
                throw new DukeException("Invalid Command.");
        }
    }

    public static String getFirstParameter(String input) throws DukeException {
        String description = input.split(" /")[0];
        String[] descArr = description.split(" ");
        if (descArr.length == 1) {
            throw new DukeException("☹ Why did you not provide any description?");
        }
        // remove verb
        String[] cleaned = Arrays.copyOfRange(descArr, 1, descArr.length);
        return String.join(" ", cleaned);
    }

    public static LocalDate getDate(String input) throws DukeException {
        String[] tryGetDate = input.split(" /");
        if (tryGetDate.length == 1) {
            throw new DukeException("☹ Why did you not provide the time?");
        }
        String time = tryGetDate[1];
        String[] timeArr = time.split(" ");
        // remove verb
        String[] cleaned = Arrays.copyOfRange(timeArr, 1, timeArr.length);
        return convertToLocalDate(String.join(" ", cleaned));
    }

    public static LocalDate convertToLocalDate(String input) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(input, formatter);
    }
}
