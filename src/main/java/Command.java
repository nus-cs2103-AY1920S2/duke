/**
 * Enum to store the various Commands
 * that may be used in the bot
 */
public enum Command {
    BYE("bye"),
    DONE("done "),
    LIST("list"),
    DEADLINE("deadline "),
    EVENT("event "),
    TODO("todo ");

    public final String word;

    Command(String w) {
        this.word = w;
    }
}
