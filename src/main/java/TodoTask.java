public class TodoTask extends Task {
    public static char ICON = 'T';

    public TodoTask(String description) {
        super(description);
    }
    
    @Override
    public String toString() {
        return String.format("[%c]%s", TodoTask.ICON, super.toString());
    }
}