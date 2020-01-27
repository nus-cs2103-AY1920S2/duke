import java.util.HashMap;
import java.util.Optional;

public class Parser {
    private static HashMap<String, Keyword> validKeywords = new HashMap<>() {
        {
            put("list", Keyword.LIST);
            put("done", Keyword.DONE);
            put("todo", Keyword.TODO);
            put("deadline", Keyword.DEADLINE);
            put("event", Keyword.EVENT);
            put("delete", Keyword.DELETE);
        }
    };

    public Parser() {

    }

    /**
     * Parses the command string entered by the client andcreate an Argument
     * instance. If the command keyword is a valid keyword, it will return an
     * Argument instance. Otherwise, it will throw an exception.
     * @param command The command string entered by the client.
     * @return An Argument instance, initialized by the constructor.
     * @throws DukeUnknownKeywordException If the command keyword (the first word) is invalid.
     */

    public Command parse(String command) throws DukeUnknownKeywordException {
        String[] splitted_commands = command.split(" ");
        String command_string = splitted_commands[0];
        /*
         If optional_command is empty, it means that the command is not found.
         Therefore, it will throw the exception to inform the client.
         */

        Optional<Keyword> optionalKeyword = getOptionalKeyword(command_string);
        Keyword keyword = optionalKeyword.orElseThrow(() ->
                new DukeUnknownKeywordException("☹ OOPS!!! I'm sorry, " +
                        "but I don't know what that means :-("));
        String details = splitted_commands.length > 1 ?
                command.split(" ", 2)[1] :
                "";
        return new Argument(keyword, details);
    }

    /**
     * Returns the Keyword enums of the corresponding
     * command string provided. Optional instances are used to handle cases
     * where the command string entered by the client is invalid, thus returning
     * null value.
     * @param commandString The command provided by the client to be processed.
     * @return The corresponding Keyword enums, packed in form of an Optional instance.
     */

    public static Optional<Keyword> getOptionalKeyword(String commandString) {
        return Optional.ofNullable(validKeywords.get(commandString));
    }
}