package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FindCommand implements Command {
    /**
     * findCommand Method finds Tasks which has matching keywords in its description.
     *
     * @param taskList      is the list of Tasks are saved and manipulated
     * @param commandSuffix is the additional String that accompanies two-step commands
     */
    public static String run(TaskList taskList, String... commandSuffix) {
        StringBuilder sb = new StringBuilder();
        List<String> keywordList = new ArrayList<>();

        //Converted previous nested if and for loop logic into an adapted Stream
        Stream.of(commandSuffix)
            .map(String::new)
            .filter(i -> !i.equals(""))
            .forEach(i -> {
                for (Task task : taskList.getTasks()) {
                    if (task.getTaskName().toLowerCase().contains(i.toLowerCase())) {
                        keywordList.add(task.toString());
                    }
                }
            });

        if (keywordList.size() == 0) {
            sb.append("No matching tasks with that keyword found.");
        } else {
            sb.append("Here are the matching task(s) in your list:\n");

            //Lambda for printing of found keyword tasks into a list
            for (String task : keywordList) {
                sb.append(task).append("\n");
            }

            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }
}
