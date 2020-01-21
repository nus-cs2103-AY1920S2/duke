import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) throws DukeException {
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
        
        System.out.println(logo + "Arghhhh... It's you again.\n" );

        String saveReply = "Saving now....:\n     ";
        String input = "";

        //reply to input
        input = s.nextLine();
        while(!input.equals("bye")){    
            String reply = "";   
            String[] inputArr = input.split(" ");

            try{
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
                    reply = "Okcan, I mark this task as done:\n     " + tasks.get(taskNo);
                } else {
                    //check which type of task
                    int pointer;
                    String nameOfEvent;
                    String dateTime;
                    Task newTask;
                    String date;

                    int arrLength = inputArr.length;

                    if (inputArr[0].equals("event")){
                        pointer = findIndex("/at", inputArr);
                        nameOfEvent = combineString(inputArr, 1, pointer);
                        dateTime = combineString(inputArr, pointer + 1, arrLength);

                        newTask = new Event(nameOfEvent, dateTime);
                        tasks.add(newTask);
                        reply = saveReply + newTask;
                        reply += "\n    Aiyo still got " + tasks.size() + " task(s), what you doing sia";
                    } else if (inputArr[0].equals("deadline")){
                        pointer = findIndex("/by", inputArr);
                        nameOfEvent = combineString(inputArr, 1, pointer);
                        date = combineString(inputArr, pointer + 1, arrLength);

                        newTask = new Deadline(nameOfEvent, date);
                        tasks.add(newTask);
                        reply = saveReply + newTask;
                        reply += "\n    Aiyo still got " + tasks.size() + " task(s), what you doing sia";
                    } else if (inputArr[0].equals("todo")){
                        nameOfEvent = combineString(inputArr, 1, arrLength);
                        newTask = new Todo(input);
                        tasks.add(newTask);

                        reply = saveReply + newTask;
                        reply += "\n    Aiyo still got " + tasks.size() + " task(s), what you doing sia";
                    } else {
                        throw new DukeException(DukeExceptionType.UNKNOWNCOMMAND);
                    }
                }
                //printing replies
                System.out.println("    ____________________________________________________________");
                System.out.println("    " + reply);
                System.out.println("    ____________________________________________________________");
                
                
            } catch (DukeException e){
                System.err.println(e);
            }
            // next input
            input = s.nextLine();
        }
        System.out.println("Yes. FINALLY. Hope never to see you again!");
    }

    public static int findIndex(String s, String[] arr){
        for (int i = 0; i < arr.length; i++){
            if (arr[i].equals(s)){
                return i;
            }
        }
        return -1;
    }

    public static String combineString(String[] arr, int start, int end){
        String ans = "";
        for(int i = start; i < end; i++){
            ans += arr[i];
            if (i != end - 1){
                ans += " ";
            }
        }
        return ans;
    }
}
