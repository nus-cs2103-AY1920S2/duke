import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;

public class TriviaCommand extends Command {
    private ArrayList<String> triviaList;

    public TriviaCommand(String input) {
        super(input);
        this.triviaList = new ArrayList<>();
        populateTriviaList(this.triviaList);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder("Did you know?\n");
        int randomNumber = getRandomNumber();
        String randomTrivia = triviaList.get(randomNumber);
        sb.append(randomTrivia);
        return sb.toString();
    }

    private void populateTriviaList(ArrayList<String> triviaList) {
        // Create an array of 10 trivia
        String[] triviaArr = new String[10];
        // add trivia 1
        triviaArr[0] = "The 100 folds in a chef's hat represent 100 ways to cook an egg!";
        // add trivia 2
        triviaArr[1] = "The unicorn is the national animal of Scotland!";
        // add trivia 3
        triviaArr[2] = "The odds of getting a royal flush is exactly 1/649740!";
        // add trivia 4
        triviaArr[3] = "A baby puffin is called a puffling.";
        // add trivia 5
        triviaArr[4] = "Coca-Cola was the first soft drink in space!";
        // add trivia 6
        triviaArr[5] = "The speed of a computer mouse is measured in 'Mickeys'";
        // add trivia 7
        triviaArr[6] = "About 700 grapes go into one bottle of wine.";
        // add trivia 8
        triviaArr[7] = "Fear of the number 13 is called triskaidekaphobia.";
        // add trivia 9
        triviaArr[8] = "A $1 bill costs 5 cents to make.";
        // add trivia 10
        triviaArr[9] = "The coldest temperature ever recorded was -98 degrees Celsius!";
        Collections.addAll(triviaList, triviaArr);
    }

    private int getRandomNumber() {
        return (int) (Math.random() * 10);
    }
}
