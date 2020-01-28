public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static String getDesc(char[] input) {
        int marker = 0;
        String desc = "";
        for (int i = input.length - 1; (input[i] != '/'); i--) {
            marker = i;
        }
        for (int i = 9; i < marker - 2; i++) {
            desc += input[i];
        }
        return desc;
    }

    public static String getBy(char[] input) {
        int marker = 0;
        String by = "";
        for (int i = input.length - 1; (input[i] != '/'); i--) {
            marker = i;
        }
        for (int i = marker + 3; i < input.length; i++) {
            by += input[i];
        }
        return by;
    }

    public static Deadline createDeadline(String desc, String by) {
        Deadline task = new Deadline(desc, by);
        return task;
    }

    @Override
    public String toString(){
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + this.by + ")";
    }
}
