public class EventTask extends Task {
    public static char ICON = 'E';

    private String at;

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getTime() {
        return at;
    }
    
    @Override
    public String toString() {
        return String.format(
                "[%c]%s (at: %s)",
                EventTask.ICON, super.toString(), getTime()
        );
    }
}