package duke;

import duke.tasks.*;

public class Parser {

    /**
     * Parses the command the user enters and execute it
     * @param tasks TaskList that stores all the current tasks
     * @param ui UI for interaction with the user
     * @return boolean. true indicates that the user has terminated the program (by typing "bye")
     * @throws DukeException for invalid user commands
     */
    public static boolean parse(TaskList tasks, Ui ui) throws DukeException {

        String[] cmdString = ui.getUserCmd()
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
            System.out.printf("\tMortal, the following task has been deleted:\n\t  %s\n\tYou have %d tasks left\n",
                    tasks.removeTask(taskIndex), tasks.size());

        } else if (cmdString[0].equalsIgnoreCase("todo")) { //todo
            if (cmdString.length == 1) {
                throw new DukeException("\tA \"todo\" task must have a description, mortal!");
            }
            tasks.addTask(new ToDo(cmdString[1]));
            System.out.printf("\tTask added: \n\t  %s\n\tYou have %d tasks now, mortal\n",
                    tasks.getTask(tasks.size() - 1), tasks.size());

        } else if (cmdString[0].equalsIgnoreCase("deadline")) { //deadline
            if (cmdString.length == 1) {
                throw new DukeException("\tA \"deadline\" task must have a description and a deadline, mortal!");
            }
            String[] byString = cmdString[1].split("/by ", 2);
            if (byString.length == 1) {
                throw new DukeException("\tA \"deadline\" task must have a deadline, mortal!");
            }
            tasks.addTask(new Deadline(byString[0], byString[1]));
            System.out.printf("\tTask added: \n\t  %s\n\tYou have %d tasks now, mortal\n",
                    tasks.getTask(tasks.size() - 1), tasks.size());

        } else if (cmdString[0].equalsIgnoreCase("event")) { //event
            if (cmdString.length == 1) {
                throw new DukeException("\tAn \"event\" task must have a description and a time, mortal!");
            }
            String[] atString = cmdString[1].split("/at ", 2);
            if (atString.length == 1) {
                throw new DukeException("\tAn \"event\" task must have a time, mortal!");
            }
            tasks.addTask(new Event(atString[0], atString[1]));
            System.out.printf("\tTask added: \n\t  %s\n\tYou have %d tasks now, mortal\n",
                    tasks.getTask(tasks.size() - 1), tasks.size());

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

        } else {
            throw new DukeException("\tMortal, that's an invalid Task!");

        }

        return false;
    }
}
