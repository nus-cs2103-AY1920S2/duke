package dude;

public class ParsingException extends Exception {
    private String[] usageMsgs;

    public ParsingException() {
        super();
    }

    public ParsingException(String errorMsg, String ...usageMsgs) {
        super(errorMsg);
        this.usageMsgs = usageMsgs;
    }

    public String[] getUsageMsgs() {
        return usageMsgs;
    }
}