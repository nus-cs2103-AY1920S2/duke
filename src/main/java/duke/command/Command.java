package duke.command;

import duke.Duke;
// import duke.command.method.*;
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

public class Command {
    private String name;
    private String[] arguments;
    private CommandMethod method;

    public static Command createCommand(String input) throws DukeException {
        String name;
        String[] arguments;
        if (input.contains(" ")) {
            String[] inputs = input.split(" ", 2);
            name = inputs[0];
            if (inputs[1].contains(" ")) {
                arguments = inputs[1].split(" ");
            } else {
                arguments = new String[] {inputs[1]};
            }
        } else {
            name = input;
            arguments = new String[0];
        }
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
            break;
        }
        }
        throw new DukeUnrecognisedCommandException(name);
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

    public void execute(Duke program) throws DukeException {
        method.execute(program, this);
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
