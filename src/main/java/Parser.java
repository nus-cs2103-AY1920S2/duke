import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Parser {
    static String exitInput = "bye";

    static Command parse(String text) {
        if (text.toLowerCase().equals(exitInput)) {
            return new ByeCommand();
        }

        if (text.toLowerCase().equals("list")) {
            return new ListCommand();
        }

        String[] splitStr = text.split("\\s+");

        if (splitStr[0].toLowerCase().equals("done")) {
            int index = Integer.parseInt(splitStr[1]) - 1;
            return new DoneCommand(index);
        }

        if (splitStr[0].toLowerCase().equals("delete")) {
            int index = Integer.parseInt(splitStr[1]) - 1;
            return new DeleteCommand(index);
        }

        if (splitStr[0].toLowerCase().equals("deadline")) {
            try {
                LocalDate date = LocalDate.now().minus(1, ChronoUnit.MONTHS);
                String deadline = "";
                for (int i = 1; i < splitStr.length; i++) {
                    if ((splitStr[i].equals("/by"))) {
                        date = LocalDate.parse(splitStr[i + 1]);
                        break;
                    } else {
                        deadline += splitStr[i] + " ";
                    }
                }
                deadline = deadline.substring(0, deadline.length() - 1);

                if (date.isBefore(LocalDate.now())) {
                    throw new DukeException("☹ OOPS!!! You cannot set a date that is in the past! Format is yyyy-mm-dd ☹ OOPS!!!");
                } else {
                    Deadline d = new Deadline(deadline, date);
                    return new DeadlineCommand(d);
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }

        if (splitStr[0].toLowerCase().equals("event")) {
            try {
                String at = "";
                String event = "";
                for (int i = 1; i < splitStr.length; i++) {
                    if ((splitStr[i].equals("/at"))) {
                        for (int j = i + 1; j < splitStr.length; j++) {
                            at += splitStr[j] + " ";
                        }
                        break;
                    } else {
                        event += splitStr[i] + " ";
                    }
                }
                if (at.equals("")) {
                    throw new DukeException("☹ OOPS!!! Where is this event????? use /at to tell me! ☹ OOPS!!!");
                } else {
                    event = event.substring(0, event.length() - 1);
                    at = at.substring(0, at.length() - 1);

                    Event e = new Event(event, at);
                    return new EventCommand(e);
                }
            } catch (DukeException error) {
                System.out.println(error);
            }
        }

        if (splitStr[0].toLowerCase().equals("todo")) {
            try {
                if (splitStr.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty. ☹ OOPS!!!");
                } else {
                    String todo = "";
                    for (int i = 1; i < splitStr.length; i++) {
                        todo += splitStr[i] + " ";
                    }
                    todo.substring(0, todo.length() - 1);
                    Todo t = new Todo(todo);
                    return new TodoCommand(t);
                }
            } catch (DukeException m) {
                System.out.println(m);
            }

        }
        return null;
    }
}
