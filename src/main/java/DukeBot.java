import java.util.ArrayList;
import java.util.List;

/**
 * The DukeBot class. It contains methods available for DukeBot to use, and also stores content that DukeBot needs.
 */

public class DukeBot {

    private List<String> wordList;
    private boolean isActive;

    /**
     * The constructor method of DukeBot. It initialises our list of words, and sets the bot to an "active" state.
     */
    public DukeBot() {
        wordList = new ArrayList<String>();
        isActive = true;
    }

    /**
     * Process command method of DukeBot. It processes the user input from the main class and the logic behind each
     * command is carried out here.
     * @param command A String object that is the command as entered by the user.
     */
    public void processCommand(String command) {
        switch(command) {
            case "bye":
                dukeBye();
                break;
            case "list":
                printWordList();
                break;
            default:
                addString(command);
        }
    }

    /**
     * DukeBot's hello command. Used to introduce itself.
     */
    public void dukeHi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm your personal chat bot assistant! How may I be of service today?");
    }

    /**
     * DukeBot's goodbye command. Used to disable DukeBot.
     */
    public void dukeBye() {
        System.out.println("Bye! Hope to see you again soon :)");
        disableDuke();
    }

    /**
     * DukeBot's add String command. It adds a String to the word list for storage.
     * @param string A String object to be stored in DukeBot's word list.
     */
    public void addString(String string) {
        wordList.add(string);
        System.out.println("added: " + string);
    }

    /**
     * DukeBot's print word list command. It prints all the words in the word list in the order they were added.
     */
    public void printWordList() {
        for(int i = 0; i < wordList.size(); i++) {
            System.out.println((i+1) + ". " + wordList.get(i));
        }
    }

    /**
     * DukeBot's disable command. It sets the boolean "isActive" to false to disable the bot.
     */
    public void disableDuke() {
        isActive = false;
    }

    /**
     * DukeBot's isActive command. It checks whether the bot is active or not.
     * @return Returns a boolean value, true if the bot is active and false if it is not.
     */
    public boolean isActive() {
        return this.isActive;
    }
}
