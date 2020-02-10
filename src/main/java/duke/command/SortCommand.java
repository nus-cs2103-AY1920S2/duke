package duke.command;

import duke.exception.UnknownTaskTypeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

public class SortCommand implements Command {
    /**
     * sortCommand Method sorts Tasks by the matching TaskType.
     *
     * @param taskList  is the list of Tasks are saved and manipulated
     * @param commandSuffix  is the additional String that accompanies two-step commands
     */
    public static String run(TaskList taskList, String commandSuffix) throws UnknownTaskTypeException {
        StringBuilder sortedSB = new StringBuilder();
        StringBuilder unsortedSB = new StringBuilder();
        Class<?> taskType;

        if (taskList.size() == 0) {
            sortedSB.append("There's nothing to sort, as the list is empty.");
        } else {
            try {
                taskType = TaskType.valueOf(commandSuffix.toUpperCase()).getTaskType();
            } catch (IllegalArgumentException e) {
                throw new UnknownTaskTypeException();
            }

            sortedSB.append("Here are the task(s) in your list, with ")
                .append(taskType.getSimpleName())
                .append(" Tasks sorted first:\n");

            for (Task task : taskList.getTasks()) {
                if (task.getClass().equals(taskType)) {
                    sortedSB.append(task).append("\n");
                } else {
                    unsortedSB.append(task).append("\n");
                }
            }
        }

        sortedSB.append(unsortedSB);
        sortedSB.setLength(sortedSB.length() - 1);
        return sortedSB.toString();
    }
}
