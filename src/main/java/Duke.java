import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> storage = new ArrayList<>();

    private void greet() {
        System.out.println("Greeting, traveler. My name is Andrew. What can I do for you?");
    }

    private void bidFarewell() {
        System.out.println("I shall not trouble you anymore. Farewell, partner.");
    }

    private void outputList() {
        for (int i = 0; i < storage.size(); i++) {
            System.out.printf("%d -%s\n", i + 1, storage.get(i));
        }
    }

    private void deleteTask(int i) {
        storage.remove(i);
    }

    private void completeTask(int i) {
        try {
            storage.get(i).setDone();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
                try {
                    storage.add(Task.generateTask(input));
                    System.out.printf("Added: %s\n", storage.get(storage.size() - 1));
                    System.out.printf("You now have %d tasks in your list\n", Task.getSize());
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();
        bot.run();
    }

}
