import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Squirtle squirtle = new Squirtle();
        Storage storage = new Storage("../data/tasks.txt");
        squirtle.start();
        squirtle.setStorage(storage);
        Scanner sc = new Scanner(System.in);
        while (squirtle.isRunning()) {
            String userInput = sc.nextLine();
            squirtle.passInput(userInput);

        }
        sc.close();
    }
}