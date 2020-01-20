import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        //initialise scanner
        Scanner s = new Scanner(System.in);

        //store tasks
        List<Task> tasks = new ArrayList<>();
    

        String logo = "\n\n____________________¶¶¶¶¶¶¶¶¶¶¶ \n" +
                "_______________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶ \n" +
                "____________¶¶¶¶¶¶¶¶1111111111¶¶¶¶¶¶¶¶ \n" +
                "__________¶¶¶¶¶11111111111111111111¶¶¶¶¶¶ \n" +
                "________¶¶¶¶¶1111111111111111111111111¶¶¶¶¶ \n" +
                "_______¶¶¶¶11111111111111111111111111111¶¶¶¶ \n" +
                "_____¶¶¶¶1111¶¶¶1111111111111111111¶¶¶111¶¶¶¶ \n" +
                "____¶¶¶¶11111¶¶¶1111111111111111111¶¶¶11111¶¶¶ \n" +
                "___¶¶¶1111111¶¶¶1111111111111111111¶¶¶111111¶¶¶ \n" +
                "__¶¶¶¶1111¶¶¶¶¶¶1111111111111111111¶¶¶¶¶¶1111¶¶¶ \n" +
                "__¶¶¶111¶¶¶¶¶¶¶¶1111111111111111111¶¶¶¶¶¶¶¶111¶¶¶ \n" +
                "_¶¶¶111¶¶¶¶___¶¶¶11111111111111111¶¶¶___¶¶¶¶11¶¶¶ \n" +
                "_¶¶¶11¶¶¶____¶¶¶¶¶1111111111111111¶¶¶¶____¶¶¶11¶¶ \n" +
                "_¶¶11¶¶¶_____¶¶¶¶¶¶11111111111111¶¶¶¶¶_____¶¶11¶¶¶ \n" +
                "¶¶¶11¶¶¶_____¶¶¶¶¶¶¶¶11111111111¶¶¶¶¶¶_____¶¶¶1¶¶¶ \n" +
                "¶¶¶11¶¶¶_____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶_____¶¶111¶¶ \n" +
                "¶¶¶111¶¶¶____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶____¶¶¶111¶¶ \n" +
                "¶¶¶111¶¶¶¶___¶¶¶¶¶¶¶¶¶¶11111¶¶¶¶¶¶¶¶¶¶___¶¶¶111¶¶¶ \n" +
                "¶¶¶1111¶¶¶¶___¶¶¶¶¶¶¶¶111111¶¶¶¶¶¶¶¶¶___¶¶¶¶111¶¶¶ \n" +
                "_¶¶111111¶¶¶¶¶¶¶¶¶¶¶¶111111111¶¶¶¶¶¶¶¶¶¶¶¶11111¶¶¶ \n" +
                "_¶¶¶1111111¶¶¶¶¶¶¶¶1111111111111¶¶¶¶¶¶¶¶1111111¶¶ \n" +
                "_¶¶¶111111111111111111111111111111111111111111¶¶¶ \n" +
                "__¶¶¶1111111111111111¶¶¶¶¶¶¶¶¶111111111111111¶¶¶ \n" +
                "___¶¶¶11111111111111¶¶¶¶¶¶¶¶¶¶¶11111111111111¶¶¶ \n" +
                "____¶¶¶11111111111¶¶¶¶1111111¶¶¶¶11111111111¶¶¶ \n" +
                "____¶¶¶¶1111111111¶¶¶111111111¶¶¶111111111¶¶¶¶ \n" +
                "______¶¶¶¶1111111111111111111111111111111¶¶¶¶ \n" +
                "_______¶¶¶¶1111111111111111111111111111¶¶¶¶ \n" +
                "_________¶¶¶¶¶11111111111111111111111¶¶¶¶¶ \n" +
                "___________¶¶¶¶¶¶11111111111111111¶¶¶¶¶¶ \n" +
                "_____________¶¶¶¶¶¶¶¶¶¶1111¶¶¶¶¶¶¶¶¶¶ \n" +
                "_________________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n\n";
        
        System.out.println("Arghhhh... It's you again.\n" + logo);

        String saveReply = "Saving now....:";
        String input = "";

        //reply to input
        input = s.nextLine();
        while(!input.equals("bye")){    
            String reply = "";   
            String[] inputArr = input.split(" ");

            if (inputArr[0].equals("list")){
                //loop through to the tasks
                for(int i = 0; i < tasks.size(); i++){
                    int numbering = i + 1;
                    reply += (numbering + ".");

                    reply += (tasks.get(i) + "\n    ");
                }
                reply += "\n    I told you save liao loh........";
            } else if (inputArr[0].equals("done")){
                int taskNo = Integer.parseInt(inputArr[1]) - 1;
                tasks.set(taskNo, tasks.get(taskNo).complete());
                reply = "I've marked this task as done: " + tasks.get(taskNo);
            } else {
                //check which type of task
                if (inputArr[0].equals("event")){

                } else if (inputArr[0].equals("deadline")){

                } else {

                }

                // format the input tasks
                Task newTask = new Task(input);
                tasks.add(newTask);

                reply = saveReply + newTask;
            }

            //printing replys
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + reply);
            System.out.println("    ____________________________________________________________");
            
            //next input
            input = s.nextLine();
        }
        System.out.println("Bye. Hope never to see you again!");
    }
}
