import java.time.LocalDate;

/**
 * Parent class of Deadline, Event and ToDo.
 */
public class Task {
    public boolean done;
    public String name;

    /**
     * Constructor. Initialise name and done status.
     *
     * @param name Name of task.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getClassName() { return "Task"; }

    public LocalDate getDateObj() { return null; }

    /**
     * Toggle status of the done attribute of task.
     */
    public void toggleDone() {
        this.done = !this.done;
    }

    /**
     * Converts Task into a String to be saved to file.
     * Overwritten by child classes
     * @return String to be saved to file
     */
    public String toFile() {
        int doneInt = done ? 1 : 0;
        return "Q , " + doneInt + " , " + name;
    }

    @Override
    public String toString() {
        String symbol;
        if (done) {
            symbol = "O";
        } else {
            symbol = "X";
        }
        return "[" + symbol + "] " + this.name;
    }

//    @Override
//    public int compareTo(Task other) {
//        String thisClass = this.getClassName();
//        String otherClass = other.getClassName();
//        System.out.println("Compare ");
//        if (thisClass.equals(otherClass)) {
//            if (thisClass.equals("Deadline")) {
//                return this.getDateObj().compareTo(other.getDateObj());
//            }
//        } else {
//            if (thisClass.equals("Todo")) {
//                return -1;
//            } else if (thisClass.equals("Event") && otherClass.equals("Deadline")) {
//                return -1;
//            } else {
//                return 1;
//            }
//        }
//
//        return 1;
//    }
}
