import java.io.IOException;

public class Parser {

    private TaskList taskList;

    public Parser (TaskList taskList) {
        this.taskList = taskList;
    }

    public void parse(String input) throws IOException {
        if (input.equals("list")) {
            taskList.list();
        } else if (input.contains("done")) {
            try {
                int taskNum = Integer.parseInt(input.substring(5));
                taskList.done(taskNum);
            } catch (Exception e) {
                System.out.println ("Please state a task number:)");
            }
        } else if (input.contains("delete")) {
            //Delete task
            int taskNum = Integer.parseInt(input.substring(7));
            taskList.delete(taskNum);
        } else {
            //Create task using key words: "todo", "deadline", "event"
            if (input.contains("todo")) {
                //todo request format: todo<space><task>
                taskList.addTask("T", input);
            } else if (input.contains("deadline")) {
                //deadline request format: deadline<space><task></><yyyy-mm-dd>
                taskList.addTask ("D", input);
            } else if (input.contains("event")) {
                //event request format: event<space><task></><yyyy-mm-dd><T><hh:mm-hh:mm>
                taskList.addTask ("E", input);
            } else {
                //must have todo/deadline/event request format
                System.out.println("Im sorry, but I do not understand what this means:-(");
            }
        }
    }


}
