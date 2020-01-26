import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Dude dude = new Dude();
        dude.start();
        Scanner sc = new Scanner(System.in);
        while (dude.isRunning()) {
            String userInput = sc.nextLine();
            dude.passInput(userInput);

        }
        sc.close();
    }
}

