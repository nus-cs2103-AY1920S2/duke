

public class Event extends Task {


    protected String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    public String saveData(){
        String temp = this.isDone? "1" : "0";
        //1 is done, 0 is not done

        return "Event" + "|" + temp + "|" + this.description + "|" + this.at;
    }
}
