class Task {
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

    public static Task parseFromCSV(String csvStr) {
        CSV csv = CSV.parseCSV(csvStr);
        switch (csv.getStr(0)) {
        case ToDoTask.typeStr:
            return ToDoTask.parseFromCSV(csvStr);
        case DeadlineTask.typeStr:
            return DeadlineTask.parseFromCSV(csvStr);
        case EventTask.typeStr:
            return EventTask.parseFromCSV(csvStr);
        default:
            return new Task(csv.getStr(csv.size()));
        }
    }
}

class ToDoTask extends Task {
    protected static final String typeStr = "T";

    ToDoTask(String name) {
        super(name);
    }

    private ToDoTask(String name, boolean done) {
        super(name);
        this.done = done;
    }

    @Override
    public CSV toCSV() {
        return new CSV(new CSV(ToDoTask.typeStr), new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

    public static ToDoTask parseFromCSV(String csvStr) {
        CSV csv = CSV.parseCSV(csvStr);
        return new ToDoTask(csv.getStr(2), Boolean.parseBoolean(csv.getStr(1)));
    }

    @Override
    public String toString() {
        return sqB(ToDoTask.typeStr) + sqB(gou()) + " " + getName();
    }
}

class DeadlineTask extends Task {
    protected static final String typeStr = "D";
    String prepos;
    String dateLine;

    DeadlineTask(String name, String prepos, String time) {
        super(name);
        this.prepos = prepos;
        this.dateLine = time;
    }

    private String timeRemStr() {
        return this.prepos + ":" + this.dateLine;
    }

    private DeadlineTask(String name, String prepos, String time, boolean done) {
        super(name);
        this.prepos = prepos;
        this.dateLine = time;
        this.done = done;
    }

    @Override
    public CSV toCSV() {
        return new CSV(new CSV(DeadlineTask.typeStr), new CSV(this.prepos), new CSV(this.dateLine),
                new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

    public static DeadlineTask parseFromCSV(String csvStr) {
        CSV csv = CSV.parseCSV(csvStr);
        return new DeadlineTask(csv.getStr(4), csv.getStr(1), csv.getStr(2), Boolean.parseBoolean(csv.getStr(3)));
    }

    @Override
    public String toString() {
        return sqB(DeadlineTask.typeStr) + sqB(gou()) + " " + getName() + " (" + timeRemStr() + ")";
    }
}

class EventTask extends Task {
    protected static final String typeStr = "E";
    String prepos;
    String time;

    EventTask(String name, String prepos, String time) {
        super(name);
        this.prepos = prepos;
        this.time = time;
    }

    private EventTask(String name, String prepos, String time, boolean done) {
        super(name);
        this.prepos = prepos;
        this.time = time;
        this.done = done;
    }

    @Override
    public CSV toCSV() {
        return new CSV(new CSV(EventTask.typeStr), new CSV(this.prepos), new CSV(this.time),
                new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

    public static EventTask parseFromCSV(String csvStr) {
        CSV csv = CSV.parseCSV(csvStr);
        return new EventTask(csv.getStr(4), csv.getStr(1), csv.getStr(2), Boolean.parseBoolean(csv.getStr(3)));
    }

    private String timeRemStr() {
        return this.prepos + ": " + this.time;
    }

    @Override
    public String toString() {
        return sqB(EventTask.typeStr) + sqB(gou()) + " " + getName() + " (" + timeRemStr() + ")";
    }
}
