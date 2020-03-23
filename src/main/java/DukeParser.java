import java.util.Scanner;

public class DukeParser {

    Scanner scanner;
    TaskList tasks;

    public DukeParser(TaskList tasks) {
        this.scanner = new Scanner(System.in);
        this.tasks = tasks;
    }

    /**
     * parses user input as commands and generates a response.
     * @param command user input
     * @return Bot response after processing user's command
     */
    public String parseCommand(String command) {

        String[] commandBroken = command.split(" ",2);

        String action = commandBroken[0];

        try {

            if (action.equalsIgnoreCase("bye")) {
                return DukeUI.showByeMsg();

            } else if (action.equalsIgnoreCase("list")) {
                return tasks.printTasks();

            } else if (action.equalsIgnoreCase("done")) {

                String context = commandBroken[1];
                int taskNo = Integer.parseInt(context);

                return DukeUI.showDoneMsg(tasks.markDone(taskNo));

            } else if (action.equalsIgnoreCase("delete")) {

                String context = commandBroken[1];
                int taskNo = Integer.parseInt(context);
                return tasks.removeTask(taskNo);

            } else if (action.equalsIgnoreCase(("deadline"))) {

                String context = commandBroken[1];
                String[] contextBroken = context.split(" /by ", 2);
                Task tempTask = tasks.addTask(new Deadlines(contextBroken[0], false, contextBroken[1]));
                return DukeUI.showCreationMsg(tempTask);

            } else if (action.equalsIgnoreCase(("todo"))) {

                if (commandBroken.length == 1) {
                    throw new DukeException("THE DESCRIPTION OF TODO CANNOT BE EMPTY");
                }
                Task tempTask = tasks.addTask(new ToDos(commandBroken[1], false));
                return DukeUI.showCreationMsg(tempTask);

            } else if (action.equalsIgnoreCase(("event"))) {

                String context = commandBroken[1];
                String[] contextBroken = context.split(" /at ", 2);
                Task tempTask = tasks.addTask(new Events(contextBroken[0], false, contextBroken[1]));
                return DukeUI.showCreationMsg(tempTask);

            } else if (action.equalsIgnoreCase("find")) {

                int count = 1;
                for (Task t : tasks.getAllTasks()) {
                    if (t.containsSubstring(commandBroken[1])) {
                        System.out.println(count + "." + t);
                        count++;
                    }
                }
                return DukeUI.showFindMsg();
            } else if (action.equalsIgnoreCase("archive")) {

                String context = commandBroken[1];
                int taskNo = Integer.parseInt(context);
                return tasks.archiveTask(taskNo);

            } else if (action.equalsIgnoreCase("archiveall")) {

                return tasks.archiveAll();

            } else if (action.equalsIgnoreCase("showarchive")) {
                return tasks.showArchived();
            } else {
                return "UNABLE TO COMPREHEND";
            }

        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
        }
        return "";
    }
}
