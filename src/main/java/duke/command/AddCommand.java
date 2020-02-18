package duke.command;

import duke.exceptions.DuplicateTaskError;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.time.LocalDateTime;

/**
 * The AddCommand program call method to add task into the taskList.
 *
 * @version 1.2
 * @since 17/2/2020
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

            boolean hasDup = new CheckDupCommand(null, taskList).executeCommand(taskDescriptionArr, dateTime);

            if (hasDup) {

                throw new DuplicateTaskError(taskDescriptionArr[0]);
            }

            String start = "Got it. I've added this task: \n";

            if (taskDescriptionArr[0].equals("T")) {

                return start + taskList.addTask(taskDescriptionArr[1],
                        dateTime, Task.Types.T) + "\n" + taskList.reportTotal();

            } else if (taskDescriptionArr[0].equals("D")) {

                return start + taskList.addTask(taskDescriptionArr[1],
                        dateTime, Task.Types.D) + "\n" + taskList.reportTotal();

            } else if (taskDescriptionArr[0].equals("E")) {

                return start + taskList.addTask(taskDescriptionArr[1],
                        dateTime, Task.Types.E) + "\n" + taskList.reportTotal();

            }

        } catch (Exception e) {

            return e.toString();

        }

        return "";
    }
}
