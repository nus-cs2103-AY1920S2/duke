public class EventTask extends Task {
    public static char ICON = 'E';

    protected String at;

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }
    
    @Override
    public String toString() {
        return String.format(
                "[%c]%s (at: %s)",
                EventTask.ICON, super.toString(), at
        );
    }
}