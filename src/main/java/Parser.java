import java.util.Optional;
import java.util.stream.Stream;
import java.util.function.Supplier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Parser {
    private String command;
    
    public Parser(String command) {
        this.command = command;
    }
    
    interface DukeOptionalCommand {
        public Optional<Command> get() throws DukeException;
    }
    
    public Optional<Command> parse() throws DukeException {
        Optional<Command> res = Stream.<DukeOptionalCommand>of(
                this::byeCommand,
                this::deadlineCommand,
                this::deleteCommand,
                this::doneCommand,
                this::eventCommand,
                this::listCommand,
                this::todoCommand)
            .map(DukeOptionalCommand::get)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst();
        return res;
    }
    
    private static LocalDate parseDate(String dateString) throws DukeException {
        try {
            //return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("uuuu-mm-dd"));
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date must be a valid date in the yyyy-mm-dd format");
        }
    }
    
    private Optional<Command> byeCommand() throws DukeException {
        Pattern listPattern = Pattern.compile("^bye( .*)?$");
        Matcher listMatcher = listPattern.matcher(command);
        if (listMatcher.find()) {
            return Optional.of(new ByeCommand());
        } else {
            return Optional.empty();
        }
    }
    
    private Optional<Command> listCommand() throws DukeException {
        Pattern listPattern = Pattern.compile("^list( (.*))?");
        Matcher listMatcher = listPattern.matcher(command);
        if (listMatcher.find()) {
            String rem = listMatcher.group(2);
            if (rem != null && !rem.isEmpty()) {
                throw new DukeException("List command does not accept any arguments");
            } else {
                return Optional.of(new ListCommand());
            }
        } else {
            return Optional.empty();
        }
    }
    
    private Optional<Command> doneCommand() throws DukeException {
        Pattern donePattern = Pattern.compile("^done( (.*))?");
        Matcher doneMatcher = donePattern.matcher(command);
        if (doneMatcher.find()) {
            try {
                String taskString = doneMatcher.group(2);
                if (taskString == null || taskString.isEmpty()) {
                    throw new DukeException("Task number cannot be empty");
                } else {
                    int taskIndex = Integer.parseInt(taskString);
                    return Optional.of(new DoneCommand(taskIndex));
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Task number must be an integer");
            }
        } else {
            return Optional.empty();
        }
    }
    
    private Optional<Command> deleteCommand() throws DukeException {
        Pattern donePattern = Pattern.compile("^delete( (.*))?");
        Matcher doneMatcher = donePattern.matcher(command);
        if (doneMatcher.find()) {
            try {
                String taskString = doneMatcher.group(2);
                if (taskString == null || taskString.isEmpty()) {
                    throw new DukeException("Task number cannot be empty");
                } else {
                    int taskIndex = Integer.parseInt(taskString);
                    return Optional.of(new DeleteCommand(taskIndex));
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Task number must be an integer");
            }
        } else {
            return Optional.empty();
        }
    }
    
    private Optional<Command> todoCommand() throws DukeException {
        Pattern donePattern = Pattern.compile("^todo( (.*))?");
        Matcher doneMatcher = donePattern.matcher(command);
        if (doneMatcher.find()) {
            String taskString = doneMatcher.group(2);
            if (taskString == null || taskString.isEmpty()) {
                throw new DukeException("Task description cannot be empty");
            } else {
                return Optional.of(new TodoCommand(taskString));
            }
        } else {
            return Optional.empty();
        }
    }
    
    private Optional<Command> deadlineCommand() throws DukeException {
        Pattern deadlinePattern = Pattern.compile("^deadline ?((.*?)( /by ?(.*))?)$");
        Matcher deadlineMatcher = deadlinePattern.matcher(command);
        if (deadlineMatcher.find()) {
            String taskDescription = deadlineMatcher.group(2);
            String deadline = deadlineMatcher.group(4);
            if (taskDescription == null || taskDescription.isEmpty()) {
                throw new DukeException("Task description cannot be empty");
            } else if (deadline == null || deadline.isEmpty()) {
                throw new DukeException("Deadline cannot be empty");
            } else {
                return Optional.of(new DeadlineCommand(taskDescription, parseDate(deadline)));
            }
        } else {
            return Optional.empty();
        }
    }
    
    private Optional<Command> eventCommand() throws DukeException {
        Pattern eventPattern = Pattern.compile("^event ?((.*?)( /at ?(.*))?)$");
        Matcher eventMatcher = eventPattern.matcher(command);
        if (eventMatcher.find()) {
            String taskDescription = eventMatcher.group(2);
            String eventTime = eventMatcher.group(4);
            if (taskDescription == null || taskDescription.isEmpty()) {
                throw new DukeException("Task description cannot be empty");
            } else if (eventTime == null || eventTime.isEmpty()) {
                throw new DukeException("Event time cannot be empty");
            } else {
                return Optional.of(new EventCommand(taskDescription, parseDate(eventTime)));
            }
        } else {
            return Optional.empty();
        }
    }
}

