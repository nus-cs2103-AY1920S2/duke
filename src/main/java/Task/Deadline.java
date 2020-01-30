package Task;

public class Deadline extends Task {


    public Deadline(String desc, int index, DukeDate date){

        super(desc, index, date);

    }

    @Override
    public String toString() {
        return index + "." + "[D]" + super.toString() + " (by: " + date.getDateString() + ")";
    }
}
