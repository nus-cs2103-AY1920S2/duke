public class Deadline extends Task {

    // by is completion date.
    private String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    void setBy(String by) {

        this.by = by.substring(by.indexOf("by ")).replaceAll("by", "");
    }

    @Override
    void setDescription(String s) {
        String deadline_task = s.substring(s.indexOf("deadline"), s.indexOf("/"));
        String deadline_task2 = deadline_task.replaceAll("deadline", "").trim();
        super.setDescription(deadline_task2);
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + by + ")";
    }

}
