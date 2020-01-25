import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

import static java.time.format.TextStyle.SHORT;

public class TaskDate implements Serializable {
    protected String dateString;
    protected LocalDate localDate;
    protected String time;
    protected String date;

    public TaskDate(String dateString) {
        this.dateString = dateString;
        this.localDate = getLocalDate();
        this.time = getTime();
        this.date = getDate();
    }

    private LocalDate getLocalDate() {
        String[] dateArr = this.dateString.split("/");
        String day = dateArr[0];
        String month = dateArr[1];
        String year = dateArr[2].substring(0,4);
        return LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
    }

    private String getDate() {
        String res = "";
        Month month = this.localDate.getMonth();
        String monthString = month.getDisplayName(SHORT, Locale.ENGLISH);
        res += String.valueOf(this.localDate.getDayOfMonth());
        res += " ";
        res += monthString;
        res += " ";
        res += String.valueOf(this.localDate.getYear());
        return res;    }

    private String getTime() {
        int dateStringLen = this.dateString.length();
        String res = "";
        String time24 = this.dateString.substring(dateStringLen - 4);
        int timeInt = Integer.valueOf(time24);
        String m = "am";

        int hour = Integer.valueOf(time24.substring(0,2));
        if (timeInt >= 0 && timeInt <= 59) {
            hour = 12;
        } else if (timeInt >= 1200) {
            hour -= 12;
            m = "pm";
        }
        String hourRes = String.valueOf(hour);

        String minutesRes = "";
        int minutes = Integer.valueOf(time24.substring(2,4));
        if (minutes < 10) {
            minutesRes += "0";
        }
        minutesRes += String.valueOf(minutes);

        res += hourRes;
        res += ".";
        res += minutesRes;
        res += m;
        return res;
    }

    @Override
    public String toString() {
        return this.date + " @ " + this.time;
    }
}
