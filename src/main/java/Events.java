public class Events extends Task{
    String time;
    public Events(String newDescripton, String newTime){
        super(newDescripton);
        this.time = newTime;
    }
    public Events(String newDescripton, String newTime, String status){
        super(newDescripton, status);
        this.time = newTime;
    }
    public String getTime(){
        return this.time;
    }
    @Override
    public String toString(){
        return " [E][" + super.getStatusIcon() + "]" + super.description + " (at:" + this.time + ")";
    }
}
