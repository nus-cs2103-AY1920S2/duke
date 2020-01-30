import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private int drunkCounter = 0;
    private ArrayList<Task> storage;

    private void greet() {
        System.out.println("Greeting, traveler. My name is Andrew. What can I do for you?");
    }

    private void bidFarewell() {
        System.out.println("I shall not trouble you anymore. Farewell, partner.");
    }

    private void outputList() {
        storage = Storage.generateTaskList();
        for (int i = 0; i < storage.size(); i++) {
            System.out.printf("%d -%s\n", i + 1, storage.get(i));
        }
    }

    private void deleteTask(int i) {
        try {
            storage = Storage.generateTaskList();
            System.out.printf("Your burden has been lifted, removed: \n\t %s\n", storage.get(i));
            storage.remove(i);
            Storage.writeTask(storage);
            System.out.printf("You now have %d tasks in your list\n", storage.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
    private void completeTask(int i) {
        try {
            storage = Storage.generateTaskList();
            storage.get(i).setDone();
            System.out.printf("Task successfully completed: \n\t %s\n", storage.get(i));
            System.out.printf("You now have %d tasks in your list\n", storage.size());
            Storage.writeTask(storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void run() {
        Scanner scan = new Scanner(System.in);
        label:
        try {
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
                    break;
                case "delete":
                    int ind = Integer.parseInt(inputArray[1]) - 1;
                    this.deleteTask(ind);
                    break;
                default:
                    try {
                        storage = Storage.generateTaskList();
                        storage.add(Task.generateTask(input));
                        int storageSize = storage.size();
                        System.out.printf("Added: %s\n", storage.get(storageSize - 1));
                        System.out.printf("You now have %d tasks in your list\n", storageSize);
                        Storage.writeTask(storage);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Please give me a sign. A sign. A number.");
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        Storage.generateTaskList();
        bot.greet();
        bot.run();
    }

}
