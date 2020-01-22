public class Events extends Task{
    String time;
    public Events(String newDescripton, String newTime){
        super(newDescripton);
        this.time = newTime;
    }
    @Override
    public String toString(){
        return " [D][" + super.getStatusIcon() + "]" + super.description + "(at:" + this.time + ")";
    }
}
