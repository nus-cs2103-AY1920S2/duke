public class Deadline extends Task {
    protected String word;
    protected String date;

    public Deadline(String s) {
        super(s);
        description = s.split("/")[0];
        date = s.split("/")[1];
        word = date.substring(0, date.indexOf(" "));
        date = date.substring(date.indexOf(" ") + 1, date.length());
    }

    public String getType() {
        return "D";
    }

    public String getTask() {
        return description + "(" +word+ ": " +date+ ")";
    }
}
