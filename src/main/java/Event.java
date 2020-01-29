import java.time.LocalDate;

public class Event extends Task {
    String at;
    LocalDate atld;
    public Event(String name, String at){
        super(name);
        this.at = at;
    }
    public Event(String name, LocalDate atld){
        super(name);
        this.atld = atld;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + (at == null ? atld.format(dtf) : at) + ")";
    }
}
