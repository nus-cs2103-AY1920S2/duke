/**
 * Processes user input
 */
public class Parser {

    /** A list of Task objects to keep track of task changes*/
    private TaskList taskList;

    /**
     * Creates a Parser object that is able to process user input
     *
     * @param taskList A list of task objects to keep track of task changes
     */
    public Parser (TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Recognises command prompts and does the appropriate task handling
     *
     * @param input A command prompt by the user to Duke
     */
    public void parse (String input) {
        if (input.equals ("list")) {
            taskList.list();
        } else if (input.contains ("done")) {
            try {
                int taskNum = Integer.parseInt (input.substring(5));
                taskList.done(taskNum);
            } catch (Exception e) {
                System.out.println ("Please state a task number:)");
            }
        } else if (input.contains ("delete")) {
            //Delete task
            try {
                int taskNum = Integer.parseInt(input.substring(7));
                taskList.delete(taskNum);
            } catch (Exception e) {
                System.out.println ("Please state a proper delete command:(");
            }
        } else if (input.contains ("find")) {
            try {
                String keyWord = input.substring(5);
                taskList.find (keyWord);
            } catch (Exception e) {
                System.out.println ("Where is your keyword:(");
            }
        } else {
            //Create task using key words: "todo", "deadline", "event"
            if (input.contains ("todo")) {
                //todo request format: todo<space><task>
                taskList.addTask ("T", input);
            } else if (input.contains ("deadline")) {
                //deadline request format: deadline<space><task></><yyyy-mm-dd>
                taskList.addTask ("D", input);
            } else if (input.contains ("event")) {
                //event request format: event<space><task></><yyyy-mm-dd><T><hh:mm-hh:mm>
                taskList.addTask ("E", input);
            } else {
                //must have todo/deadline/event request format
                System.out.println ("Im sorry, but I do not understand what this means:-(");
            }
        }
    }
}
