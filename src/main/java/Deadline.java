/**
 * Class that represents
 * "Deadline" type Tasks
 */
public class Deadline extends Task {
    public Deadline(String td) {
        super(td);
    }

    @Override
    public String type() {
        return "D";
    }
}
