import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

public class Argument {
    private Command command;
    private String details;
    private static HashMap<String, Command> valid_commands = new HashMap<String, Command>() {
        {
            put("list", Command.LIST);
            put("done", Command.DONE);
            put("todo", Command.TODO);
            put("deadline", Command.DEADLINE);
            put("event", Command.EVENT);
            put("delete", Command.DELETE);
        }
    };

    private Argument(Command command, String details) {
        this.command = command;
        this.details = details;
    }

    public static Argument createArgument(String commands) throws DukeException {
        String[] splitted_commands = commands.split(" ");
        String command_string = splitted_commands[0];
        /*
         If boxed_command is empty, it means that the command is not found.
         Therefore, it will throw the exception to inform the client.
         */

        Optional<Command> optional_command = getOptionalCommand(command_string);
        Command keyword = optional_command.orElseThrow(() ->
                new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
        String details = splitted_commands.length > 1 ?
                commands.split(" ", 2)[1] :
                "";
        return new Argument(keyword, details);
    }

    /**
     * getOptionalCommand method gets the Command enums of the corresponding
     * command string provided. Optional instances are used to handle cases
     * where the command string entered by the client is invalid, thus returning
     * null value.
     * @param command_string The command provided by the client to be processed.
     * @return The corresponding Command enums, packed in form of an Optional instance.
     */

    public static Optional<Command> getOptionalCommand(String command_string) {
        return Optional.ofNullable(valid_commands.get(command_string));
    }

    public Command getCommand() {
        return this.command;
    }
}
