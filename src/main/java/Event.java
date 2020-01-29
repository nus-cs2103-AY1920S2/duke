public class Event extends Task {

    private String time;

    public Event (String description, String time){
        super(description);
        this.time = time.strip().substring(3).strip();
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "| at: " + time;
    }
    public void Output(){
        System.out.println(super.line);
        System.out.println("Got it. I've added this task: ");
        System.out.println(" [E]" + super.toString() + "| at: " + time);
    }
}
