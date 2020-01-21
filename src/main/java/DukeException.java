package packagedirectory.test;

import packagedirectory.test.Message;

public class DukeException extends Exception {
    private String type;

    public DukeException(String s) {
        super(s);
        type = s;
    }

    @Override
    public String toString() {
        return  Message.lines
                + type
                + "\n"
                + Message.lines;
    }
}
