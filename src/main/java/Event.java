public class Event extends Task {

    private String time;

    /**
     * This method updates the action and time accordingly for Events.
     * @param description This is the details for Event.
     * @param time This is the time for the Event.
     */
    public Event (String description, String time){
        super(description);
        this.time = time;
    }

    @Override
    /*Override the toString method for Event*/
    public String toString(){
        return "[E]" + super.toString() + "| at: " + time ;
    }

    /**
     * This method indicates that a new Event Task has been added to the ArrayList in Store.
     */
    public void Output(){
        System.out.println(super.line);
        System.out.println("Got it. I've added this task: ");
        System.out.println(" [E]" + super.toString() + "| at: " + time );
    }

    /**
     * This method return the time of this Event Task.
     * @return String This is the time for the Event.
     */
    public String getTime(){
        return time;
    }
}
