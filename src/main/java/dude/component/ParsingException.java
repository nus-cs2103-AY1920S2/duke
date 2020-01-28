package dude.component;

public class ParsingException extends Exception {
    private String[] usageMsgs;

    /**
     * Initializes a ParsingException when one need not report the cause of the Exception to users,
     * thus no information is stored.
     */
    public ParsingException() {
        super();
    }

    /**
     * Initializes the ParsingException carrying information of the error in parsing as well as the proper usage,
     * for users to learn the proper commands to communicate with Dude.
     *
     * @param errorMsg message explaining what went wrong in parsing.
     * @param usageMsgs message(s) showing possible commands.
     */
    public ParsingException(String errorMsg, String... usageMsgs) {
        super(errorMsg);
        this.usageMsgs = usageMsgs;
    }

    /**
     * Returns all proper usages of the command that lead to this ParsingException.
     *
     * @return an array of Strings of proper usage messages.
     */
    public String[] getUsageMsgs() {
        return usageMsgs;
    }
}