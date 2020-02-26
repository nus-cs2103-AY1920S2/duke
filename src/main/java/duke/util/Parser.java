package duke.util;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class Parser {

    /**
     * Parses data from a given string and translates it into a respective task object.
     * @param s String in standardised saved form
     * @return task object that can be subsequently manipulated in program
     */
    public static Task parseDataFromFile(String s) {
        String taskType = s.substring(0, 1);
        String isTaskDone = s.substring(4, 5);
        String taskDesc = s.substring(8);
        String taskDateTime = "";
        Task parsedTask = null;
        if (!taskType.equals("T")) {
            String[] taskArr = taskDesc.split(",", 2);
            taskDesc = taskArr[0].trim();
            taskDateTime = taskArr[1].trim();
        }
        switch (taskType) {
        case "T":
            Todo t = new Todo(taskDesc);
            parsedTask = t;
            break;
        case "D":
            Deadline d = new Deadline(taskDesc, taskDateTime);
            parsedTask = d;
            break;
        case "E":
            Event e = new Event(taskDesc, taskDateTime);
            parsedTask = e;
            break;
        default:
            break;
        }
        return parsedTask;
    }

}
