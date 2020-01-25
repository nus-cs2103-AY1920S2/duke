import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Duchess {
    private ArrayList<Task> tasks;
    private Scanner scanner;

    Duchess() {
        this.tasks = new ArrayList<>(100);
        this.scanner = new Scanner(System.in);
    }

    private void awaitInput() {
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = scanner.nextLine();
                ArrayList<String> commands = new ArrayList<>(Arrays.asList(input.split("\\s", 2)));
                switch (this.getCommandType(commands.get(0))) {
                case TODO:
                case EVENT:
                case DEADLINE:
                    if (commands.size() < 2) {
                        throw new DuchessException(
                                "Your " + commands.get(0).trim() + " description cannot be empty!");
                    }
                    this.createTask(commands.get(0), commands.get(1));
                    break;
                case DONE:
                    this.completeTask(commands.get(1));
                    break;
                case DELETE:
                    this.deleteTask(commands.get(1));
                    break;
                case LIST:
                    this.printTasks();
                    break;
                case BYE:
                    this.sayGoodbye();
                    isRunning = false;
                    break;
                default:
                    // never reached, but here as good practice
                    echo(input);
                    break;
                }
            } catch (DuchessException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void introduce() {
        String logo = "\t _____             _\n"
                + "\t|  __ \\           | |\n"
                + "\t| |  | |_   _  ___| |__   ___  ___ ___\n"
                + "\t| |  | | | | |/ __| '_ \\ / _ \\/ __/ __|\n"
                + "\t| |__| | |_| | (__| | | |  __/\\__ \\__ \\\n"
                + "\t|_____/ \\__,_|\\___|_| |_|\\___||___/___/";
        System.out.println("\tHello from\n" + logo);
        System.out.println("\tMy name is Duchess, as you can see above.");
        System.out.println("\tHow may I help you?");
    }

    private void echo(String input) {
        System.out.println("\tOh? You said \"" + input + "\"? How interesting.");
        System.out.println("\tBut I don't see what I can do with that.");
    }

    private void printTasks() {
        if (this.tasks.size() > 0) {
            System.out.println("\tSighs... you never remember what you say, don't you.");
            System.out.println("\tYou said these:");
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println("\t\t" + (i + 1) + ".\t" + this.tasks.get(i));
            }
        } else {
            System.out.println("\tIs this a trick question? You have not told me anything about 'tasks'.");
        }
    }

    private void sayGoodbye() {
        System.out.println("\tBye, is it? Shoo shoo then.");
    }

    private CommandType getCommandType(String input) throws DuchessException {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException error) {
            throw new DuchessException(
                    "I don't see what I can do with what you just told me.");
        }
    }

    void run() {
        this.introduce();
        this.awaitInput();
    }

    private void createTask(String type, String description) throws DuchessException {
        switch (type) {
        case "todo":
            Task newTask = new ToDo(description.trim());
            this.addToTasks(newTask);
            break;
        case "event":
            ArrayList<String> details = new ArrayList<>(Arrays.asList(description.split("/at")));
            if (details.size() < 2) {
                throw new DuchessException(
                        "I don't know when is your event! Please use /at [time here].");
            }
            newTask = new Event(details.get(0).trim(), details.get(1).trim());
            this.addToTasks(newTask);
            break;
        case "deadline":
            details = new ArrayList<>(Arrays.asList(description.split("/by")));
            if (details.size() < 2) {
                throw new DuchessException(
                        "I don't know when is your deadline! Please use /by [deadline here].");
            }
            newTask = new Deadline(details.get(0).trim(), details.get(1).trim());
            this.addToTasks(newTask);
            break;
        default:
            break;
        }
    }

    private void addToTasks(Task task) {
        System.out.println("\tAs always, needing someone to keep track of things for you...");
        this.tasks.add(task);
        System.out.println("\t\t" + task);
        int size = this.tasks.size();
        System.out.println(
                "\tI've already tracked " + size + " " + (size == 1 ? "task" : "tasks") + " for you.");
    }

    private void deleteTask(String index) throws DuchessException {
        int indexAsInt = Integer.parseInt(index.trim());
        if (indexAsInt < 0 || indexAsInt > this.tasks.size()) {
            throw new DuchessException("You're referring to a task that does not exist!");
        } else {
            Task taskToComplete = this.tasks.get(indexAsInt - 1);
            System.out.println("\tGreat! One less thing for me to track for you.");
            System.out.println("\t\t" + taskToComplete);
            this.tasks.remove(indexAsInt - 1);
            int size = this.tasks.size();
            System.out.println(
                    "\tNow I'm tracking " + size + " " + (size == 1 ? "task" : "tasks") + " for you.");
        }
    }

    private void completeTask(String index) throws DuchessException {
        int indexAsInt = Integer.parseInt(index.trim());
        if (indexAsInt < 0 || indexAsInt > this.tasks.size()) {
            throw new DuchessException("You're referring to a task that does not exist!");
        } else {
            Task taskToComplete = this.tasks.get(indexAsInt - 1);
            taskToComplete.toggleIsCompleted();
            System.out.println("\tOh? You actually completed something? Impressive...");
            System.out.println("\t\t" + taskToComplete);
        }
    }
}
