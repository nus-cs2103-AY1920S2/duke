package duke.util;

import duke.exception.OutOfBoundMarkingRequestException;
import duke.task.Task;
import duke.util.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    /**
     * Mark task at pos in storedItems as Done.
     * @param pos
     * @param storedItems
     * @throws OutOfBoundMarkingRequestException
     * @return Ui String of marking task job.
     */
    public static String markItemAsDone(int pos, ArrayList<Task> storedItems) throws OutOfBoundMarkingRequestException {
        if (pos >= storedItems.size() || pos < 0)
            throw new OutOfBoundMarkingRequestException(pos+1);

        if (storedItems.get(pos).isDoneTask())
            return Ui.markItemAsDone(pos, storedItems, true);

        storedItems.get(pos).markDone();
        return Ui.markItemAsDone(pos, storedItems, false);
    }

    /**
     * Delete task at pos in storedItems.
     * @param pos
     * @param storedItems
     * @throws OutOfBoundMarkingRequestException
     * @return Ui String of deletion job.
     */
    public static String deleteItem(int pos, ArrayList<Task> storedItems) throws OutOfBoundMarkingRequestException {
        if (pos >= storedItems.size() || pos < 0)
            throw new OutOfBoundMarkingRequestException(pos+1);

        Task t = storedItems.remove(pos);
        return Ui.deleteItem(t, storedItems);
    }

    /**
     * Search for tasks whose names contain the given sequence.
     * Print them to the screen by calling Ui method.
     * @param str
     * @param storedItems
     * @return Ui String of found tasks.
     */
    public static String findItem(String str, ArrayList<Task> storedItems) {
        assert str.startsWith("find");
        if (str.contains("/")) {
            return findAdvancedByType(str, storedItems);
        }

        ArrayList<Task> foundList = new ArrayList<>();

        Scanner sc = new Scanner(str);
        assert sc.hasNext();
        sc.next();
        assert sc.hasNext();
        String criteria = sc.nextLine().trim();
        sc.close();

        int len = storedItems.size();
        for (int i = 0; i < len; i++) {
            Task cur = storedItems.get(i);
            if (cur.getName().contains(criteria)) {
                foundList.add(cur);
            }
        }

        return Ui.printFoundList(foundList);
    }

    /**
     * Implementing BetterSearch feature.
     * Can customize search to by certain Task type,
     * @param str
     * @param storedItems
     * @return Ui String of found tasks.
     */
    private static String findAdvancedByType(String str, ArrayList<Task> storedItems) {
        ArrayList<Task> foundList = new ArrayList<>();
        System.out.println("doing advanced search: " + str);
        String[] parts = str.split("/");
        assert parts[0].equals("find ");
        Scanner sc = new Scanner(parts[1]);
        String option = sc.next();
        String criteria = sc.nextLine().trim();
        sc.close();
        System.out.println(criteria);
        Task.TaskType toFindType = Task.TaskType.UNKNOWN;
        switch (option) {
            case "todo":
                toFindType = Task.TaskType.TODO;
                break;
            case "deadline":
                toFindType = Task.TaskType.DEADLINE;
                break;
            case "event":
                toFindType = Task.TaskType.EVENT;
                break;
            default:
                break;
        }
        System.out.println("toFindType = " + toFindType);
        assert toFindType != Task.TaskType.UNKNOWN;

        int len = storedItems.size();
        for (int i = 0; i < len; i++) {
            Task cur = storedItems.get(i);
            System.out.println("cur = " + cur);
            System.out.println(cur.getTaskType());
            if (cur.getTaskType() == toFindType && cur.getName().contains(criteria)) {
                foundList.add(cur);
            }
        }


        return Ui.printFoundList(foundList);
    }
}
