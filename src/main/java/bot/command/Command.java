package bot.command;

/**
 * Enum to store the various Commands
 * that may be used in the bot
 */
public enum Command {
    BYE("bye"),
    DELETE("delete"),
    DONE("done"),
    EX("ex"),
    EXI("exi"),
    EXIT("exit"),
    FIND("find"),
    HELLO("hello"),
    HELP("help"),
    LIST("list"),
    NOT_DONE("notdone"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    THANKS("thanks"),
    SEARCH("search");

    private final String word;

    /**
     * Constructor for a custom
     * Command
     *
     * @param w A String consisting of one word
     *          that represents this Command
     */
    Command(String w) {
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
