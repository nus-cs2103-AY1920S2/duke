public class ExceptionHandler {
    public static void checkEmptyField(String[] taskStringArr, String type) throws EmptyFieldException {
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

    public static void checkInvalidCommand(String type) throws InvalidCommandException {
        if (!(type.equals("todo")
                || type.equals("deadline")
                || type.equals("event")
                || type.equals("list")
                || type.equals("done")
                || type.equals("delete")
                || type.equals("bye"))) {
            throw new InvalidCommandException("");
        }
    }
}
