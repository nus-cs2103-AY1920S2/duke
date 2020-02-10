/**
 * Class representation of ToDo.
 */
public class ToDo extends Task {

    /**
     * ToDo Constructor.
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * ToDo's overriden toString method.
     * @return String representation of ToDO
     */
    @Override
    public String toString() {
        if (tag.equals("")) {
            return ("[T] [" + this.getStatusIcon() + "] " + this.description);
        } else {
            return ("[T] [" + this.getStatusIcon() + "] " + " [" + this.tag + "] " + this.description);
        }
    }
}
