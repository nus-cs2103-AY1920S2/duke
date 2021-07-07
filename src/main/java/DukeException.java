package packagedirectory.test;

import packagedirectory.test.Message;

public class DukeException extends Exception {
    private String errorMsg;

    public DukeException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return  Message.lines
                + errorMsg
                + "\n"
                + Message.lines;
    }
}
