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
    void setDescription(String s) throws DukeException {

        try {
            String deadline_task = s.substring(s.indexOf("deadline"), s.indexOf("/"));


            String deadline_task2 = deadline_task.replaceAll("deadline", "").trim();


            super.setDescription(deadline_task2);
        } catch (Exception e) {
            throw new DukeException("", "Deadline");
        }
    }

    @Override
    public String toString() {
        return " [" + Task_Codes.D + "]" + super.toString() + " (by: " + by + ")";
    }

}
