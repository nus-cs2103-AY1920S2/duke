package sharadhr.duke.io;

import sharadhr.duke.command.AddCommand;
import sharadhr.duke.command.ByeCommand;
import sharadhr.duke.command.Command;
import sharadhr.duke.command.DeleteCommand;
import sharadhr.duke.command.DoneCommand;
import sharadhr.duke.command.FindCommand;
import sharadhr.duke.command.ListCommand;
import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.exception.DukeInvalidCommandException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A class to accept and parse user inputs from the input stream.
 */
public class Input {
    private static final Pattern whitespace = Pattern.compile("\\s+");
    private static final Pattern slashCommands = Pattern
        .compile("(?i)\\/((from)|(to)|(at)|(by)|(on))");
    private static BufferedReader reader;
    protected String line;

    /////////////////////////////////////////////////////////////////////////
    // Instance
    /////////////////////////////////////////////////////////////////////////
    protected String[] tokens;

    public Input() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Input(InputStream is) {
        reader = new BufferedReader(new InputStreamReader(is));
    }

    static String readline() {
        try {
            return reader.readLine();
        }
        catch (Exception e) {
            System.err.println("I/O exception occurred.");
            return "";
        }
    }

    public void setFromString(String input) {
        this.line = input;
        this.tokens = input.trim().split("\\s+");
    }

    /**
     * Gets the next line in the input stream, and stores it. Returns this object
     * for method chaining.
     *
     * @return this {@link Input} object, with the cursor advanced by one line.
     */
    public Input nextLine() {
        this.line = readline();
        this.tokens = whitespace.split(this.line.trim());
        return this;
    }

    /**
     * Returns the current line of input as a {@link String} array of tokens, where
     * the {@link String} is trimmed and separated by any whitespace character.
     *
     * @return the current line, tokenised.
     */
    public String[] getTokens() {
        this.tokens = this.tokens.length == 0 ? whitespace.split(this.line.trim()) : this.tokens;
        return this.tokens;
    }

    /**
     * Returns the current line of input as a {@link Stream} of {@link String}s,
     * trimmed and tokenised by any whitespace character.
     *
     * @return the current line of input as a {@link Stream}<{@link String}>.
     */
    public Stream<String> getTokenStream() {
        return Stream.of(this.getTokens());
    }

    String getFirstToken() {
        return this.getTokens()[0];
    }

    /**
     * Returns a {@link Command}, using the first token in the user input,
     * appropriately parsed with the rest of the user input.
     *
     * @return the user input as an executable {@link Command} object, containing the parsed and
     * tokenised input.
     * @throws DukeInvalidArgumentException if the arguments to the command are incorrect
     * @throws DukeInvalidCommandException  if the command itself is invalid.
     */
    public Optional<Command> getCommand()
        throws DukeInvalidArgumentException, DukeInvalidCommandException {
        String commandString = this.getFirstToken();
        Command.CommandName cmd = Command.whichCommand(commandString);
        switch (cmd) {
        case TODO:
            return Optional.of(new AddCommand(this.getDetail(), cmd));
        case DEADLINE:
            return Optional.of(new AddCommand(this.getDetail(), this.getFirstTimeString(), cmd));
        case EVENT:
            return Optional.of(new AddCommand(this.getDetail(), this.getFirstTimeString(),
                                              this.getNextTimeString(), cmd));
        case LIST:
            return Optional.of(new ListCommand(this.tokensWithoutFirst()));
        case DONE:
            return Optional.of(new DoneCommand(this.tokensWithoutFirst()));
        case FIND:
            return Optional.of(new FindCommand(this.tokensWithoutFirst()));
        case DELETE:
            return Optional.of(new DeleteCommand(this.tokensWithoutFirst()));
        case EMPTY:
            return Optional.empty();
        case INVALID:
            throw new DukeInvalidCommandException(commandString, this.getClass().getName());
        case BYE:
            return Optional.of(new ByeCommand(this.tokensWithoutFirst()));
        default:
            return Optional.empty();
        }
    }

    /**
     * Returns all of this {@link Input}'s tokens minus the first, as a {@link String}[].
     *
     * @return a {@link String}[] of tokens
     */
    public String[] tokensWithoutFirst() {
        return this.getTokenStream().skip(1).toArray(String[]::new);
    }

    /**
     * Returns all of this {@link Input}'s tokens minus the first, as a single {@link String},
     * delimited by a single space.
     *
     * @return a single string of space-delimited tokens
     */
    public String inputWithoutFirstToken() {
        return this.getTokenStream().skip(1).collect(Collectors.joining(" "));
    }

    /**
     * Returns this {@link Input}'s {@code detail} as a single string, with the tokens
     *
     * @return A
     */
    public String getDetail() {
        return this.getTokenStream().skip(1).takeWhile(slashCommands.asMatchPredicate().negate())
                   .collect(Collectors.joining(" "));
    }

    public String getFirstTimeString() {
        return this.getTokenStream().dropWhile(slashCommands.asMatchPredicate().negate()).skip(1)
                   .takeWhile(slashCommands.asMatchPredicate().negate())
                   .collect(Collectors.joining(" "));
    }

    public String getNextTimeString() {
        return this.getTokenStream().dropWhile(slashCommands.asMatchPredicate().negate()).skip(1)
                   .dropWhile(slashCommands.asMatchPredicate().negate()).skip(1)
                   .collect(Collectors.joining(" "));
    }

    public void close() {
        try {
            reader.close();
        }
        catch (IOException e) {
            System.err.println("I/O Exception occurred.");
        }
    }
}