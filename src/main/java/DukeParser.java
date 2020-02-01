import java.util.Scanner;

public class DukeParser {

    Scanner scanner;
    UserText tasks;

    public DukeParser(UserText tasks) {
        this.scanner = new Scanner(System.in);
        this.tasks = tasks;
    }

    public UserText parseCommand() {

        while(true) {
            String command = scanner.nextLine();
            String[] command_broken = command.split(" ",2);

            String action = command_broken[0];

            try {

                if (action.equalsIgnoreCase("bye")) {
                    DukeUI.showByeMsg();
                    break;

                } else if (action.equalsIgnoreCase("list")) {
                    tasks.printTasks();

                } else if (action.equalsIgnoreCase("done")) {
                    DukeUI.showDoneMsg();
                    String context = command_broken[1];
                    int taskNo = Integer.parseInt(context);
                    tasks.markDone(taskNo);

                } else if (action.equalsIgnoreCase("delete")) {
                    DukeUI.showDeleteMsg();
                    String context = command_broken[1];
                    int taskNo = Integer.parseInt(context);
                    tasks.removeTask(taskNo);

                } else if (action.equalsIgnoreCase(("deadline"))) {
                    DukeUI.showCreationMsg();
                    String context = command_broken[1];
                    String[] context_broken = context.split(" /by ", 2);
                    tasks.addInput(new Deadlines(context_broken[0], false ,context_broken[1]));

                } else if (action.equalsIgnoreCase(("todo"))) {
                    DukeUI.showCreationMsg();
                    if (command_broken.length == 1) {
                        throw new DukeException("Ooops! The description of a ToDo cannot be empty.");
                    }
                    tasks.addInput(new ToDos(command_broken[1], false));

                } else if (action.equalsIgnoreCase(("event"))) {
                    DukeUI.showCreationMsg();
                    String context = command_broken[1];
                    String[] context_broken = context.split(" /at ", 2);
                    tasks.addInput(new Events(context_broken[0], false, context_broken[1]));

                } else if (action.equalsIgnoreCase("find")) {
                    DukeUI.showFindMsg();
                    int count = 1;
                    for (Task t: tasks.getAllTasks()) {
                        if (t.containsSubstring(command_broken[1])) {
                            System.out.println(count + "." + t);
                            count++;
                        }
                    }

                } else {
                    throw new DukeException("Ooops! I'm sorry, i don't know what it means");
                }

            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return this.tasks;
    }
}
