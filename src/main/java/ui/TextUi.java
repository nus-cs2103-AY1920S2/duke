package ui;

import commands.ClearCommand;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.add.AddDeadlineCommand;
import commands.add.AddEventCommand;
import commands.add.AddTodoCommand;
import common.Messages;
import data.task.*;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.InputMismatchException;
import java.util.Scanner;

import static common.Messages.*;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;


public class TextUi {

    public static final int DISPLAYED_INDEX_OFFSET = 1;
    public static final Ansi.Color SYSTEM_COLOR_MESSAGE = BLUE;
    public static final Ansi.Color SYSTEM_COLOR_RESPONSE = GREEN;
    public static final Ansi.Color SYSTEM_COLOR_DIVIDER = BLACK;
    public static final Ansi.Color SYSTEM_COLOR_LOGO = MAGENTA;
    public static final Ansi.Color SYSTEM_COLOR_ALERT = RED;


    //display welcome message
    public static void showWelcomeMessage (){
        AnsiConsole.systemInstall();
        printDivider();
        System.out.print(String.format(Messages.respondFormat,
                ansi().bold().fg(SYSTEM_COLOR_MESSAGE).a(Messages.MESSAGE_WELCOME_1).reset()) );
        System.out.print(String.format(Messages.respondFormat,
                ansi().bold().fg(SYSTEM_COLOR_MESSAGE).a(Messages.MESSAGE_WELCOME_2).reset()) );
        printDivider();
        AnsiConsole.systemUninstall();
    }

    //display farewell message
    public static void showFarewellMessage (){
        AnsiConsole.systemInstall();
        printDivider();
        System.out.println( ansi().fg(SYSTEM_COLOR_MESSAGE).a(Messages.MESSAGE_FAREWELL).reset() );
        printDivider();
        AnsiConsole.systemUninstall();
    }

    //echo function, display user's input
    public static void showResult(String text) {
        //printDivider();
        printMessage(CYAN, text);
        printDivider();
    }

    public static void displayLogo() {
        AnsiConsole.systemInstall();
        ansi().reset();
        System.out.print(ansi().bold().fg(SYSTEM_COLOR_LOGO).a(Messages.LOGO).reset());
        printDivider();
        AnsiConsole.systemUninstall();
    }

    public static int getUserChoice() {
        printDivider();
        printMessage(SYSTEM_COLOR_RESPONSE, Messages.MESSAGE_ASK_TO_CHOOSE_UI);
        printDivider();
        int userChoice;
        try {
            Scanner sc = new Scanner(System.in);
            userChoice = sc.nextInt();
            return userChoice;
        } catch (InputMismatchException ex) {
            getUserChoice();
            return -1;
        }
    }

    public static void askForReInput() {
        printDivider();
        printAlert();
        printMessage(SYSTEM_COLOR_MESSAGE, Messages.MESSAGE_INVALID_USER_CHOICE);
    }

    public static void acknowledgementUserChoice(int userChoice){
        switch (userChoice){
        case 1:
            printMessage(SYSTEM_COLOR_RESPONSE, Messages.MESSAGE_THANKS_FOR_GUI);
            break;
        case 2:
            printMessage(SYSTEM_COLOR_RESPONSE, Messages.MESSAGE_THANKS_FOR_CLI);
            break;
        }
    }

    /**
     * print a divider
     */
    public static void printDivider(){
        AnsiConsole.systemInstall();
        System.out.println( ansi().bold().fg(SYSTEM_COLOR_DIVIDER).a(DIVIDER).reset() );
        AnsiConsole.systemUninstall();
    }

    public static void alertToAddDuplicateTask(Task toCheck){
        printAlert();
        printMessage(SYSTEM_COLOR_MESSAGE,
                String.format(Messages.MESSAGE_DUPLICATE_TASK_ALERT_1, toCheck.getTaskIndex()));
        printMessage(SYSTEM_COLOR_MESSAGE,
                Messages.MESSAGE_DUPLICATE_TASK_ALERT_2);
        printMessage(SYSTEM_COLOR_MESSAGE,
               Messages.MESSAGE_DUPLICATE_TASK_ALERT_3);
    }

    public static void printDuplicateTaskNotAdded(){
        printMessage(SYSTEM_COLOR_MESSAGE, Messages.MESSAGE_DUPLICATE_TASK_NOT_ADDED);
    }

    /**
     * clear the screen
     */
    public static void clearScreen(){
        AnsiConsole.systemInstall();
        System.out.println(ansi().eraseScreen());
        AnsiConsole.systemUninstall();
    }

    public static void printAlert(){
        AnsiConsole.systemInstall();
        ansi().reset();
        System.out.print(String.format(Messages.respondFormat,
                ansi().bold().fg(SYSTEM_COLOR_ALERT).a(Messages.MESSAGE_ALERT).reset()) );
        AnsiConsole.systemUninstall();
    }

    public static void printMessage(Ansi.Color color, String message){
        AnsiConsole.systemInstall();
        ansi().reset();
        System.out.print(String.format(Messages.respondFormat,
                ansi().bold().fg(color).a(message).reset()));
        AnsiConsole.systemUninstall();
    }

    public static void printLS(){
        AnsiConsole.systemInstall();
        System.out.print(ansi().bold().fg(SYSTEM_COLOR_DIVIDER).a(MESSAGE_LS).reset());
        AnsiConsole.systemUninstall();
    }

    public static void printRS(){
        AnsiConsole.systemInstall();
        System.out.println(ansi().bold().fg(SYSTEM_COLOR_DIVIDER).a(MESSAGE_RS).reset());
        AnsiConsole.systemUninstall();
    }

    public static void printTask(Ansi.Color color, String message){
        AnsiConsole.systemInstall();
        printLS();
        System.out.print(
                ansi().bold().fg(color).a(message).reset());
        printRS();
        AnsiConsole.systemUninstall();
    }

