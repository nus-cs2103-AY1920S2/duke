public class Deadlines extends Task{
    String time;
    public Deadlines(String newDescripton, String newTime){
        super(newDescripton);
        this.time = newTime;
    }
    public Deadlines(String newDescripton, String newTime, String status){
        super(newDescripton, status);
        this.time = newTime;
    }
    public String getTime(){
        return this.time;
    }
    @Override
    public String toString(){
        return " [D][" + super.getStatusIcon() + "]" + super.description + "(by:" + this.time + ")";
    }
}
