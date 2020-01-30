package duchess.io;

import duchess.command.Command;
import duchess.exception.DuchessException;

/**
 * The {@code Parser} class helps to parse given user inputs
 * into a {@code Command} of the appropriate type.
 */
public class Parser {
    /**
     * Parses a given {@code String} into a {@code Command} of the
     * appropriate type.
     *
     * @param command Entire command to be processed.
     * @return Command type of the given command.
     * @throws DuchessException If the command is not recognised.
     */
    public static Command parse(String command) throws DuchessException {
        try {
            return Command.valueOf(command.split("\\s", 2)[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DuchessException("I don't see what I can do with what you just told me.");
        }
    }
}
