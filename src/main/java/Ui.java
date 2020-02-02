import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {

    public Ui() {

    }

    public void showWelcome() {
        String welcome = "OwO Hello! I am your neckbeard chatbot! \n" + "What can I do for you senpai? *sweats profusely*";
        System.out.println(welcome);
    }


    public void readCommands() {

        Scanner input = new Scanner(System.in);
        Parser parser = new Parser();


        while(input.hasNextLine()) {

            String userInput = input.nextLine();

            if (userInput.equals("bye")) {
                parser.parse(userInput);
                break;

            } else {
                parser.parse(userInput);
            }

        }
    }

    public void showLoadingError() {

        System.out.println("Task file does not exist!");
    }



}
