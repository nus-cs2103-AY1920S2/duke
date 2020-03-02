package duke.task;

import exception.IllegalCommandException;

import java.util.Arrays;

public interface TaskDispatch {

    public static Task dispatchTaskFromInput(String[] commandArgs) throws IllegalCommandException {
        Task t = null;
        switch (commandArgs[0]) {
            case "event":
                t = Event.createTask(commandArgs);
                break;
            case "todo":
                t = Todo.createTask(commandArgs);
                break;
            case "deadline":
                t = Deadline.createTask(commandArgs);
                break;
            default:
                // Unrecognised command
                throw new IllegalCommandException();
        }
        return t;
    }

    public static Task dispatchTaskFromStorage(String[] inputStrings) throws IllegalCommandException {
        Task t = null;
        String[] commandArgs = new String[inputStrings.length - 1];

        for (int i = 1; i< inputStrings.length; i++) {
            commandArgs[i - 1] = inputStrings[i];
        }

        switch (inputStrings[0].toCharArray()[1]) {
            case 'E':
                t = Event.createTask(commandArgs);
                break;
            case 'T':
                t = Todo.createTask(commandArgs);
                break;
            case 'D':
                t = Deadline.createTask(commandArgs);
                break;
            default:
                // Unrecognised command
                throw new IllegalCommandException();
        }

        // Complete the duke.task.Task if Done
        if (inputStrings[0].toCharArray()[4] == Task.TICK) {
            t.markAsDone();
        }

        return t;
    }
}
