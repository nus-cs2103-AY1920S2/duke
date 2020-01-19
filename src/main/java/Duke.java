import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {

       Duke duke = new Duke();
       duke.frontDesk();
    }

    public void frontDesk() {
     try {
         String greetings = "____________________________________________________________ \n" +
                            "Hello! I'm Chu Chu \n" +
                            "What can I do for you ? \n" +
                            "____________________________________________________________\n";
         System.out.println(greetings);

         InputStreamReader rd = new InputStreamReader(System.in);
         BufferedReader br = new BufferedReader(rd);
         String command = null;

         while(!(command = br.readLine()).equals("bye")) {

             String echo = "____________________________________________________________ \n" +
                            command + "\n" +
                           "____________________________________________________________ \n";
             System.out.println(echo);

         }

         System.out.println("Bye. I hope you liked the service and hope to see you soon ! \n");

     } catch(IOException e) {
         System.out.print(e);
     }

    }
}
