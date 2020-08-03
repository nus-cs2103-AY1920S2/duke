package ip.task;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public abstract class Task implements Serializable {
    public static DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
    String name;
    boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public void markAsDone() {
        done = true;
    }

    @Override
    public String toString() {
        return "[" + (done ? "v" : "x") + "] " + name;
    }

    public boolean contains(String keyword) {
        return name.contains(keyword);
    }
}
