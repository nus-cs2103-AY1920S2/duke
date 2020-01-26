<<<<<<< .merge_file_a04196
public class Deadline extends Task {
    String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
=======
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate localDate;
    LocalTime localTime;

    String time;
    boolean haveTime;

    public Deadline(String description, String time) throws GrapieExceptions {
        super(description);
        this.time = time;

        time = time.trim();
        //String[] splitedStr = time.split("\\s+");

//        if (splitedStr.length == 1) {
//            haveTime = false;
//        } else {
//            haveTime = true;
//        }

        String[] splittedToCheckIfValid = time.split("-");
        if (splittedToCheckIfValid.length == 3 && time.length() == 10) {
            localDate = LocalDate.parse(time);
        } else {
            throw new GrapieExceptions("OOPS!!! Please format your date like this: YYYY-MM-DD TTTT");
        }

>>>>>>> .merge_file_a06784
    }

    @Override
    public String toString() {
<<<<<<< .merge_file_a04196
        return "[D]" + super.toString() + " (by: " + time + ")";
=======
        return "[D]" + super.toString() + " (by: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
>>>>>>> .merge_file_a06784
    }
}




