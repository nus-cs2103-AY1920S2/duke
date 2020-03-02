package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DisplayTaskCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.IncorrectCommand;
import duke.command.InstructionCommand;
import duke.exception.DukeException;
import duke.task.Deadlines;
import duke.task.Symbol;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Event;

import java.util.Arrays;

/**
 * Parser class handles all the input of the user.
 * @author Lua Jia Zheng.
 */
public class Parser {
    /**
     * Process message and deem if user wants to quit.
     * Print good bye message if user wants to quit.
     * Else echo back the message the user inputted
     * @param message entered by user.
     * @return boolean used to inform other function if user wants to quit
     *     true -> continue using bot, false -> quit bot.
     */
    public static Command parseMessage(String message) throws DukeException {
        try {
            Task temp;
            if (message.equals("bye")) {
                return new ByeCommand(true);
            } else if (message.equals("i")) {
                return new InstructionCommand();
            } else if (message.equals("list")) {
                return new DisplayTaskCommand();
            } else if (message.substring(0, 4).equals("done")) {
                int taskIndex = Integer.parseInt(message.split(" ", 2)[1]);
                return new DoneCommand(taskIndex);
            } else if (message.substring(0, 4).equals("todo")) {
                temp = parseTask(message, Symbol.T);
                return new AddCommand(temp);
            } else if (message.substring(0, 4).equals("find")) {
                String find = message.split(" ",2)[1];
                return new FindCommand(find);
            } else if (message.substring(0, 5).equals("event")) {
                temp = parseTask(message, Symbol.E);
                return new AddCommand(temp);
            } else if (message.substring(0, 8).equals("deadline")) {
                temp = parseTask(message, Symbol.D);
                return new AddCommand(temp);
            } else if (message.substring(0, 6).equals("delete")) {
                int taskIndex = Integer.parseInt(message.split(" ", 2)[1]);
                return new DeleteCommand(taskIndex);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (StringIndexOutOfBoundsException ex) {
            return new IncorrectCommand("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Creates task.
     * @param message use to store it in the tasks.
     */
    public static Task parseTask(String message, Symbol symbol) throws DukeException {
        String[] arrMessage = message.split(" ");
        Task task = null;
        int landmark = arrMessage.length;
        switch (symbol) {
        case T:
            String[] tempArr = Arrays.copyOfRange(arrMessage,1,arrMessage.length);
            if (tempArr.length == 0) {
                throw new DukeException("  ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String newMessage = String.join(" ", tempArr);
            task = new Todo(newMessage, TaskList.index);
            break;
        case D:
            for (int i = 1; i < arrMessage.length; i++) {
                if (arrMessage[i].equals("/by")) {
                    landmark = i;
                    break;
                }
            }
            if (landmark == arrMessage.length) {
                throw new DukeException("  ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            task = createTask(arrMessage, 1, landmark);
            break;
        case E:
            for (int i = 1; i < arrMessage.length; i++) {
                if (arrMessage[i].equals("/at")) {
                    landmark = i;
                    break;
                }
            }
            if (landmark == arrMessage.length) {
                throw new DukeException("  ☹ OOPS!!! The description of a event cannot be empty.");
            }
            task = createTask(arrMessage, 2, landmark);
            break;
        default:
            System.out.println("No task created.");
        }
        return task;
    }

    /**
     * Creates Task.
     * @param arrMessage arrMessage contains the information of the task.
     * @param i 1 == Deadline task, 2 == Event Task.
     * @param landmark index of the /at or /by in the arr.
     * @return returns a Task.
     */
    public static Task createTask(String[] arrMessage, int i, int landmark) {
        String[] tempArr = Arrays.copyOfRange(arrMessage, 1, landmark);
        String[] tempArr2 = Arrays.copyOfRange(arrMessage, landmark + 1, arrMessage.length);
        String newMessage = String.join(" ", tempArr);
        String newDate = String.join(" ", tempArr2);
        if (i == 1) {
            return new Deadlines(newMessage, TaskList.index, false, newDate);
        } else {
            return new Event(newMessage, TaskList.index, newDate);
        }
    }
}
