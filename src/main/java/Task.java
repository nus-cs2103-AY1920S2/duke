import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Task implements CSVParsable {
    private String name;
    protected boolean done = false;

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

    public CSV toCSV() {
        return new CSV(new CSV("T"), new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

    public static Task parseFromCSV(CSV csv) {
        switch (csv.getStr(0)) {
        case ToDoTask.TYPE_STR:
            return ToDoTask.parseFromCSV(csv);
        case DeadlineTask.TYPE_STR:
            return DeadlineTask.parseFromCSV(csv);
        case EventTask.TYPE_STR:
            return EventTask.parseFromCSV(csv);
        default:
            return new Task(csv.getStr(csv.size()));
        }
    }
}

class ToDoTask extends Task {
    protected static final String TYPE_STR = "T";

    ToDoTask(String name) {
        super(name);
    }

    private ToDoTask(String name, boolean done) {
        super(name);
        this.done = done;
    }

    @Override
    public CSV toCSV() {
        return new CSV(new CSV(ToDoTask.TYPE_STR), new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

    public static ToDoTask parseFromCSV(CSV csv) {
        return new ToDoTask(csv.getStr(2), Boolean.parseBoolean(csv.getStr(1)));
    }

    @Override
    public String toString() {
        return sqB(ToDoTask.TYPE_STR) + sqB(gou()) + " " + getName();
    }
}

class DeadlineTask extends Task {
    protected static final String TYPE_STR = "D";
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

    private DeadlineTask(String name, String prepos, String time, boolean done) {
        super(name);
        this.prepos = prepos;
        this.dateLine = time;
        this.done = done;
        try {
            this.ld = LocalDate.parse(time, defaultDateF);
        } catch (DateTimeParseException dtpe) {
            this.ld = null;
        }
    }

    @Override
    public CSV toCSV() {
        return new CSV(new CSV(DeadlineTask.TYPE_STR), new CSV(this.prepos), new CSV(this.dateLine),
                new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

    public static DeadlineTask parseFromCSV(CSV csv) {
        return new DeadlineTask(csv.getStr(4), csv.getStr(1), csv.getStr(2), Boolean.parseBoolean(csv.getStr(3)));
    }

    @Override
    public String toString() {
        return sqB(DeadlineTask.TYPE_STR) + sqB(gou()) + " " + getName() + " (" + timeRemStr() + ")";
    }
}

class EventTask extends Task {
    protected static final String TYPE_STR = "E";
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

    private EventTask(String name, String prepos, String time, boolean done) {
        super(name);
        this.prepos = prepos;
        this.time = time;
        this.done = done;
        try {
            this.ld = LocalDate.parse(time, defaultDateF);
        } catch (DateTimeParseException dtpe) {
            this.ld = null;
        }
    }

    @Override
    public CSV toCSV() {
        return new CSV(new CSV(EventTask.TYPE_STR), new CSV(this.prepos), new CSV(this.time),
                new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

    public static EventTask parseFromCSV(CSV csv) {
        return new EventTask(csv.getStr(4), csv.getStr(1), csv.getStr(2), Boolean.parseBoolean(csv.getStr(3)));
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
        return sqB(EventTask.TYPE_STR) + sqB(gou()) + " " + getName() + " (" + timeRemStr() + ")";
    }
}
