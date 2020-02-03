package duke.ui;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exceptions.Exceptions;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The Ui program gets user input, interprets it and calls relevant methods.
 *
 * @version 1.0
 * @since 2020-01-28
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
     */
    public void frontDesk() {

        final String HEADER = "____________________________________________________________\n";
        final String FOOTER = "____________________________________________________________\n";

        try {
            String greetings = HEADER
                    + "\nHello! I'm Chu Chu \n"
                    + "What can I do for you ? \n"
                    + FOOTER;
            System.out.println(greetings);

            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(rd);
            String input = null;
            String[] taskDescriptionArr = null;
            Parser parser = new Parser();

            while (true) {

                try {
                    input = br.readLine();
                    taskDescriptionArr = parser.parseUserInput(input);
                    Command c = null;

                    if (taskDescriptionArr[0].equals("bye")) {

                        c = new ExitCommand(null, null);
                        c.executeCommand(taskDescriptionArr);
                        break;

                    } else if (taskDescriptionArr[0].equals("list")) {

                        c = new ListCommand(null, taskList);
                        c.executeCommand(taskDescriptionArr);

                    } else if (taskDescriptionArr[0].equals("done")) {

                        c = new DoneCommand(storage, taskList);
                        c.executeCommand(taskDescriptionArr);

                    } else if (taskDescriptionArr[0].equals("delete")) {

                        c = new DeleteCommand(storage, taskList);
                        c.executeCommand(taskDescriptionArr);

                    } else if (taskDescriptionArr[0].equals("find")) {

                        c = new FindCommand(null, taskList);
                        c.executeCommand(taskDescriptionArr);

                    } else {

                        c = new AddCommand(storage, taskList);
                        c.executeCommand(taskDescriptionArr);
                    }
                } catch (Exception e) {

                    if (e instanceof Exceptions) {

                        System.out.println(((Exceptions) e).errorMessage());

                    } else {

                        System.out.println(e);

                    }
                    continue;

                }
            }

        } catch (Exception e) {

            System.out.print(e);

        }


    }


}
