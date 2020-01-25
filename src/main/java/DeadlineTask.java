public class DeadlineTask extends Task {
    public static char ICON = 'D';

    private String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTime() {
        return by;
    }
    
    @Override
    public String toString() {
        return String.format(
                "[%c]%s (by: %s)",
                DeadlineTask.ICON, super.toString(), getTime()
        );
    }
}