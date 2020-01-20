import java.util.Scanner;

public class DukeAssistant {
    private boolean dukeActive = false;
    private final Scanner scanner = new Scanner(System.in);
    private String userInput = "";

    /**
     * Constructor for the DukeAssistant class.
     */
    public DukeAssistant() {
        this.dukeActive = true;
    }

    /**
     * This method is used to start the DukeAssistant prompt-and-reply
     * sequence.
     */
    public void run() {
        dukePrompt(new String[]{"Hello! I'm Duke", "What can I do for you?"});
        while (this.dukeActive) {
            readUserInput();

            switch (this.userInput) {
            case "bye":
                this.dukeActive = false;
                dukePrompt("Bye. Hope to see you again!");
                break;

            default:
                dukePrompt(this.userInput);
                break;
            }
        }
    }
    
    private void dukePrompt(String prompt) {
        System.out.println("Duke: " + prompt);
    }

    private void dukePrompt(String[] prompts) {
        for (int i = 0; i < prompts.length; i++) {
            if (i == 0) {
                System.out.println("Duke: " + prompts[i]);
            } else {
                System.out.println("      " + prompts[i]);
            }
        }
    }

    private void readUserInput() {
        System.out.printf("You:  ");
        this.userInput = scanner.nextLine();
    }
}