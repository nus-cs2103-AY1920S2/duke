package lc.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate taskDate;

    public Event(String description, String at) {
        super(description);
        if(at.contains("/")){
            at = at.replaceAll("/", "-");
        }
        this.taskDate = LocalDate.parse(at);
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
        return "[E]" + super.toString() + " (at: " + this.taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
