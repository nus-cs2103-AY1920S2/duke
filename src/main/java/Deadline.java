public class Deadline extends Task {

    private String time;

    public Deadline (String description, int index, String time){
        super(description, index);
//        String[] input = time.split("by");
//        this.time = input[1];
        this.time = time.substring(3).strip();
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + time + ")";
    }
    public void Output(){
        System.out.println(super.line);
        System.out.println("Got it. I've added this task: ");
        System.out.println(" [D]" + super.toString() + "(by: " + time + ")");
        System.out.println(String.format("Now you have %d tasks in the list.", index));
        System.out.println(super.line);
    }
}