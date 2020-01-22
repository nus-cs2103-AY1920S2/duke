import java.util.Arrays;

public class ExceptionHandler {
    public void checkEmptyField(String[] taskStringArr, String type) throws EmptyFieldException {
        if (type.equals("todo") || type.equals("deadline") || type.equals("event")) {
            System.out.println(Arrays.toString(taskStringArr));
            if (taskStringArr.length != 2) {
                throw new EmptyFieldException(
                        "You're usually not that taciturn, Red. Explain things to me.");
            } else if (!type.equals("todo")) {
                String[] time = taskStringArr[1].split("/", 2);
                System.out.println(Arrays.toString(time));
                if (time.length != 2) {
                    throw new EmptyFieldException(
                            "We're running short of time, so make sure you note it down.");
                } else if (time[0].isEmpty()) {
                    throw new EmptyFieldException(
                            "You're usually not that taciturn, Red. Explain things to me.");
                }
            }
        }
    }
}
