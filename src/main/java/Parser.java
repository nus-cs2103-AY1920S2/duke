import java.io.IOException;

/**
 * Processes user input
 */
public class Parser {

    /**
     * A list of Task objects to keep track of task changes
     */
    private TaskList taskList;

    /**
     * Creates a Parser object that is able to process user input
     *
     * @param taskList A list of task objects to keep track of task changes
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Recognises command prompts and does the appropriate task handling
     *
     * @param input A command prompt by the user to Duke
     * @return a string to be printed out on the duke application GUI
     */
    public String parse(String input) throws Exception {
        if (input.equals("list")) {
            return taskList.list();
        } else if (input.contains("done")) {
            try {
                int taskNum = Integer.parseInt(input.substring(5));
                assert taskNum <= taskList.size() : "Task number cannot be larger than list size";
                return taskList.done(taskNum);
            } catch (Exception e) {
                return "Please state a task number:)";
            }
        } else if (input.contains("delete")) {
            //Delete task
            try {
                int taskNum = Integer.parseInt(input.substring(7));
                assert taskNum <= taskList.size() : "Task number cannot be larger than list size";
                return taskList.delete(taskNum);
            } catch (Exception e) {
                return "Please state a proper delete command:(";
            }
        } else if (input.contains("find")) {
            try {
                String keyWord = input.substring(5);
                return taskList.find(keyWord);
            } catch (Exception e) {
                return "Where is your keyword:(";
            }
        } else if (input.equals ("bye")) {

            return "Cya soon:)";
        } else {
            //Create task using key words: "todo", "deadline", "event"
            if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
                if (taskList.containsDup(input)) {
                    return "This task has already been added before!";
                } else {
                    if (input.contains("todo")) {
                        //todo request format: todo<space><task>
                        return taskList.addTask("T", input);
                    } else if (input.contains("deadline")) {
                        //deadline request format: deadline<space><task></><yyyy-mm-dd>
                        return taskList.addTask("D", input);
                    } else {
                        //event request format: event<space><task></><yyyy-mm-dd><T><hh:mm-hh:mm>
                        return taskList.addTask("E", input);
                    }
                }
            } else {
                //must have todo/deadline/event request format
                return "Back at you!";
            }
        }
    }
}
