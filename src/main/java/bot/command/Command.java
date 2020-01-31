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
    SEARCH("search");

    public final String word;

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
}
