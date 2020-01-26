public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline create(String[] strArr) {
        Deadline t = new Deadline(strArr[2], strArr[3]);
        if(strArr[1].equals("1")) {t.setDone();}
        return t;
    }

    @Override
    public String store() {
        return "D|" + (isDone?"1":"0") + "|" + this.description + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
