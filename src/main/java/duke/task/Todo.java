package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    public Todo(String title,int index) {
        super(title,index);
        this.symbol = "T";
    }

    public Todo(String title,int index, Boolean don) {
        super(title,index, don);
        this.symbol = "T";
    }

    /**
     * Get the a String output to be stored in a txt file.
     * @return formated String to be stored in a txt file.
     */
    public String getInfo() {
        int binary = 0;
        if (this.done) {
            binary = 1;
        }
        return String.format("%s|%d|%s", this.symbol, binary, this.title);
    }

    @Override
    public String toString() {
        String crosstick = "";
        if (this.done) {
            crosstick = "✔";
        } else {
            crosstick = "✘";
        }
        return String.format("[%s][%s] %s", this.symbol, crosstick, this.title);
    }
}
