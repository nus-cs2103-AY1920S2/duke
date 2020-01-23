public class Event extends Task{
     private String timing;
     public Event(String task, String timing){
         super(task);
         this.timing = timing;
     }

     @Override
     public String toString(){
        return ". " + "[E] " + complete + task + "(" + timing + ")";
     }

}