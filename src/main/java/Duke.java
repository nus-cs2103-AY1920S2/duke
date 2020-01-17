import java.util.*;

public class Duke {
    private void run() {
        Scanner sc = new Scanner(System.in);
        String logo = "*******   **     ** **   ** ********\n"
                + "/**////** /**    /**/**  ** /**/////\n"
                + "/**    /**/**    /**/** **  /**\n"
                + "/**    /**/**    /**/****   /*******\n"
                + "/**    /**/**    /**/**/**  /**////\n"
                + "/**    ** /**    /**/**//** /**\n"
                + "/*******  //******* /** //**/********\n"
                + "///////    ///////  //   // //////// \n";
        System.out.println("\nHi hi I'm \n" + logo);
        String[] text = {"Master! I'm so glad you used me!", "What will you do to me today?"};
        dukeSays(text);
        boolean running = true;
        while(running){
            System.out.print("\nMaster: ");
            String inp = sc.nextLine();
            System.out.println("");
            switch (inp){
                case "bye":
                    running = false;
                    break;
                default:
                    dukeSays(inp);
            }
        }
        text = new String[] {"Are you leaving already?", "Please come back and play with Duke soon..."};
        dukeSays(text);
    }

    private void dukeSays(String[] text) {
        boolean first = true;
        for(String line : text) {
            if(first){
                System.out.print("Duke: ");
                first = false;
            } else{
                System.out.print("      ");
            }
            System.out.println(line);
        }
    }

    private void dukeSays(String text) {
        System.out.println("Duke: " + text);
    }

    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }
}
