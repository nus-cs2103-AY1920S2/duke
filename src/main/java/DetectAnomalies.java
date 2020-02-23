import java.util.ArrayList;

public class DetectAnomalies {

    public DetectAnomalies() {
    }
    /**
     * This method is to check for clashes on the same day same time.
     * The commented part are to filter for the same day only.
     * @param taskLists List for all the tasks in storage.
     * @param dateTimes string[] that store the date and time of the current task.
     * @return String result of the check if there is clashes or no clashes.
     */

    public String checkForClash(ArrayList<Task> taskLists, String[] dateTimes) {
        //uncomment the codes to filter for same day task as well.
        String output;
        String sameTiming = "";
        String sameDay = "";
        boolean hasTime = !dateTimes[1].equals(" ");
        for (Task task : taskLists) {
            String item = task.toString();
            boolean hasSameDate = item.contains(dateTimes[0]);
            boolean hasSameTime = item.contains(dateTimes[1]) && hasTime;

            if (hasSameDate && hasSameTime) {
                sameTiming = sameTiming + item + "\n";
            }
            //else if (hasSameDate) {
            //    sameDay = sameDay + item + "\n";
            //}
        }
        if (!sameTiming.equals("")) {
            output = "Clashes with: \n" + sameTiming;

        //} else if (!sameDay.equals("")) {
        //    output = "May clash with these event on the same day: \n" + sameDay;
        } else {
            output = "No Clashes.";
        }
        return output;
    }
}
