package util;

import exception.InvalidCommandArgumentException;
import exception.InvalidTaskDateTimeException;
import exception.MissingDateTimeException;
import exception.MissingDescriptionException;
import exception.TaskListIndexOutOfBoundsException;

import main.Duke;
import util.task.DeadlineTask;
import util.task.EventTask;
import util.task.Task;
import util.task.TaskList;
import util.task.ToDoTask;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class to accept and execute a user's commands.
 */
public class Parser {
    /**
     * Converts a Task's date to a String.
     *
     * @return A String of the Task's deadline.
     */
    public static String dateToString(Date deadline) {
        SimpleDateFormat format = new SimpleDateFormat("E HHmm, MMM d, YYYY");
        String string = format.format(deadline);
        String[] arr = string.split(DukeConstant.DELIMITER_COMMA);

        int day = deadline.getDate();
        if (day == DukeConstant.DAY_ONE) {
            arr[1] += DukeConstant.DAY_FIRST;
        } else if (day == DukeConstant.DAY_TWO) {
            arr[1] += DukeConstant.DAY_SECOND;
        } else if (day == DukeConstant.DAY_THREE) {
            arr[1] += DukeConstant.DAY_THIRD;
        } else {
            arr[1] += DukeConstant.DAY_NTH;
        }

        return arr[0] + "," + arr[1] + "," + arr[2];
    }

    /**
     * Converts a String to a Date object.
     *
     * @param parameter A String of dd/MM/yyyy hh:mm.
     * @return A Date parsed using the input String
     * @throws ParseException if the String cannot be parsed.
     */
    public static Date stringToDate(String parameter) throws ParseException {
        assert parameter != null;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        String[] arr = parameter.split(DukeConstant.DELIMITER_WHITESPACE);
        String hour = arr[1].substring(DukeConstant.TASK_HOUR_START_INDEX, DukeConstant.TASK_HOUR_END_INDEX);
        String minute = arr[1].substring(DukeConstant.TASK_HOUR_END_INDEX);
        String[] date = arr[0].split(DukeConstant.DELIMITER_DATE);

        // Input date is too short/long
        if (date.length != 3) {
            throw new InvalidTaskDateTimeException("   The date and/or time format is invalid.\n"
                    + "   Please format your input as: DD/MM/YYYY HHmm.");
        }

        String day = date[DukeConstant.DATE_DAY_INDEX];
        String month = date[DukeConstant.DATE_MONTH_INDEX];
        String year = date[DukeConstant.DATE_YEAR_INDEX];
        return format.parse(day + "/" + month + "/" + year + " " + hour + ":" + minute);
    }

    private static Date stringNoTimeToDate(String parameter) throws ParseException {
        assert parameter != null;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String[] date = parameter.split(DukeConstant.DELIMITER_DATE);

        // Input date is too short/long
        if (date.length != 3) {
            throw new InvalidTaskDateTimeException("   The date and/or time format is invalid.\n"
                    + "   Please format your input as: DD/MM/YYYY HHmm.");
        }

        String day = date[DukeConstant.DATE_DAY_INDEX];
        String month = date[DukeConstant.DATE_MONTH_INDEX];
        String year = date[DukeConstant.DATE_YEAR_INDEX];
        return format.parse(day + "/" + month + "/" + year);
    }

