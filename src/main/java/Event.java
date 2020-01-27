public class Event extends Task {
    protected String word;
    protected String date;

    public Event(String s) {
        super(s);
        description = s.split("/")[0];
        date = s.split("/")[1];
        word = date.substring(0, date.indexOf(" "));
        date = date.substring(date.indexOf(" ") + 1, date.length());
    }

    public String getType() {
        return "E";
    }

    public String getTask() {
        return description + "(" +word+ ": " +date+ ")";
    }
}
