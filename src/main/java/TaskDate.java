import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/** Custom date object to store different formats of date in String and LocalDate. */
public class TaskDate implements Serializable {
    protected String dateString;
    protected LocalDate localDate;
    protected String time;
    protected String date;

    /**
     * Constructor for TaskDate. Stores formats of LocalDate, and String date and time from dateString.
     * 12/11/1999 2838
     * @param dateString string to be parsed.
     */
    public TaskDate(String dateString) {
        this.dateString = dateString;
        this.localDate = generateLocalDate();
        this.time = generateTime();
        this.date = generateDate();
    }

    /**
     * Gets LocalDate.
     *
     * @return LocalDate.
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Gets date.
     *
     * @return date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets time.
     *
     * @return time.
     */
    public String getTime() {
        return time;
    }

    private LocalDate generateLocalDate() {
        LocalDate temp = null;
        try {
            String[] dateArr = this.dateString.split("/");
            String day = dateArr[0];
            String month = dateArr[1];
            String year = dateArr[2].substring(0, 4);
            temp = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Check your format!");
        } catch (NumberFormatException e) {
            System.err.println("Use numbers, and check your format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    private String generateDate() {
        String res = "";
        try {
            Month month = this.localDate.getMonth();
            String monthString = month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            res += String.valueOf(this.localDate.getDayOfMonth());
            res += " ";
            res += monthString;
            res += " ";
            res += String.valueOf(this.localDate.getYear());
        } catch (NullPointerException e) {
            System.err.println("Error in date.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private String generateTime() {
        String res = "";
        try {
            int dateStringLen = this.dateString.length();
            String time24 = this.dateString.substring(dateStringLen - 4);
            int timeInt = Integer.valueOf(time24);
            String m = "am";

            int hour = Integer.valueOf(time24.substring(0,2));
            if (timeInt >= 0 && timeInt <= 59) {
                hour = 12;
            } else if (timeInt >= 1200) {
                if (timeInt <= 1259) {
                    hour = 12;
                } else {
                    hour -= 12;
                }
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
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Check your format!");
        } catch (NumberFormatException e) {
            System.err.println("Use numbers, and check your format!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public String toString() {
        return this.date + " @ " + this.time;
    }
}
