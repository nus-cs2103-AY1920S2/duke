public class Deadlines extends Task{
    String time;
    public Deadlines(String newDescripton, String newTime){
        super(newDescripton);
        this.time = newTime;
    }
    @Override
    public String toString(){
        return " [D][" + super.getStatusIcon() + "]" + super.description + "(by:" + this.time + ")";
    }
}
