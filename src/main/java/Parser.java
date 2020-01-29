import java.time.LocalDate;

/**
 * Parses user input.
 */
public class Parser {

    private Commands com;
    private int indexOfTaskAffected = -1;
    private Task newTask;

    /**
     * Decodes user input and generates a Parser object that is easier to understand.
     * com saves the type of Command that the user input.
     * indexOfTaskAffected saves the index for commands that require indexes.
     * newTask saves a Task for commands that create a new task.
     * @param line String to be decoded.
     * @param size Current number of tasks.
     * @throws DukeException If there was an invalid user input.
     */
    public Parser(String line, int size) throws DukeException {
        if (line.equals("bye")) {
            com = Commands.BYE;

        } else if (line.equals("list")) {
            com = Commands.LIST_TASKS;

        } else {
            String[] comArs = line.split("\\s", 2);
            if (comArs[0].equals("done")) {
                if (comArs.length == 1) {
                    throw new DukeExceptionIndex("done");
                }
                int index = Integer.parseInt(comArs[1]) - 1;
                if (index > size - 1) {
                    throw new DukeExceptionIndex("done");
                }

                com = Commands.DONE;
                indexOfTaskAffected = index;

            } else if (comArs[0].equals("delete")) {
                if (comArs.length == 1) {
                    throw new DukeExceptionIndex("delete");
                }
                int index = Integer.parseInt(comArs[1]) - 1;
                if (index > size - 1) {
                    throw new DukeExceptionIndex("delete");
                }

                com = Commands.DEL_TASK;
                indexOfTaskAffected = index;

            } else {
                if (comArs[0].equals("todo")) {
                    String details = line.substring(4, line.length());
                    if (details.isBlank()) {
                        throw new DukeExceptionDescription("todo");
                    }
                    com = Commands.NEW_TASK;
                    newTask = new ToDo(details);

                } else if (comArs[0].equals("event")) {
                    String details = line.substring(5, line.length());
                    if (details.isBlank()) {
                        throw new DukeExceptionDescription("event");
                    }
                    String[] msgDate = details.split(" /at ", 2);
                    if (msgDate.length == 1) {
                        throw new DukeExceptionDate("event");
                    }

                    LocalDate date = LocalDate.parse(msgDate[1]);
                    com = Commands.NEW_TASK;
                    newTask = new Event(msgDate[0], date);

                } else if (comArs[0].equals("deadline")) {
                    String details = line.substring(8, line.length());
                    if (details.isBlank()) {
                        throw new DukeExceptionDescription("deadline");
                    }
                    String[] msgDate = details.split(" /by ", 2);
                    if (msgDate.length == 1) {
                        throw new DukeExceptionDate("deadline");
                    }

                    LocalDate date = LocalDate.parse(msgDate[1]);
                    com = Commands.NEW_TASK;
                    newTask = new Deadline(msgDate[0], date);

                } else {
                    throw new DukeExceptionCommand();
                }
            }
        }
    }

    public Task getTask() {
        return newTask;
    }

    public Commands getCommand() {
        return com;
    }

    public int getIndexAffected() {
        return indexOfTaskAffected;
    }
}
