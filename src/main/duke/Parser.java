package main.duke;

import main.duke.enums.Command;
import main.duke.exception.BadDateException;
import main.duke.exception.InvalidCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM uuuu");
    public static DateTimeFormatter DATE_WRITE_FORMATTER =
            DateTimeFormatter.ofPattern("dd MM uu");

    public static LocalDate dateParser(String str) throws BadDateException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM uu");
            return LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            throw new BadDateException("Bad date format");
        }
    }

    public static Command commandParser(String str) throws InvalidCommandException {
        try {
            return Command.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new InvalidCommandException(str + " is an invalid command");
        }
    }
}
