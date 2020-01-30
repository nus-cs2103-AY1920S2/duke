package ip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    String by;
    LocalDate byld;
    public Deadline(String name, String by){
        super(name);
        this.by = by;
    }
    public Deadline(String name, LocalDate byld){
        super(name);
        this.byld = byld;
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + (by == null ? byld.format(dtf) : by) + ")";
    }
}
