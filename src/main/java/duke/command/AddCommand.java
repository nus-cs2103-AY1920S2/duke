package duke.command;

import duke.exceptions.Exceptions;
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
    public void executeCommand(String[] taskDescriptionArr) {

        try {

            Parser parser = new Parser();
            LocalDateTime[] dateTime = parser.parseDateTime(taskDescriptionArr[2], taskDescriptionArr[0]);

            if (taskDescriptionArr[0].equals("todo")) {

                System.out.println(HEADER);
                System.out.println(taskList.addTask(taskDescriptionArr[1], dateTime, Task.Types.ToDo));
                System.out.println(taskList.reportTotal());
                System.out.println(FOOTER);

            } else if (taskDescriptionArr[0].equals("deadline")) {

                System.out.println(HEADER);
                System.out.println(taskList.addTask(taskDescriptionArr[1], dateTime, Task.Types.Deadline));
                System.out.println(taskList.reportTotal());
                System.out.println(FOOTER);

            } else if (taskDescriptionArr[0].equals("event")) {

                System.out.println(HEADER);
                System.out.println(taskList.addTask(taskDescriptionArr[1], dateTime, Task.Types.Event));
                System.out.println(taskList.reportTotal());
                System.out.println(FOOTER);

            }
        } catch (Exception e) {

            if (e instanceof Exceptions) {

                System.out.println(((Exceptions) e).errorMessage());

            } else {

                System.out.println(e);

            }
        }
    }
}
