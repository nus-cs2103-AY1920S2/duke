import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private boolean sayFirst = true;
    private boolean sayNumbered = false;

    private void run() {
        String logo = "*******   **     ** **   ** ********\n"
                + "/**////** /**    /**/**  ** /**/////\n"
                + "/**    /**/**    /**/** **  /**\n"
                + "/**    /**/**    /**/****   /*******\n"
                + "/**    /**/**    /**/**/**  /**////\n"
                + "/**    ** /**    /**/**//** /**\n"
                + "/*******  //******* /** //**/********\n"
                + "///////    ///////  //   // //////// \n";

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        ArrayList<String> tasks = new ArrayList<String>();

        System.out.println("\nHi hi I'm \n" + logo);
        dukeSays(new String[] {"Master! I'm so glad you used me!", "What will you do to me today?"});
        while (running) {
            System.out.println("\nMaster: ");
            String inp = sc.nextLine();
            System.out.println("");
            switch (inp) {
            case "bye":
                running = false;
                break;
            case "list":
                dukeSays(new String[] {"Master already forgotten what Master wanted to do?!"
                    ,"Duke has no choice but to remind Master then!"});
                if (tasks.size()==0) {
                    dukeSays("Huh there are no tasks! Master is so forgetful...");
                } else {
                    dukeSays("These are the tasks which Master forgot:");
                    this.sayFirst = false;
                    this.sayNumbered = true;
                    dukeSays(tasks.toArray(new String[tasks.size()]));
                }
                break;
            default:
                dukeSays("So Master wants to " + inp + "...");
                tasks.add(inp);
            }
        }
        dukeSays(new String[] {"Are you leaving already?", "Please come back and play with Duke soon..."});
    }

    private void dukeSays(String[] text) {
        boolean first = this.sayFirst;
        int i = 1;
        for (String line : text) {
            if (this.sayNumbered) {
                System.out.print("      " + Integer.toString(i) + ". ");
                i += 1;
            } else if (first) {
                System.out.print("Duke: ");
                first = false;
            } else {
                System.out.print("      ");
            }
            System.out.println(line);
        }
        this.sayFirst = true;
        this.sayNumbered = false;
    }

    private void dukeSays(String line) {
        dukeSays(new String[] {line});
    }

    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }
}
