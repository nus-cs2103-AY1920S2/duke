public class Event extends Task{
     private String timing;
     public Event(String task, String timing){
         super(task);
         this.timing = timing;
     }

    public Event(String task, String timing, boolean bool){
        super(task, bool);
        this.timing = timing;
    }

    public String getTiming(){
        return timing;
    }

     @Override
     public String toString(){
        return ". " + "[E] " + complete + task + "(" + timing + ")";
     }

}