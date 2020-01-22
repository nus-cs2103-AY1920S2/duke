import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> storage = new ArrayList<>();

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();
        bot.run();

    }

    private void greet() {
        System.out.println("Greeting, traveler. My name is Andrew. What can I do for you?");
    }

    private void bidFarewell() {
        System.out.println("I shall not trouble you anymore. Farewell, partner.");
    }

    private void outputList() {
        for (int i = 0; i < storage.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, storage.get(i).toString());
        }
    }

    private void deleteTask(int i) {
        storage.remove(i);
    }

    private void completeTask(int i) {
        storage.get(i).setDone();
    }

    private void run() {
        Scanner scan = new Scanner(System.in);
        label:
        while (scan.hasNext()) {
            String input = scan.nextLine();
            String[] inputArray = input.split("\\s");
            switch (inputArray[0]) {
                case "bye":
                    this.bidFarewell();
                    break label;
                case "list":
                    System.out.println("Thy list is here:");
                    this.outputList();
                    break;
                case "done":
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    this.completeTask(index);
                    System.out.printf("Task successfully completed: \n\t %s\n", storage.get(index));
                    break;
                default:
                    storage.add(new Task(input));
                    System.out.printf("Added: %s\n", input);
                    break;
            }
        }
    }
}
