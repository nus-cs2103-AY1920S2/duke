import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {

    public Ui() {

    }


    /**
     * Method displays the welcome message upon starting the program
     *
     */

    public void showWelcome() {
        String welcome = "OwO Hello! I am your neckbeard chatbot! \n" + "What can I do for you senpai? *sweats profusely*";
        System.out.println(welcome);
    }

    /**
     * Method is the main method in which the user input is read by
     * the Duke program. Method makes use of the Parser class to parse
     * the inputs and make sense of them.
     *
     * Method also checks if the input is bye, which is the case returns
     * the necessary output and terminates the program
     *
     */

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

    /**
     * Method returns the error in the case that the text file in
     * the taskList directory does not exist
     *
     */

    public void showLoadingError() {

        System.out.println("Task file does not exist!");
    }



}
