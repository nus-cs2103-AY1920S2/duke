package LC.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate taskDate;

    public Deadline(String description, String by) {
        super(description);
        if(by.contains("/")){
            by = by.replaceAll("/", "-");
        }
        this.taskDate = LocalDate.parse(by);
    }

    public void printInit(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + this.toString());
        System.out.println("     Now you have " + taskNo + " tasks in the list.");
        System.out.println("    ____________________________________________________________\n");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
