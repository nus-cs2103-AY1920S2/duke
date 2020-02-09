package duke.command;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.time.LocalDateTime;

/**
 * The AddCommand program call method to add task into the taskList.
 *
 * @version 1.1
 * @since 3/2/2020
 */
public class AddCommand extends Command {

    /**
     * Constructor.
     *
     * @param storage  refers to file storage.
     * @param taskList refers a taskList object that manages the current list of tasks.
     */
    public AddCommand(Storage storage, TaskList taskList) {

        super(storage, taskList);

    }

    /**
     * Calls method to add task into taskList.
     *
     * @param taskDescriptionArr is a parsed task description which
     *                           contains information such as date
     *                           of task.
     */
    @Override
    public String executeCommand(String[] taskDescriptionArr) {

        try {

            Parser parser = new Parser();
            LocalDateTime[] dateTime = parser.parseDateTime(taskDescriptionArr[2], taskDescriptionArr[0]);
            String start = "Got it. I've added this task: \n";

            if (taskDescriptionArr[0].equals("todo")) {

                return start + taskList.addTask(taskDescriptionArr[1],
                        dateTime, Task.Types.TODO) + "\n" + taskList.reportTotal();

            } else if (taskDescriptionArr[0].equals("deadline")) {

                return start + taskList.addTask(taskDescriptionArr[1],
                        dateTime, Task.Types.DEADLINE) + "\n" + taskList.reportTotal();

            } else if (taskDescriptionArr[0].equals("event")) {

                return start + taskList.addTask(taskDescriptionArr[1],
                        dateTime, Task.Types.EVENT) + "\n" + taskList.reportTotal();

            }

        } catch (Exception e) {

            return e.toString();

            }

        return "";
    }
}
