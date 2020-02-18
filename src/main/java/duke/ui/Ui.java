package duke.ui;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * The Ui program gets user input, interprets it and calls relevant methods.
 *
 * @version 1.1
 * @since 4/2/2020
 */
public class Ui {

    Storage storage;
    TaskList taskList;

    /**
     * Constructor.
     *
     * @param taskList refers to TaskList object required to manage the taskList.
     */
    public Ui(Storage storage, TaskList taskList) {

        this.storage = storage;
        this.taskList = taskList;

    }

    /**
     * Takes in user input and calls relevant method.
     *
     * @param input is user input.
     * @return message relevant to command.
     */
    public String frontDesk(String input) {

        String[] taskDescriptionArr;
        Parser parser = new Parser();

        try {

            taskDescriptionArr = parser.parseUserInput(input);
            Command c;

            if (taskDescriptionArr[0].equals("bye")) {

                c = new ExitCommand(null, null);

            } else if (taskDescriptionArr[0].equals("list")) {

                c = new ListCommand(null, taskList);

            } else if (taskDescriptionArr[0].equals("done")) {

                c = new DoneCommand(storage, taskList);

            } else if (taskDescriptionArr[0].equals("delete")) {

                c = new DeleteCommand(storage, taskList);

            } else if (taskDescriptionArr[0].equals("find")) {

                c = new FindCommand(null, taskList);

            } else {

                assert taskDescriptionArr[0] == "E"
                        || taskDescriptionArr[0] == "T"
                        || taskDescriptionArr[0] == "D" : taskDescriptionArr[0];

                c = new AddCommand(storage, taskList);
            }

            return c.executeCommand(taskDescriptionArr);

        } catch (Exception e) {

            return e.toString();

        }

    }


}


