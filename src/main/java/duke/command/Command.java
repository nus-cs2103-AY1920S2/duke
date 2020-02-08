package duke.command;

import duke.command.method.ByeCommandMethod;
import duke.command.method.CommandMethod;
import duke.command.method.DeadlineCommandMethod;
import duke.command.method.DeleteCommandMethod;
import duke.command.method.DoneCommandMethod;
import duke.command.method.EventCommandMethod;
import duke.command.method.FindCommandMethod;
import duke.command.method.ListCommandMethod;
import duke.command.method.TodoCommandMethod;
import duke.exception.DukeException;
import duke.exception.DukeNoCommandException;
import duke.exception.DukeUnrecognisedCommandException;
import duke.utils.Pair;

public class Command {
    private String name;
    private String[] arguments;
    private CommandMethod method;

    private static Pair<String,String[]> parseInput(String input) {
        if (input.contains(" ")) {
            String[] parts = input.split(" ", 2);
            String name = parts[0];
            if (parts[1].contains(" ")) {
                return Pair.of(name, parts[1].split(" "));
            } else {
                return Pair.of(name, new String[] {parts[1]});
            }
        } else {
            return Pair.of(input, new String[0]);
        }
    }

    public static Command createCommand(String input) throws DukeException {
        Pair<String,String[]> result = parseInput(input);
        String name = result.getFirst();
        String[] arguments = result.getSecond();
        switch (name) {
        case "": {
            throw new DukeNoCommandException();
        }
        case TodoCommandMethod.NAME: {
            return new Command(name, arguments, new TodoCommandMethod());
        }
        case EventCommandMethod.NAME: {
            return new Command(name, arguments, new EventCommandMethod());
        }
        case DeadlineCommandMethod.NAME: {
            return new Command(name, arguments,
                    new DeadlineCommandMethod());
        }
        case ListCommandMethod.NAME: {
            return new Command(name, arguments, new ListCommandMethod());
        }
        case DoneCommandMethod.NAME: {
            return new Command(name, arguments, new DoneCommandMethod());
        }
        case DeleteCommandMethod.NAME: {
            return new Command(name, arguments, new DeleteCommandMethod());
        }
        case FindCommandMethod.NAME: {
            return new Command(name, arguments, new FindCommandMethod());
        }
        case ByeCommandMethod.NAME: {
            return new Command(name, arguments, new ByeCommandMethod());
        }
        default: {
            throw new DukeUnrecognisedCommandException(name);
        }
        }
    }

    private Command(String name, String[] arguments, CommandMethod method) {
        this.name = name;
        this.arguments = arguments;
        this.method = method;
    }

    public String getCommandName() {
        return name;
    }

    public String[] getArgumentList() {
        return arguments;
    }

    public String getArgumentString() {
        return String.join(" ", arguments);
    }

    public void execute() throws DukeException {
        method.execute(this);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(getCommandName());
        if (arguments.length > 0) {
            output.append(String.format(" %s", getArgumentString()));
        }
        return output.toString();
    }
}
