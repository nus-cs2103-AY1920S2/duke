import java.util.Scanner;

/**
 * <h1> Ui </h1>
 * The Ui class deals with the interaction with the User
 */
public class Ui {
    private String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private final String separator = "____________________________________________________________";
    private final String indent1 = " ".repeat(4);
    private final String indent2 = " ".repeat(5);
    private final String indent3 = " ".repeat(7);
    private boolean hasExited = false;
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String printList(TaskList tasks) {
        String str = "Here are the tasks in your list! \n";
        str += (separator + "\n");
        for (int i = 0; i < tasks.size(); i++) {
            str += (String.format("%d.%s", i + 1, tasks.get(i)) + "\n");
        }
        str += (separator + "\n");
        return str;
    }

    public String printIntro() {
        return String.format("Hello from %n%s%nKonnichiwa! I am Duke the cat! What can I do for you? meow~ (^.___.^)",
                logo);
    }

    public String getInput() {
        String input = sc.nextLine().trim();
        if(input.toLowerCase().equals("bye")) {
            this.close();
            return "bye";
        }
        return input;
    }

    public void close() {
        this.hasExited = true;
        this.sc.close();
    }

    public boolean hasEnded() {
        return this.hasExited;
    }

    public String printBye() {
        return "Sayonara~ \n" + " (_＼ヽ\n" + "　 ＼＼ .Λ＿Λ.\n" + "　　 ＼(　ˇωˇ)　\n" + "　　　 >　⌒ヽ\n" + "　　　/ 　 へ＼\n"
                + "　　 /　　/　＼＼\n" + "　　 ﾚ　ノ　　 ヽ_つ\n" + "　　/　/\n" + "　 /　/|\n" + "　(　(ヽ\n" + "　|　|、＼\n" + "　| 丿 ＼ ⌒)\n"
                + "　| |　　) /\n" + "`ノ ) 　 Lﾉ\n" + "(_／";
    }

    public String printMarkedTask(Task t) {
        return String.format("%s%nGood job! I have marked the task as done! meow~%n",separator)
                + String.format("%s%s%n%s", indent1, t, separator);
    }

    public String printRemovedTask(Task t, int size) {
        return String.format("%s%n", separator) + String.format(
                "Noted. I've removed this task:%n%s%nNow you have %d tasks in the list![^._.^]ﾉ%n%s", t, size, separator);
    }

    public String printTaskAdded(Task t, int size) {
        return String.format("%s%nGot it! I have added this task:%n%s%s%n", separator, indent1, t)
                + String.format("Now you have %d tasks in the list! [^._.^]ﾉ %n%s", size, separator);
    }

    public void printErr(DukeException err) {
        System.out.println(err);
    }

    public String printSearchResult(String str) {
        return String.format("%s%n%s%n%s", separator, str, separator);
    }

    public String printAskForDateTime() {
        return String.format("Please enter new date and time![^._.^]ﾉ");
    }

    public String printEdited(TaskList tasks) {
        return String.format("Your task has been edited!%n%s", this.printList(tasks));
    }

}
