import java.util.Scanner;

public class Parser {
    private Ui ui;
    private TaskList tasklist;
    private Scanner sc;
    private boolean bye = false;

    public void parse() throws Exception {
        while (sc.hasNext()) {
            String str = sc.nextLine();

            if (str.isEmpty()) {
                continue;
            }

            System.out.println(ui.lineBarrier());
            parseCommands(str);
            if (bye) {
                break;
            }

        }
    }

    public Parser(TaskList tasklist) {
        this.tasklist = tasklist;
        this.sc = new Scanner(System.in);
        this.ui = new Ui();
    }
    public void parseCommands(String str) throws Exception {

        if (str.equals("bye")) {
            tasklist.bye();
            bye = true;
            return;

        } else if (str.equals("list")) {
            tasklist.printList();

        } else if (str.contains("done")) {
            tasklist.done(str);

        } else if (str.contains("todo")) {
            tasklist.todo(str);

        } else if (str.contains("deadline")) {
            tasklist.deadline(str);

        } else if (str.contains("event")) {
            tasklist.event(str);

        } else if (str.contains("delete")) {
            tasklist.delete(str);

        } else {
            System.out.println(ui.unknownCommand());
        }
        System.out.println(ui.lineBarrier() + "\n\n");
    }
}

