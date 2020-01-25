public class Event extends Task {
    String at;
    public Event(String name, String at){
        super(name);
        this.at = at;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
