import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class Events extends Task {
    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    String getAt() {
        return this.at;
    }

    String date(String at) {
        String s = "";
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
            DateFormat newFormat = new SimpleDateFormat("MMM d yyyy h a");
            s =  newFormat.format(df.parse(at));
        }catch (ParseException pe) {
            pe.printStackTrace();
        }
        return s;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date(at) + ")";
    }
}
