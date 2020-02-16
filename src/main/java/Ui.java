/**
 *<h1>Ui</h1>
 * The Ui class handles most of the interaction with the user.
 * It contains methods: printList, markAsDone, printTask, printDeletedTask and printBye.
 */
public class Ui {
    private TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * This method prints out all the tasks on the list.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list!");
        System.out.println("____________________________________________________________");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println("        " + (i + 1) + " " + this.taskList.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * This method prints out a confirmation text that the task at position index has been marked as done.
     * @param index the position of the task being marked done
     */
    public void markAsDone(int index) {
        this.taskList.get(index).markAsDone();
        System.out.println("____________________________________________________________");
        System.out.println("Good job! I have marked the task as done! meow~ \n");
        System.out.println("        " + this.taskList.get(index));
        System.out.println("____________________________________________________________");
    }

    /**
     * This method prints out a confirmation text that task t has been added to the list.
     * @param t the task being added
     */
    public void printTask(Task t) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it! I have added this task: \n");
        System.out.println("        " + t + "\n");
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list! [^._.^]ﾉ");
        System.out.println("____________________________________________________________");
    }

    /**
     * This method prints out a confirmation text that task t has been deleted from the list.
     * @param t the task required to be deleted
     */
    public void printDeletedTask(Task t) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted! I have removed this task: \n");
        System.out.println("        " + t + "\n");
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list![^._.^]ﾉ");
        System.out.println("____________________________________________________________");
    }

    /**
     * This method prints the bye statement.
     */
    public void printBye() {
        System.out.println("Sayonara~");
        System.out.println(" (_＼ヽ\n" + "　 ＼＼ .Λ＿Λ.\n" + "　　 ＼(　ˇωˇ)　\n" + "　　　 >　⌒ヽ\n" + "　　　/ 　 へ＼\n"
                + "　　 /　　/　＼＼\n" + "　　 ﾚ　ノ　　 ヽ_つ\n" + "　　/　/\n" + "　 /　/|\n" + "　(　(ヽ\n" + "　|　|、＼\n"
                + "　| 丿 ＼ ⌒)\n" + "　| |　　) /\n" + "`ノ ) 　 Lﾉ\n" + "(_／");
    }

    public void printMatchingTasks(TaskList tL) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tL.size(); i++) {
            System.out.println("        " + (i + 1) + " " + tL.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
