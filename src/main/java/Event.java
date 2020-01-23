public class Event extends Task{
     private String timing;
     public Event(String task, int id, String timing){
         super(task, id);
         this.timing = timing;
     }

     @Override
     public String toString(){
        return id + ". " + "[E] " + complete + task + "(" + timing + ")";
     }

}