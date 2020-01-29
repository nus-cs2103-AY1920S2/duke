import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    private Commands com;
    private int indexOfTaskAffected = -1;
    private Task newTask;
    private String keyword;

    public Parser(String line, int size) throws DukeException {
        if (line.equals("bye")) {
            com = Commands.BYE;

        } else if (line.equals("list")) {
            com = Commands.LIST_TASKS;

        } else {
            String[] comArs = line.split("\\s", 2);
            if (comArs[0].equals("done")) {
                if (comArs.length == 1) {
                    throw new DEIndex("done");
                }
                int index = Integer.parseInt(comArs[1]) - 1;
                if (index > size - 1) {
                    throw new DEIndex("done");
                }

                com = Commands.DONE;
                indexOfTaskAffected = index;

            } else if (comArs[0].equals("delete")) {
                if (comArs.length == 1) {
                    throw new DEIndex("delete");
                }
                int index = Integer.parseInt(comArs[1]) - 1;
                if (index > size - 1) {
                    throw new DEIndex("delete");
                }

                com = Commands.DEL_TASK;
                indexOfTaskAffected = index;

            } else {
                if (comArs[0].equals("todo")) {
                    String details = line.substring(4, line.length());
                    if (details.isBlank()) {
                        throw new DEDescription("todo");
                    }
                    com = Commands.NEW_TASK;
                    newTask = new ToDo(details);

                } else if (comArs[0].equals("event")) {
                    String details = line.substring(5, line.length());
                    if (details.isBlank()) {
                        throw new DEDescription("event");
                    }
                    String[] msgDate = details.split(" /at ", 2);
                    if (msgDate.length == 1) {
                        throw new DEDate("event");
                    }

                    try {
                        LocalDate date = LocalDate.parse(msgDate[1]);
                        com = Commands.NEW_TASK;
                        newTask = new Event(msgDate[0], date);
                    } catch (DateTimeParseException e) {
                        throw new DukeExceptionDate();
                    }

                } else if (comArs[0].equals("deadline")) {
                    String details = line.substring(8, line.length());
                    if (details.isBlank()) {
                        throw new DEDescription("deadline");
                    }
                    String[] msgDate = details.split(" /by ", 2);
                    if (msgDate.length == 1) {
                        throw new DEDate("deadline");
                    }

                    try {
                        LocalDate date = LocalDate.parse(msgDate[1]);
                        com = Commands.NEW_TASK;
                        newTask = new Deadline(msgDate[0], date);
                    } catch (DateTimeParseException e) {
                        throw new DukeExceptionDate();
                    }

                } else if (comArs[0].equals("find")) {
                    com = Commands.FIND;
                    keyword = line.substring(4, line.length());

                } else {
                    throw new DECommand();
                }
            }
        }
    }

    public Task getTask() {
        return newTask;
    }

    public String getKeyWord() {
        return keyword;
    }

    public Commands getCommand() {
        return com;
    }

    public int getIndexAffected() {
        return indexOfTaskAffected;
    }
}
