package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.util.Scanner;

// Solution below adapted from https://github.com/Amoscheong97/duke/blob/master/src/main/java/DukeCommand.java
public enum DukeCommand {

    BYE {
        @Override
        public boolean executeCommand(TaskList tasks, Ui ui, String cmdString) {
            return true;
        }
    },

    LIST {
        @Override
        public boolean executeCommand(TaskList tasks, Ui ui, String cmdString) {
            if (tasks.size() == 0) {
                ui.cathulhuSays("\tYou are free now, mortal.");
            } else {
                ui.cathulhuSays("\tHere are your tasks, mortal:\n");
                for (int i = 1; i <= tasks.size(); i++) {
                    ui.cathulhuSays("\t" + i + ". " + tasks.getTask(i - 1));
                }
            }
            return false;
        }
    },

    DONE {
        @Override
        public boolean executeCommand(TaskList tasks, Ui ui, String cmdString) throws DukeException {
            if (cmdString.equals("")) {
                throw new DukeException("\tWhich task do you want to mark as done, mortal!");
            }
            int taskIndex;
            try {
                taskIndex = Integer.parseInt(cmdString) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("\tWhich task do you want to delete, mortal!");
            }
            if (tasks.size() <= taskIndex) {
                throw new DukeException("\tMortal, thou have no such task!");
            }
            tasks.getTask(taskIndex).markAsDone();
            ui.cathulhuSays("\tMortal, thou have completed this task:");
            ui.cathulhuSays("\t  " + tasks.getTask(taskIndex));
            return false;
        }
    },

    DELETE {
        @Override
        public boolean executeCommand(TaskList tasks, Ui ui, String cmdString) throws DukeException {
            if (cmdString.equals("")) {
                throw new DukeException("\tWhich task do you want to delete, mortal!");
            }
            int taskIndex;
            try {
                taskIndex = Integer.parseInt(cmdString) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("\tWhich task do you want to delete, mortal!");
            }
            if (tasks.size() <= taskIndex) {
                throw new DukeException("\tMortal, thou have no such task!");
            }
            ui.cathulhuSays("\tMortal, the following task has been deleted:\n\t  "
                    + tasks.removeTask(taskIndex)
                    + "\n\tYou have "
                    + tasks.size()
                    + " tasks left\n");
            return false;
        }
    },

    TODO {
        @Override
        public boolean executeCommand(TaskList tasks, Ui ui, String cmdString) throws DukeException {
            if (cmdString.equals("")) {
                throw new DukeException("\tA \"todo\" task must have a description, mortal!");
            }
            tasks.addTask(new ToDo(cmdString));
            ui.cathulhuSays("\tTask added: \n\t  "
                    + tasks.getTask(tasks.size() - 1)
                    + "\n\tYou have "
                    + tasks.size()
                    + " tasks now, mortal\n");
            return false;
        }
    },

    DEADLINE {
        @Override
        public boolean executeCommand(TaskList tasks, Ui ui, String cmdString) throws DukeException {
            if (cmdString.equals("")) {
                throw new DukeException("\tA \"deadline\" task must have a description and a deadline, mortal!");
            }
            String[] byString = cmdString.split("/by ", 2);
            if (byString.length == 1) {
                throw new DukeException("\tA \"deadline\" task must have a deadline, mortal!");
            }
            tasks.addTask(new Deadline(byString[0], byString[1]));
            ui.cathulhuSays("\tTask added: \n\t  "
                    + tasks.getTask(tasks.size() - 1)
                    + "\n\tYou have "
                    + tasks.size()
                    + " tasks now, mortal\n");
            return false;
        }
    },

    EVENT {
        @Override
        public boolean executeCommand(TaskList tasks, Ui ui, String cmdString) throws DukeException {
            if (cmdString.equals("")) {
                throw new DukeException("\tAn \"event\" task must have a description and a time, mortal!");
            }
            String[] atString = cmdString.split("/at ", 2);
            if (atString.length == 1) {
                throw new DukeException("\tAn \"event\" task must have a time, mortal!");
            }
            tasks.addTask(new Event(atString[0], atString[1]));
            ui.cathulhuSays("\tTask added: \n\t  "
                    + tasks.getTask(tasks.size() - 1)
                    + "\n\tYou have "
                    + tasks.size()
                    + " tasks now, mortal\n");
            return false;
        }
    },

    FIND {
        @Override
        public boolean executeCommand(TaskList tasks, Ui ui, String cmdString) throws DukeException {
            if (cmdString.equals("")) {
                throw new DukeException("\tA \"find\" task must have a keyword to search, mortal!");
            }
            ui.cathulhuSays("\tHere are the matching tasks in your list, mortal:");
            int numMatches = 0;
            for (int i = 0; i < tasks.size(); i++) {
                String taskString = tasks.getTask(i).toString();
                if (taskString.contains(cmdString)) {
                    numMatches++;
                    ui.cathulhuSays("\t" + String.valueOf(numMatches) + "." + taskString);
                }
            }
            if (numMatches == 0) {
                ui.cathulhuSays("\tYou have no matching task, mortal.");
            }
            return false;
        }
    },

    UPDATE {
        @Override
        public boolean executeCommand(TaskList tasks, Ui ui, String cmdString) throws DukeException {
            if (cmdString.equals("")) {
                throw new DukeException("\tWhat do you want to update, mortal?");
            }
            String [] splitCmds = cmdString.split(" ", 2);
            int taskPos;
            String[] updateStringArr;
            try {
                taskPos = Integer.parseInt(splitCmds[0]);
                updateStringArr = splitCmds[1].split(",");
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("\tPlease give the numerical index of the task you want to update, mortal!");
            }

            if (!cmdString.contains("=")) {
                throw new DukeException("\tAttributes to update in the task must be comma separated, "
                        + "in the following format:\n \tattributeName1=newValue1, attributeName2=newValue2, ...");
            }
            String originalTask = tasks.getTask(taskPos).toString();
            String updatedTask = tasks.getTask(taskPos).update(updateStringArr).toString();
            ui.cathulhuSays("\tThe following task has been changed: \n\tFrom: "
                    + originalTask + "\n\tto: " + updatedTask);
            return false;
        }
    };

    public abstract boolean executeCommand(TaskList tasks, Ui ui, String cmdString) throws DukeException;
}
