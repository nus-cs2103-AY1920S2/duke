/**
 * DukeTimeFormatter Class.
 * This class is responsible for formatting the time input by user from
 * 24-hour format into AM/PM format
 *
 * @author Amos Cheong
 */
public class DukeTimeFormatter {

    /**
     * This method only does the conversion from 24-hour format to AM/PM format.
     *
     * @param minutes The minutes of the 24-hour time
     * @param fullTime The full 24-hour time input by user
     * @return Returns the converted string to user.
     */
    public String toAMPMFormat(int minutes, int fullTime) {
        String time = "";
        // Translating the time from 24-hour format to
        // AM/PM format
        if (fullTime < 1300){
            time = minutes < 10 ? time + "0" : time;

            String hour = fullTime < 100 ? "12" : Integer.toString(fullTime / 100);
            String minute = Integer.toString(minutes);
            time = fullTime < 1200 ? hour + ":" + time + minute + "am" :
                    hour + ":" + time + minute + "pm";
        } else {
            time = (fullTime % 100) < 10 ? time + "0" : time;
            String hour = Integer.toString((fullTime / 100) - 12);
            String minute = Integer.toString(minutes);
            time = hour + ":" + time + minute + "pm";
        }

        return time;
    }
}
