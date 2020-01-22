import java.util.Arrays;

public class ExceptionHandler {
    public void checkEmptyField(String[] taskStringArr, String type) throws EmptyFieldException {
        if (type.equals("todo") || type.equals("deadline") || type.equals("event")) {
            if (taskStringArr.length != 2) {
                throw new EmptyFieldException(
                        "You're usually not that taciturn, Red. Explain things to me.");
            } else if (!type.equals("todo")) {
                String[] time = taskStringArr[1].split("/", 2);
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

    public void checkWrongCommand(String type) throws WrongCommandException{
        if (!(type.equals("todo")
                || type.equals("deadline")
                || type.equals("event")
                || type.equals("list")
                || type.equals("done")
                || type.equals("bye"))) {
            throw new WrongCommandException(
                    "Uhh... You're gonna have to say that again, Red.");
        }
    }
}