    /**
     * print todo-type task
     * @param todoTask
     * @param index
     * @return
     */
    public static void printTodoTask(TodoTask todoTask, int index){
        printTask(SYSTEM_COLOR_RESPONSE,String.format(Messages.MESSAGE_LIST_RESPOND_Format, String.format(
                Messages.MESSAGE_TODO_LIST,
                index,
                todoTask.getTaskType(),
                todoTask.getChar(),
                todoTask.getTaskDescription()))) ;
    }

    /**
     * print deadline-type task
     * @param deadlineTask
     * @param index
     * @return
     */
    public static void printDeadlineTask(DeadlineTask deadlineTask, int index){
        printTask(SYSTEM_COLOR_RESPONSE,String.format(Messages.MESSAGE_LIST_RESPOND_Format, String.format(
                Messages.MESSAGE_DEADLINE_LIST,
                index,
                deadlineTask.getTaskType(),
                deadlineTask.getChar(),
                deadlineTask.getTaskDescription(),
                deadlineTask.getTaskDeadline())));
    }

    /**
     * print event-type task
     * @param eventTask
     * @param index
     * @return
     */
    public static void printEventTask(EventTask eventTask, int index){
        printTask(SYSTEM_COLOR_RESPONSE,String.format(Messages.MESSAGE_LIST_RESPOND_Format, String.format(
                Messages.MESSAGE_EVENT_LIST,
                index,
                eventTask.getTaskType(),
                eventTask.getChar(),
                eventTask.getTaskDescription(),
                eventTask.getTaskStartTime())));
    }

    /**
     * Print all tasks in the task list
     */
    public static String printAllTasks(TaskList tasklist){
        String taskMessage = "";
        taskListMessage = new StringBuilder();
        getTaskListMessage(tasklist);
        return taskListMessage.toString();
    }

    /**
     * get tasklist message
     * @param tasklist
     */
    private static void getTaskListMessage(TaskList tasklist) {
        for (int index =  LIST_INDEX_OFFSET; index <= tasklist.getInternalList().size() ; index++) {
            Task task = tasklist.getInternalList().get(index+ INDEX_OFF_SET);
            printTaskMessage(index, task);
        }
        TextUi.printMessage(TextUi.SYSTEM_COLOR_RESPONSE,
                String.format(MESSAGE_SHOW_TASK_NUMBER,
                        tasklist.getInternalList().size()) );
    }

    /**
     * get task message
     * @param task
     * @return
     */
    private static void printTaskMessage(int index, Task task) {
        if (task instanceof TodoTask) {
            TextUi.printTodoTask((TodoTask) task, index);
        } else if (task instanceof DeadlineTask) {
            TextUi.printDeadlineTask((DeadlineTask) task, index);
        } else if( task instanceof EventTask) {
            TextUi.printEventTask((EventTask) task, index);
        }
    }

    public static void printInvalidCommandMessage() {
        TextUi.printAlert();
        TextUi.printMessage(YELLOW, MESSAGE_INVALID);
    }

    public static void printHelpMessage() {
        TextUi.printMessage(YELLOW, MESSAGE_HELP);
        TextUi.printMessage(Ansi.Color.MAGENTA, AddTodoCommand.COMMAND_WORD);
        TextUi.printMessage(Ansi.Color.BLUE, AddTodoCommand.MESSAGE_USAGE_1);
        TextUi.printMessage(Ansi.Color.BLUE, AddTodoCommand.MESSAGE_USAGE_2);

        TextUi.printMessage(Ansi.Color.MAGENTA, AddDeadlineCommand.COMMAND_WORD);
        TextUi.printMessage(Ansi.Color.BLUE, AddDeadlineCommand.MESSAGE_USAGE_1);
        TextUi.printMessage(Ansi.Color.BLUE, AddDeadlineCommand.MESSAGE_USAGE_2);

        TextUi.printMessage(Ansi.Color.MAGENTA, AddEventCommand.COMMAND_WORD);
        TextUi.printMessage(Ansi.Color.BLUE, AddEventCommand.MESSAGE_USAGE_1);
        TextUi.printMessage(Ansi.Color.BLUE, AddEventCommand.MESSAGE_USAGE_2);

        TextUi.printMessage(Ansi.Color.MAGENTA, DeleteCommand.COMMAND_WORD);
        TextUi.printMessage(Ansi.Color.BLUE, DeleteCommand.MESSAGE_USAGE_1);
        TextUi.printMessage(Ansi.Color.BLUE, DeleteCommand.MESSAGE_USAGE_2);
        TextUi.printMessage(Ansi.Color.BLUE, DeleteCommand.MESSAGE_USAGE_3);

        TextUi.printMessage(Ansi.Color.MAGENTA, ClearCommand.COMMAND_WORD);
        TextUi.printMessage(Ansi.Color.BLUE, ClearCommand.MESSAGE_USAGE_1);
        TextUi.printMessage(Ansi.Color.BLUE, ClearCommand.MESSAGE_USAGE_2);

        TextUi.printMessage(Ansi.Color.MAGENTA, FindCommand.COMMAND_WORD);
        TextUi.printMessage(Ansi.Color.BLUE, FindCommand.MESSAGE_USAGE_1);
        TextUi.printMessage(Ansi.Color.BLUE, FindCommand.MESSAGE_USAGE_2);

        TextUi.printMessage(Ansi.Color.MAGENTA, ExitCommand.COMMAND_WORD);
        TextUi.printMessage(Ansi.Color.BLUE, ExitCommand.MESSAGE_USAGE_1);
        TextUi.printMessage(Ansi.Color.BLUE, ExitCommand.MESSAGE_USAGE_2);
    }
}
