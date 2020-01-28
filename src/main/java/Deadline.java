import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline (String description, LocalDate by){
        super(description);
        this.by = by;
    }

    /**
     * returns a String contaning description of object
     * @return returns a String containing the description of the object, used to print out
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " " + by.getDayOfWeek().toString()  + ")";
    }

    /**
     * returns the dynamic state of the Deadline
     * @return the state of Deadline, to be saved in data.txt
     */
    public String saveData(){
        String temp = this.isDone? "1" : "0"; //1 is done, 0 is not done
        return "Deadline" + "|" + temp + "|" + this.description + "|" + this.by;
    }
}
