package duke;

/**
 * UI Class.
 * @author qiujingying
 * @version 1.0
 */
public class Eevee {

    /**
     * Initialises the scanner and Duke.
     * @param tasks task list
     * @param storage storage
     */
    public String startConversation(TaskList tasks, Storage storage, String command) {
        StringBuilder sb = new StringBuilder();

        Parser parser = new Parser(tasks, storage);
        if (command.equals("bye")) {
             sb.append("Bye! Hope to see you again soon!");
             return sb.toString();
        } else {
            try {
                sb.append(parser.record(command));
                return sb.toString();
            } catch (DukeException e) {
                return e.getMessage();
            }
        }

    }

    public void showLoadingError() {
        System.out.println("The text file is empty...");
    }

}
