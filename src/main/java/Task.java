import java.time.LocalDate;
import java.time.format.*;

class Task {
    private String name;
    private boolean done = false;

    protected Task(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return this.done;
    }

    public boolean setToDone() {
        return this.done = true;
    }

    public String getName() {
        return this.name;
    }

    protected String gou() {
        return isDone() ? "✓" : "✗";
    }

    protected String sqB(String s) {
        return "[" + s + "]";
    }
}

class ToDoTask extends Task {
    ToDoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return sqB("T") + sqB(gou()) + " " + getName();
    }
}

class DeadlineTask extends Task {
    private static final DateTimeFormatter defaultDateF = DateTimeFormatter.ofPattern("d'/M/L'/y");
    LocalDate ld;
    String prepos;
    String dateLine;

    DeadlineTask(String name, String prepos, String time) {
        super(name);
        this.prepos = prepos;
        this.dateLine = time;
        try {
            this.ld = LocalDate.parse(time, defaultDateF);
        } catch (DateTimeParseException dtpe) {
            this.ld = null;
        }
    }

    private String timeRemStr() {
        try {
            return this.prepos + ":" + (this.ld == null ? this.dateLine
                    : ld.format(DateTimeFormatter.ofPattern("E, d-M/L-y")));
        } catch (DateTimeParseException dtpe) {
            return this.prepos + ":" + this.dateLine;
        }
    }

    @Override
    public String toString() {
        return sqB("D") + sqB(gou()) + " " + getName() + " (" + timeRemStr() + ")";
    }
}

class EventTask extends Task {
    private static final DateTimeFormatter defaultDateF = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    LocalDate ld;
    String prepos;
    String time;

    EventTask(String name, String prepos, String time) {
        super(name);
        this.prepos = prepos;
        this.time = time;
        try {
            this.ld = LocalDate.parse(time, defaultDateF);
        } catch (DateTimeParseException dtpe) {
            this.ld = null;
        }
    }

    private String timeRemStr() {
        try {
            return this.prepos + ": " + (this.ld == null ? this.time
                    : ld.format(DateTimeFormatter.ofPattern("E, d-M/L-y")));
        } catch (DateTimeParseException dtpe) {
            return this.prepos + ": " + this.time;
        }
    }

    @Override
    public String toString() {
        return sqB("E") + sqB(gou()) + " " + getName() + " (" + timeRemStr() + ")";
    }
}
