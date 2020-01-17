import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
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
            System.out.print("\nMaster: ");
            String inp = sc.nextLine();
            System.out.println("");
            switch (inp) {
            case "bye":
                running = false;
                break;
            case "list":
                dukeSays("Master already forgotten what Master wanted to do?!");
                break;
            default:
                dukeSays("So Master wants to " + inp + "...");
                tasks.add(inp);
            }
        }
        dukeSays(new String[] {"Are you leaving already?", "Please come back and play with Duke soon..."});
    }

    private void dukeSays(String[] text) {
        boolean first = true;
        for (String line : text) {
            if (first) {
                System.out.print("Duke: ");
                first = false;
            } else {
                System.out.print("      ");
            }
            System.out.println(line);
        }
    }

    private void dukeSays(String line) {
        System.out.println("Duke: " + line);
    }

    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }
}
