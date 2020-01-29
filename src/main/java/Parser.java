import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    public static Command parse(String command) {
        return new Command(command);
    }

    public static String convertDateAndTime(String time) { // in the form of dd/mm/yyyy hhmm
        String[] array = time.split(" ");
        String day = "";
        if (array.length == 2) {
            String[] dateArray = array[0].split("/");
            if (dateArray.length == 3) {
                if ((dateArray[0].equals("1") || dateArray[0].equals("21") || dateArray[0].equals("31"))) {
                    day = dateArray[0] + "st";
                } else if ((dateArray[0].equals("2") || dateArray[0].equals("22"))) {
                    day = dateArray[0] + "nd";
                } else if ((dateArray[0].equals("3") || dateArray[0].equals("23"))) {
                    day = dateArray[0] + "rd";
                } else {
                    day = dateArray[0] + "th";
                }
                switch (dateArray[1]) {
                case "1":
                    day = day + " of January ";
                    break;
                case "2":
                    day = day + " of February ";
                    break;
                case "3":
                    day = day + " of March ";
                    break;
                case "4":
                    day = day + " of April ";
                    break;
                case "5":
                    day = day + " of May ";
                    break;
                case "6":
                    day = day + " of June ";
                    break;
                case "7":
                    day = day + " of July ";
                    break;
                case "8":
                    day = day + " of August ";
                    break;
                case "9":
                    day = day + " of September ";
                    break;
                case "10":
                    day = day + " of October ";
                    break;
                case "11":
                    day = day + " of November ";
                    break;
                case "12":
                    day = day + " of December ";
                    break;
                }
                day = day + dateArray[2] + ", ";
                try {
                    String rawTime = array[1];
                    String[] timeArray = rawTime.split("-");
                    if (timeArray.length == 1) { // deadline
                        SimpleDateFormat input = new SimpleDateFormat("HHmm");
                        SimpleDateFormat output = new SimpleDateFormat("hh:mmaa");
                        Date date = input.parse(rawTime);
                        String outputString = output.format(date);
                        if (outputString.charAt(0) == '0') {
                            day = day + outputString.substring(1).toLowerCase();
                        } else {
                            day = day + outputString.toLowerCase();
                        }
                    } else if (timeArray.length == 2) { // event
                        SimpleDateFormat input = new SimpleDateFormat("HHmm");
                        SimpleDateFormat output = new SimpleDateFormat("hh:mmaa");
                        Date start = input.parse(timeArray[0]);
                        Date end = input.parse(timeArray[1]);
                        String startTime = output.format(start).toLowerCase();
                        String endTime = output.format(end).toLowerCase();
                        if (startTime.charAt(0) == '0') {
                            startTime = startTime.substring(1);
                        }
                        if (endTime.charAt(0) == '0') {
                            endTime = endTime.substring(1);
                        }
                        day = day + startTime + " to " + endTime;
                    }
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                return time;
            }
        } else {
            return time;
        }
        return day;
    }
}