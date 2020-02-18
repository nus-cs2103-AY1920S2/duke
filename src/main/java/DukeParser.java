import java.util.Scanner;

public class DukeParser {

    Scanner scanner;
    TaskList tasks;

    public DukeParser(TaskList tasks) {
        this.scanner = new Scanner(System.in);
        this.tasks = tasks;
    }

    /**
     *
     * @param command
     * @return Bot response after processing user's command
     */
    public String parseCommand(String command) {

            String[] command_broken = command.split(" ",2);

            String action = command_broken[0];

            try {

                if (action.equalsIgnoreCase("bye")) {
                    return DukeUI.showByeMsg();

                } else if (action.equalsIgnoreCase("list")) {
                    return tasks.printTasks();

                } else if (action.equalsIgnoreCase("done")) {

                    String context = command_broken[1];
                    int taskNo = Integer.parseInt(context);

                    return DukeUI.showDoneMsg(tasks.markDone(taskNo));

                } else if (action.equalsIgnoreCase("delete")) {

                    String context = command_broken[1];
                    int taskNo = Integer.parseInt(context);
                    return tasks.removeTask(taskNo);

                } else if (action.equalsIgnoreCase(("deadline"))) {

                    String context = command_broken[1];
                    String[] context_broken = context.split(" /by ", 2);
                    Task tempTask = tasks.addTask(new Deadlines(context_broken[0], false ,context_broken[1]));
                    return DukeUI.showCreationMsg(tempTask);

                } else if (action.equalsIgnoreCase(("todo"))) {

                    if (command_broken.length == 1) {
                        throw new DukeException("THE DESCRIPTION OF TODO CANNOT BE EMPTY");
                    }
                    Task tempTask = tasks.addTask(new ToDos(command_broken[1], false));
                    return DukeUI.showCreationMsg(tempTask);

                } else if (action.equalsIgnoreCase(("event"))) {

                    String context = command_broken[1];
                    String[] context_broken = context.split(" /at ", 2);
                    Task tempTask = tasks.addTask(new Events(context_broken[0], false, context_broken[1]));
                    return DukeUI.showCreationMsg(tempTask);

                } else if (action.equalsIgnoreCase("find")) {

                    int count = 1;
                    for (Task t : tasks.getAllTasks()) {
                        if (t.containsSubstring(command_broken[1])) {
                            System.out.println(count + "." + t);
                            count++;
                        }
                    }
                    return DukeUI.showFindMsg();
                } else if (action.equalsIgnoreCase("archive")) {

                    String context = command_broken[1];
                    int taskNo = Integer.parseInt(context);
                    return tasks.archiveTask(taskNo);

                } else if (action.equalsIgnoreCase("archiveall")) {

                    return tasks.archiveAll();

                } else if (action.equalsIgnoreCase("showarchive")){
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
