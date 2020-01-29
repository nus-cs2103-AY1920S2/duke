import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public abstract class Task {
    static DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
    String name;
    boolean done = false;
    public Task(String name){
        this.name = name;
    }
    public void markAsDone(){
        done = true;
    }
    @Override
    public String toString(){
        return "[" + (done ? "✓" : "✗") + "] " + name;
    }
}
