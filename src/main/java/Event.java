public class Event extends Task {

    String line = "____________________________________________________________";
    String time;

    public Event (String description, int index, String time){
        super(description, index);
        this.time = time.substring(3).strip();
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
    public void Output(){
        System.out.println(line);
        System.out.println("Got it. I've added this task: ");
        System.out.println(" [E]" + super.toString() + "(at: " + time + ")");
        System.out.println(String.format("Now you have %d tasks in the list.", index));
        System.out.println(line);
    }
}
