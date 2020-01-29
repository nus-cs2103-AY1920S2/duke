import java.util.Scanner;
import java.util.ArrayList;
public class Duke  {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }
    public void run() {
        Scanner sc = new Scanner(System.in);
        String[] userInput = sc.nextLine().split(" ",2);
        Parser parser = new Parser(userInput);
        String command = parser.getCommand();
        while (!command.equals("bye")) {
            try {
                ExceptionGenerator.checkInputLength(userInput);
                ExceptionGenerator.checkInputAction(userInput);
                switch (command) {
                    case "done":
                        int taskNo = Integer.parseInt(parser.getDescription());
                        this.tasks.getTask(taskNo - 1).markAsDone();
                        ui.transmitMessage("Nice! I've marked this task as done:\n "
                                + this.tasks.getTask(taskNo - 1).toString());
                        break;
                    case "delete":
                        taskNo = Integer.parseInt(userInput[1]) - 1;
                        Task removedTask = this.tasks.getTask(taskNo);
                        this.tasks.removeTask(taskNo);
                        ui.transmitMessage("Noted. I've removed this task:\n"
                                + removedTask.toString());
                        break;
                    case "list":
                        this.printText();
                        break;
                    case "todo":
                        String description = parser.getDescription();
                        ui.transmitMessage("Got it. I've added this task:");
                        this.tasks.add(new Task(description));
                        break;
                    case "deadline":
                        String[] tokens = parser.getDescription().split(" /by ");
                        ui.transmitMessage("Got it. I've added this task:");
                        this.tasks.add(new Deadlines(tokens[0], tokens[1]));
                        Deadlines d =  new Deadlines(tokens[0], tokens[1]);
                        ui.transmitMessage((d.getDate()).toString());
                        break;
                    case "event":
                        tokens = parser.getDescription().split(" /at ");
                        ui.transmitMessage("Got it. I've added this task:");
                        this.tasks.add(new Events(tokens[0], tokens[1]));

                        break;
                }
                int numbOfTask = this.tasks.taskStorage.size();
                if(numbOfTask > 0 && !command.equals("list") && !command.equals("delete") && !command.equals("done")) {
                    ui.transmitMessage(this.tasks.getTask(numbOfTask - 1).toString());
                }
                    ui.transmitMessage("Now you have " + numbOfTask + " tasks in the list.");
            } catch (DukeException ex){
                ex.printStackTrace();
            }
            userInput = sc.nextLine().split(" ", 2);
            parser = new Parser(userInput);
            command = parser.getCommand();
            storage.saveToDisk(this.tasks.taskStorage);
        }
        ui.initiateFareWell();
    }
    public void printText() {
        int counter = 1;
        for(Task task : this.tasks.taskStorage){
            ui.transmitMessage(Integer.valueOf(counter).toString() + "." + task.toString());
            counter++;
        }
    }
}