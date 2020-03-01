package parser;

/**
 * Presents a command to return help text.
 */
public class HelpCommand extends Command {

    public static final String HELP_TEXT = "Hello! This is duke, your private task manager.\n" +
            "These are some of the options you can choose: \n" +
            "\ttodo <description>: add a todo task\n" +
            "\tevent <description> /at <YYYY-mm-dd hh:ss>: add an event task\n" +
            "\tdeadline <description> /by <YYYY-mm-dd hh:ss>: add a deadline task\n" +
            "\tdelete <index>: delete a task\n" +
            "\tlist: list all current tasks\n" +
            "\thelp: call mini documentation\n" +
            "Also feel free to visit https://github.com/YingxuH/duke/tree/master/docs for more info.";

    public HelpCommand() {

    }

    /**
     * Returns the help text.
     * @return instructions.
     */
    @Override
    public String execute() {
        return HELP_TEXT;
    }
}

