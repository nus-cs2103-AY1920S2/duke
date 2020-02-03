package Duke;

public class Ui {
    private TaskList taskList;

    public Ui (TaskList taskList) {
        this.taskList = taskList;
    }

    public void printList() {
        System.out.println("Here are the tasks in your list!");
        System.out.println("____________________________________________________________");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println("        " + (i + 1) + " " + this.taskList.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void markAsDone(int index) {
        this.taskList.get(index).markAsDone();
        System.out.println("____________________________________________________________");
        System.out.println("Good job! I have marked the task as done! meow~ \n");
        System.out.println("        " + this.taskList.get(index));
        System.out.println("____________________________________________________________");
    }

    public void printTask(Task t) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it! I have added this task: \n");
        System.out.println("        " + t + "\n");
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list! [^._.^]ﾉ");
        System.out.println("____________________________________________________________");
    }

    public void printDeletedTask(Task t) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted! I have removed this task: \n");
        System.out.println("        " + t + "\n");
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list![^._.^]ﾉ");
        System.out.println("____________________________________________________________");
    }
    public void printBye() {
        System.out.println("Sayonara~");
        System.out.println(" (_＼ヽ\n" + "　 ＼＼ .Λ＿Λ.\n" + "　　 ＼(　ˇωˇ)　\n" + "　　　 >　⌒ヽ\n" + "　　　/ 　 へ＼\n"
                + "　　 /　　/　＼＼\n" + "　　 ﾚ　ノ　　 ヽ_つ\n" + "　　/　/\n" + "　 /　/|\n" + "　(　(ヽ\n" + "　|　|、＼\n"
                + "　| 丿 ＼ ⌒)\n" + "　| |　　) /\n" + "`ノ ) 　 Lﾉ\n" + "(_／");
    }
}
