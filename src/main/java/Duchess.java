import java.util.ArrayList;
import java.util.Scanner;

class Duchess {
    private ArrayList<Task> tasks;
    private Scanner scanner;

    Duchess() {
        this.tasks = new ArrayList<>(100);
        this.scanner = new Scanner(System.in);
    }

    private void awaitInput() {
        String input = scanner.nextLine();
        String[] commands = input.split("\\s", 2);
        switch (commands[0].toLowerCase()) {
        case "todo":
            Task newTask = new ToDo(commands[1].trim());
            this.addToTasks(newTask);
            break;
        case "event":
            String[] details = commands[1].split("/at");
            newTask = new Event(details[0].trim(), details[1].trim());
            this.addToTasks(newTask);
            break;
        case "deadline":
            details = commands[1].split("/by");
            newTask = new Deadline(details[0].trim(), details[1].trim());
            this.addToTasks(newTask);
            break;
        case "done":
            this.completeTask(commands[1]);
            break;
        case "list":
            this.printTasks();
            break;
        case "bye":
            this.sayGoodbye();
            break;
        default:
            echo(input);
            break;
        }
    }

    private void introduce() {
        String logo = "\t _____             _                   \n"
                + "\t|  __ \\           | |                  \n"
                + "\t| |  | |_   _  ___| |__   ___  ___ ___ \n"
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
        this.awaitInput();
    }

    private void addToTasks(Task task) {
        System.out.println("\tAs always, needing someone to keep track of things for you...");
        this.tasks.add(task);
        System.out.println("\t\t" + task);
        System.out.println("\tI've already tracked " + this.tasks.size() + " tasks for you.");
        this.awaitInput();
    }

    private void printTasks() {
        System.out.println("\tSighs... you never remember what you say, don't you.");
        System.out.println("\tYou said these:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ".\t" + this.tasks.get(i));
        }
        this.awaitInput();
    }

    private void sayGoodbye() {
        System.out.println("\tBye, is it? Shoo shoo then.");
    }

    void run() {
        this.introduce();
        this.awaitInput();
    }

    private void completeTask(String index) {
        int indexAsInt = Integer.parseInt(index.trim());
        if (indexAsInt < 0 || indexAsInt > this.tasks.size()) {
            throw new IllegalArgumentException("The index given is out of bounds!");
        } else {
            Task taskToComplete = this.tasks.get(indexAsInt - 1);
            taskToComplete.toggleIsCompleted();
            System.out.println("\tOh? You actually completed something? Impressive...");
            System.out.println("\t\t" + taskToComplete);
            this.awaitInput();
        }
    }
}
