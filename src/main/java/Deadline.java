public class Deadline extends Task {

    private String time;

    public Deadline (String description, String time){
        super(description);
//        this.time = time.substring(3).strip();
        this.time = time;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + time + ")";
    }
    public void Output(){
        System.out.println(super.line);
        System.out.println("Got it. I've added this task: ");
        System.out.println(" [D]" + super.toString() + "(by: " + time + ")");
    }
    public String getTime(){
        return time;
    }
}