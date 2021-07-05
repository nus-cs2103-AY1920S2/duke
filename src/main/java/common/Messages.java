package common;

import data.task.*;

import java.io.File;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String LOGO = "                     __ ____  ______  ____\n" +
            "                    / //_/ / / / __ \\/  _/\n" +
            "                   / ,< / / / / /_/ // /  \n" +
            "                  / /| / /_/ / _, _// /   \n" +
            "                 /_/ |_\\____/_/ |_/___/   \n" +
            "                         \n";
    public static final int INDEX_OFF_SET = -1;
    public static final int LIST_INDEX_OFFSET = 1;
    public static final char EVENT_TYPE = 'E';
    public static final char DEADLINE_TYPE = 'D';
    public static final char DONE = 'D';
    public static final char notDONE = 'N';
    public static final String DIVIDER = "---------------------------------------------------------------------------------";
    public static final String MESSAGE_SPLITTER = "=================================================================================";
    public static final String MESSAGE_WELCOME_1 = "Welcome to KURI Task Management System?";
    public static final String MESSAGE_WELCOME_2 = "What can I do for you?";
    public static final String MESSAGE_FAREWELL = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_TASK_NOT_IN_TASKLIST = "The Task could not be found in address book";
    public static final String MESSAGE_TODO_LIST = "%d. [%c][%c] %s";
    public static final String MESSAGE_DEADLINE_LIST = "%d. [%c][%c] %s (%s)";
    public static final String MESSAGE_EVENT_LIST = "%d. [%c][%c] %s (%s)";
    public static final String MESSAGE_FILE_OPERATION_IO_ERROR = "Error writing to file: %s";
    public static final String MESSAGE_DUPLICATE_TASK_ALERT_1 = "This task is similar to task Index %d";
    public static final String MESSAGE_DUPLICATE_TASK_ALERT_2 = "Do you want to add a duplicate task?";
    public static final String MESSAGE_DUPLICATE_TASK_ALERT_3 = "Press Y to add and others to not add: ";
    public static final String MESSAGE_DUPLICATE_TASK_NOT_ADDED = "The duplicate task is not added!";
    public static final String MESSAGE_ASK_TO_CHOOSE_UI = "Please enter '1' for GUI and '2' for CLI: ";
    public static final String MESSAGE_THANKS_FOR_GUI = "Thanks for choosing GUI";
    public static final String MESSAGE_THANKS_FOR_CLI = "Thanks for choosing CLI";
    public static final String MESSAGE_INVALID_USER_CHOICE = "Invalid Choice!";
    public static final String MESSAGE_ALERT = "[ALERT!]";
    public static final String MESSAGE_SHOW_TASK_NUMBER = "There are total %d task(s) in the list";
    public static StringBuilder taskListMessage;
    public static final String respondFormat = "===-             %-70s-===%n";
    public static final String MESSAGE_LIST_RESPOND_Format = "%-60s";
    public static final String MESSAGE_RS = "-===";
    public static final String MESSAGE_LS = "===-             ";
    public static final String MESSAGE_TODO_SUCCESS = "Got it. I've added this task: [ID:%d][%c][%c] %s";
    public static final String MESSAGE_DEADLINE_SUCCESS = "Got it. I've added this task: [ID:%d][%c][%c] %s (%s)";
    public static final String MESSAGE_EVENT_SUCCESS = "Got it. I've added this task: [ID:%d][%c][%c] %s (%s)";
    public static final String MESSAGE_INVALID = "Invalid command! ";
    public static final String MESSAGE_HELP = "Here are the possible commands: ";
    public static final String TXT_FILE_PATH = "src"+ File.pathSeparator+"main"+File.pathSeparator+"data"+File.pathSeparator+"taskManager.txt";
    public static final String JSON_FILE_PATH = "src"+ File.pathSeparator+"main"+File.pathSeparator+"data"+File.pathSeparator+"taskManager.json";
    public static final String MESSAGE_NEW_STORAGE = "New stroage is initialized!";
//    public static final String TXT_FILE_PATH = "."+ File.pathSeparator+"data"+File.pathSeparator+"taskManager.txt";
//    public static final String JSON_FILE_PATH = "."+ File.pathSeparator+"data"+File.pathSeparator+"taskManager.json";

    //public static StringBuilder taskListMessage = new StringBuilder();

    /**
     * Print all tasks in the task list for GUI
     */
    public static String printAllTasks(TaskList tasklist){
        String taskMessage = "";
        taskListMessage = new StringBuilder(taskMessage);
        for (int i = 1; i <= tasklist.getInternalList().size() ; i++) {
            Task task = tasklist.getInternalList().get(i-1);
            if (task instanceof TodoTask) {
                taskMessage = printTodoTask((TodoTask) task, i);
            } else if (task instanceof DeadlineTask) {
                taskMessage = printDeadlineTask((DeadlineTask) task, i);
            } else if( task instanceof EventTask) {
                taskMessage = printEventTask((EventTask) task, i);
            }
            taskListMessage = taskListMessage.append(taskMessage).append("\n");
        }
        return taskListMessage.toString();
    }

    public static String printTodoTask(TodoTask todoTask, int index){
        return String.format(
                MESSAGE_TODO_LIST,
                index,
                todoTask.getTaskType(),
                todoTask.getChar(),
                todoTask.getTaskDescription());
    }

    public static String printDeadlineTask(DeadlineTask deadlineTask, int index){
        return String.format(
                MESSAGE_DEADLINE_LIST,
                index,
                deadlineTask.getTaskType(),
                deadlineTask.getChar(),
                deadlineTask.getTaskDescription(),
                deadlineTask.getTaskDeadline());
    }

    public static String printEventTask(EventTask eventTask, int index){
        return String.format(
                MESSAGE_EVENT_LIST,
                index,
                eventTask.getTaskType(),
                eventTask.getChar(),
                eventTask.getTaskDescription(),
                eventTask.getTaskStartTime());
    }
}
