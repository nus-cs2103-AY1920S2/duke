package duke;

import duke.tasks.*;

import java.util.Scanner;

public class Parser {

    /**
     * Directs input command from System.in to the parse method.
     *
     * @param tasks TaskList that stores all the current tasks
     * @param ui UI for interaction with the user
     * @return boolean. true indicates that the user has terminated the program (by typing "bye")
     * @throws DukeException for invalid user commands
     */
    public static boolean parseNextCmd(TaskList tasks, Ui ui) throws DukeException {
        return parse(tasks, ui, ui.getUserCmd());
    }


    /**
     * Directs input command from the GUI to the parse method.
     *
     * @param tasks TaskList that stores all the current tasks
     * @param ui UI for interaction with the user
     * @param userCmd String input from GUI
     * @return boolean. true indicates that the user has terminated the program (by typing "bye")
     * @throws DukeException for invalid user commands
     */
    public static boolean parseNextCmd(TaskList tasks, Ui ui, String userCmd) throws DukeException {
        return parse(tasks, ui, userCmd);
    }


    /**
     * Parses the command the user enters and execute it.
     *
     * @param tasks TaskList that stores all the current tasks
     * @param ui UI for interaction with the user
     * @return boolean. true indicates that the user has terminated the program (by typing "bye")
     * @throws DukeException for invalid user commands
     */
    private static boolean parse(TaskList tasks, Ui ui, String userCmd) throws DukeException {

        String[] cmdString = userCmd
                .strip()
                .split(" ", 2);

        if (cmdString[0].equalsIgnoreCase("bye")) { //bye
            return true;

        } else if (cmdString[0].equalsIgnoreCase("list")) { //list

            if (tasks.size() == 0) {
                ui.cathulhuSays("\tYou are free now, mortal.");
            } else {
                ui.cathulhuSays("\tHere are your tasks, mortal:\n");
                for (int i = 1; i <= tasks.size(); i++) {
                    ui.cathulhuSays("\t" + i + ". " + tasks.getTask(i - 1));
                }
            }

        } else if (cmdString[0].equalsIgnoreCase("done")) { //done
            if (cmdString.length == 1) {
                throw new DukeException("\tWhich task do you want to mark as done, mortal!");
            }
            int taskIndex = Integer.parseInt(cmdString[1]) - 1;
            if (tasks.size() <= taskIndex) {
                throw new DukeException("\tMortal, thou have no such task!");
            }
            tasks.getTask(taskIndex).markAsDone();
            ui.cathulhuSays("\tMortal, thou have completed this task:");
            ui.cathulhuSays("\t  " + tasks.getTask(taskIndex));

        } else if (cmdString[0].equalsIgnoreCase("delete")) { //delete
            if (cmdString.length == 1) {
                throw new DukeException("\tWhich task do you want to delete, mortal!");
            }
            int taskIndex = Integer.parseInt(cmdString[1]) - 1;
            if (tasks.size() <= taskIndex) {
                throw new DukeException("\tMortal, thou have no such task!");
            }
            ui.cathulhuSays("\tMortal, the following task has been deleted:\n\t  "
                    + tasks.removeTask(taskIndex)
                    + "\n\tYou have "
                    + tasks.size()
                    + " tasks left\n");

        } else if (cmdString[0].equalsIgnoreCase("todo")) { //todo
            if (cmdString.length == 1) {
                throw new DukeException("\tA \"todo\" task must have a description, mortal!");
            }
            tasks.addTask(new ToDo(cmdString[1]));
            ui.cathulhuSays("\tTask added: \n\t  "
                    + tasks.getTask(tasks.size() - 1)
                    + "\n\tYou have "
                    + tasks.size()
                    + " tasks now, mortal\n");

        } else if (cmdString[0].equalsIgnoreCase("deadline")) { //deadline
            if (cmdString.length == 1) {
                throw new DukeException("\tA \"deadline\" task must have a description and a deadline, mortal!");
            }
            String[] byString = cmdString[1].split("/by ", 2);
            if (byString.length == 1) {
                throw new DukeException("\tA \"deadline\" task must have a deadline, mortal!");
            }
            tasks.addTask(new Deadline(byString[0], byString[1]));
            ui.cathulhuSays("\tTask added: \n\t  "
                    + tasks.getTask(tasks.size() - 1)
                    + "\n\tYou have "
                    + tasks.size()
                    + " tasks now, mortal\n");

        } else if (cmdString[0].equalsIgnoreCase("event")) { //event
            if (cmdString.length == 1) {
                throw new DukeException("\tAn \"event\" task must have a description and a time, mortal!");
            }
            String[] atString = cmdString[1].split("/at ", 2);
            if (atString.length == 1) {
                throw new DukeException("\tAn \"event\" task must have a time, mortal!");
            }
            tasks.addTask(new Event(atString[0], atString[1]));
            ui.cathulhuSays("\tTask added: \n\t  "
                    + tasks.getTask(tasks.size() - 1)
                    + "\n\tYou have "
                    + tasks.size()
                    + " tasks now, mortal\n");

        } else if (cmdString[0].equalsIgnoreCase("find")) { //find
            if (cmdString.length == 1) {
                throw new DukeException("\tA \"find\" task must have a keyword to search, mortal!");
            }
            ui.cathulhuSays("\t  Here are the matching tasks in your list, mortal:");
            int numMatches = 0;
            for (int i = 0; i < tasks.size(); i++) {
                String taskString = tasks.getTask(i).toString();
                if (taskString.contains(cmdString[1])) {
                    numMatches++;
                    ui.cathulhuSays("\t  " + String.valueOf(numMatches) + "." + taskString);
                }
            }
            if (numMatches==0) {
                ui.cathulhuSays("\t  You have no matching task, mortal.");
            }

        } else if (cmdString[0].equalsIgnoreCase("update")) { //update
            if (cmdString.length == 1) {
                throw new DukeException("\tWhat do you want to update, mortal?");
            }
            int taskPos = new Scanner(cmdString[1]).useDelimiter("\\D+").nextInt() + 1;
            if (!cmdString[1].contains("=") ) {
                throw new DukeException("Attributes to update in the task must be comma separated, " +
                        "in the following format: attributeName1=newValue1, attributeName2=newValue2, ...");
            }
            String[] updateStringArr = cmdString[1].substring(cmdString[1].indexOf(String.valueOf(taskPos)))
                    .split(",");
            String originalTask = tasks.getTask(taskPos).toString();
            String updatedTask = tasks.getTask(taskPos).update(updateStringArr).toString();
            ui.cathulhuSays("\t  The following task has been changed: \n\t  From: "
                    + originalTask + "\n\t  to: " + updatedTask);

        } else {
            throw new DukeException("\tMortal, that's an invalid Task!");

        }
        return false;
    }
}
