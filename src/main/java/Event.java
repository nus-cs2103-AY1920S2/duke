public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Event create(String[] strArr) {
        Event t = new Event(strArr[2], strArr[3]);
        if(strArr[1].equals("1")) {t.setDone();}
        return t;
    }

    @Override
    public String store() {
        return "E|" + (isDone?"1":"0") + "|" + this.description + "|" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }
}
