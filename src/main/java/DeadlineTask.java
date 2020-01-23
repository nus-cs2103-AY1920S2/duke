public class DeadlineTask extends Task {
    public static char ICON = 'D';

    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }
    
    @Override
    public String toString() {
        return String.format(
                "[%c]%s (by: %s)",
                DeadlineTask.ICON, super.toString(), by
        );
    }
}