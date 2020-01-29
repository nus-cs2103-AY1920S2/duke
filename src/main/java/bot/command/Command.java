package bot.command;

/**
 * Enum to store the various Commands
 * that may be used in the bot
 */
public enum Command {
    BYE("bye"),
    DELETE("delete"),
    DONE("done"),
    EXIT("exit"),
    FIND("find"),
    LIST("list"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    SEARCH("search");

    public final String word;

    Command(String w) {
        this.word = w;
    }
}
