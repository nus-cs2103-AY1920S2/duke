import java.time.LocalDate;
import java.util.Scanner;

public class Ui {

    public TaskList takeInput(TaskList tasks) {
        System.out.println(UiDesign.GREET.getString());

        int numTasks = tasks.getSize();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            try {
                String line = sc.nextLine();
                Parser decoded = new Parser(line, numTasks);
                Commands com = decoded.getCommand();
                int index = decoded.getIndexAffected();

                if (com == Commands.BYE) {
                    System.out.println(UiDesign.BYE.getString());
                    return tasks;

                } else if (com == Commands.LIST_TASKS) {
                    System.out.print(UiDesign.LIST_TOP_PART.getString());
                    tasks.printTaskList();
                    System.out.println(UiDesign.BORDER.getString());

                } else if (com == Commands.DONE) {
                    tasks.markDone(index);
                    System.out.println(UiDesign.DONE_TOP_PART.getString()
                            + "       " + tasks.getTask(index) + "\n"
                            + UiDesign.BORDER.getString());

                } else if (com == Commands.DEL_TASK) {
                    numTasks--;
                    System.out.println(UiDesign.REMOVE_TOP_PART.getString()
                            + "       " + tasks.getTask(index) + "\n"
                            + "     Now you have " + numTasks + " tasks in the list.\n"
                            + UiDesign.BORDER.getString());
                    tasks.remTask(index);

                } else if (com == Commands.NEW_TASK) {
                    Task newTask = decoded.getTask();
                    tasks.addTask(newTask);
                    numTasks++;
                    System.out.println(UiDesign.ADD_TOP_PART.getString()
                            + "       " + newTask + "\n"
                            + "     Now you have " + numTasks + " tasks in the list.\n"
                            + UiDesign.BORDER.getString());

                } else {    // com == FIND
                    String keyword = decoded.getKeyWord();
                    System.out.print(UiDesign.FIND_TOP_PART.getString());
                    tasks.printTasksContaining(keyword);
                    System.out.println(UiDesign.BORDER.getString());

                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        return new TaskList();
    }
}
