/**
 * Task of type "Deadline"; Stores the date of deadline
 */
public class Deadline extends Task {
    public String ddl;

    /**
     * Constructor of an instance of Event, set the type as 'D'
     * @param x the content of the task
     * @param d the date of deadline
     */
    public Deadline (String x, String d){
        super(x);
        this.type = "[D]";
        this.ddl = d;
    }

    /**
     * Generate the format for the task to be printed
     * @return a string of the format
     */
    @Override
    public String readyToPrint(){
        return super.readyToPrint() + this.ddl;
    }

    /**
     * Generate the format for the task to be saved to the data file
     * @return a string of the format
     */
    @Override
    public String readyToSave(){
        return super.readyToSave() + " |" + this.ddl.substring(5,this.ddl.length()-1);
    }
}
