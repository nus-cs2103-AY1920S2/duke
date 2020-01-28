public class Deadline extends Task {

    protected String by;

    public Deadline (String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + by + ")";
    }


    public String saveData(){
        String temp = this.isDone? "1" : "0";
        //1 is done, 0 is not done

        return "Deadline" + "|" + temp + "|" + this.description + "|" + this.by;

    }
}
