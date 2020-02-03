import java.time.LocalDate;

public class DeadLine extends task {
    private String date;
    private LocalDate ld;

    public DeadLine (String name, String end) {
        super(name);
        this.date = end;
        this.ld = LocalDate.parse(end);
    }

    @Override
    public String toSave() {
        if (this.isDone()) {
            return "D /n 1 /n " + this.getName() + " /n " + this.date;
        } else {
            return "D /n 0 /n " + this.getName() + " /n " + this.date;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ld.getDayOfMonth() + " " + ld.getMonth() + " " + ld.getYear() + ")";
    }
}