    /**
     * Parses and then executes a user's commands.
     */
    public static String parse(String input) {
        assert input != null;
        String command = Ui.readCommand(input);
        StringBuilder sb = new StringBuilder();

        switch (command) {
            case DukeConstant.HELP_COMMAND:
                sb.append(Ui.printHelp());
                break;

            case DukeConstant.LIST_COMMAND:
                sb.append(Ui.printList(Duke.dl));
                break;

            case DukeConstant.SAVE_COMMAND:
                try {
                    Storage.writeTasks(Duke.dl);
                } catch (IOException e) {
                    sb.append(Ui.printWriteErrorMessage());
                }

                sb.append(Ui.printSave());
                break;

            case DukeConstant.CLEAR_COMMAND:
                Duke.dl = new TaskList();
                sb.append(Ui.printClear());
                break;

            case DukeConstant.BYE_COMMAND:
                try {
                    Storage.writeTasks(Duke.dl);
                } catch (IOException e) {
                    sb.append(Ui.printWriteErrorMessage());
                }

                sb.append(Ui.printBye());
                break;

            case DukeConstant.FIND_COMMAND:
                String keywords = Ui.readKeyword(input.substring(command.length()));
                TaskList found = Duke.dl.find(keywords);
                sb.append(Ui.printFind(keywords, found));
                break;

            case DukeConstant.SCHEDULE_COMMAND:
                String scheduleString = Ui.readScheduleDate(input.substring(command.length()));

                try {
                    if (scheduleString.length() != 10) {
                        throw new InvalidTaskDateTimeException("   The date of the schedule is invalid.\n" +
                                "   Please try again!\n");
                    }

                    Date scheduleDate = stringNoTimeToDate(scheduleString);
                    TaskList schedule = Duke.dl.findByDay(scheduleDate);
                    sb.append(Ui.printList(schedule));
                } catch (InvalidTaskDateTimeException e) {
                    sb.append(Ui.printErrorMessage(e));
                } catch (ParseException e) {
                    sb.append(Ui.printDateTimeErrorMessage());
                }

                break;

            case DukeConstant.DONE_COMMAND:
                String str = Ui.readTaskIndex(input.substring(command.length()));

                try {
                    if (str.length() == 0) {
                        throw new TaskListIndexOutOfBoundsException("   No index entered. Please try again.");
                    }
                    int index = Integer.parseInt(str) - 1;

                    if (index < 0) {
                        throw new TaskListIndexOutOfBoundsException("   Invalid index entered. Please try again.");
                    }

                    if (index >= Duke.dl.listSize()) {
                        throw new TaskListIndexOutOfBoundsException(
                                "   There are only " + Duke.dl.listSize() + " items in the list!");
                    }

                    Duke.dl = Duke.dl.setDone(index);
                    sb.append(Ui.printDone(Duke.dl, index));
                } catch (TaskListIndexOutOfBoundsException e) {
                    sb.append(Ui.printErrorMessage(e));
                }

                break;

            case DukeConstant.DELETE_COMMAND:
                String delete = Ui.readTaskIndex(input.substring(command.length()));

                try {
                    if (delete.length() == 0) {
                        throw new TaskListIndexOutOfBoundsException("   No index entered. Please try again.");
                    }
                    int index = Integer.parseInt(delete) - 1;

                    if (index < 0) {
                        throw new TaskListIndexOutOfBoundsException("   Invalid index entered. Please try again.");
                    }

                    if (index >= Duke.dl.listSize()) {
                        throw new TaskListIndexOutOfBoundsException(
                                "   There are only " + Duke.dl.listSize() + " items in the list!");
                    }

                    Duke.dl = Duke.dl.deleteTask(index);
                    sb.append(Ui.printDelete(Duke.dl, index));
                } catch (TaskListIndexOutOfBoundsException e) {
                    sb.append(Ui.printErrorMessage(e));
                }

                break;

            case DukeConstant.TODO_COMMAND:
            case DukeConstant.DEADLINE_COMMAND:
            case DukeConstant.EVENT_COMMAND:
                String description = Ui.readTaskDescription(input.substring(command.length()));

                try {
                    if (description.replace("\n", "").replace(" ", "").length() == 0) {
                        throw new MissingDescriptionException("The description of a " + command
                                + " cannot be empty.\n   Please try again!");
                    } else {
                        Task task;
                        if (command.equals(DukeConstant.TODO_COMMAND)) {
                            task = new ToDoTask(description, false);
                        } else {
                            if (!description.contains(DukeConstant.DELIMITER_BY)
                                    && !description.contains(DukeConstant.DELIMITER_AT)) {
                                throw new MissingDateTimeException("   Missing \"/by\" or \"/at\" in task description.\n   Please try again.");
                            }

                            String[] arr = description.split("/by|/at");

                            if (arr.length == 0 || arr[1].split(DukeConstant.DELIMITER_WHITESPACE).length != 3) {
                                throw new MissingDateTimeException("   The date and time of the "
                                        + command + " is missing.\n   Please try again!\n\n"
                                        + DukeUiString.SAMPLE);
                            }

                            String[] splitting = arr[1].trim().split(DukeConstant.DELIMITER_WHITESPACE);
                            String parameter = splitting[0] + " " + splitting[1];
                            Date deadline = stringToDate(parameter);

                            task = command.equals(DukeConstant.DEADLINE_COMMAND)
                                    ? DeadlineTask.createDeadlineTask(arr[0], deadline)
                                    : EventTask.createEventTask(arr[0], deadline);
                        }

                        Duke.dl = Duke.dl.addToList(task);
                        sb.append(Ui.printTaskAdded(Duke.dl, task));
                    }
                } catch (MissingDescriptionException | MissingDateTimeException | InvalidTaskDateTimeException e) {
                    sb.append(Ui.printErrorMessage(e));
                } catch (ParseException e) {
                    sb.append(Ui.printDateTimeErrorMessage());
                }

                break;

            default:
                try {
                    throw new InvalidCommandArgumentException("   Hey, I can't do that for you. " +
                            "\n   I don't know " + input + "...");
                } catch (InvalidCommandArgumentException e) {
                    sb.append(Ui.printErrorMessage(e));
                }

                break;
        }

        System.out.println(sb.toString());
        return sb.toString();
    }
}
