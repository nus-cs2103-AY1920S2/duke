package bot.command;

/**
 * A class representing the various Commands
 * that may be used in the bot. Also has
 * the original commands that cannot be
 * changed by the user
 */
public class Command {
    public static final Command ALIAS = new Command("alias");
    public static final Command BYE = new Command("bye");
    public static final Command DELETE = new Command("delete");
    public static final Command DONE = new Command("done");
    public static final Command EX = new Command("ex");
    public static final Command EXI = new Command("exi");
    public static final Command EXIT = new Command("exit");
    public static final Command FIND = new Command("find");
    public static final Command HELLO = new Command("hello");
    public static final Command HELP = new Command("help");
    public static final Command LIST = new Command("list");
    public static final Command NOT_DONE = new Command("notdone");
    public static final Command DEADLINE = new Command("deadline");
    public static final Command EVENT = new Command("event");
    public static final Command TODO = new Command("todo");
    public static final Command THANKS = new Command("thanks");
    public static final Command SEARCH = new Command("search");

    private final String word;

    /**
     * Constructor for a custom
     * Command
     *
     * @param w A String consisting of one word
     *          that represents this Command
     */
    public Command(String w) {
        this.word = w;
    }

    /**
     * Gets the word that this Command represents
     *
     * @return Returns a String containing the word
     *     (without any whitespace)
     */
    public String getWord() {
        return this.word;
    }
}
