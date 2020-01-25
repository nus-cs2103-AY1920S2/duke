import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

public class Duke {
    private static final String space = "    ";
    private static final String line = "   " + "<------------------------------------------------------------>";
    private static final String errorLine = "   " + "**************************************************************";
    private static final String home = System.getProperty("user.dir");
    public static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static void main(String[] args) {
        //initialise scanner
        Scanner s = new Scanner(System.in);
        Storage storage = new Storage(home);
        TaskList tasks = new TaskList(storage.loadFromSave());
        Parser parser = new Parser();

        sayHi();

        String input = "";

        //reply to input
        input = s.nextLine();

        while(!input.equals("bye")){
            String reply = "";
            String[] inputArr = input.split(" ");
            Command command = parser.parse(input);
            try{
                switch (command){
                    case BYE:
                        goodBye();
                        break;
                    case LIST:
                        reply = list(inputArr, tasks);
                        break;
                    case DONE:
                        int taskNo = Integer.parseInt(inputArr[1]) - 1;
                        tasks.checkDone(taskNo);
                        reply += "Okcan, I mark this task as done:\n" + space + tasks.getTask(taskNo);
                        storage.saveToSave(tasks);
                        break;
                    case DELETE:
                        if (inputArr.length < 2) {
                            throw new NoNumberDeleteException();
                        } else {
                            int taskToDelete = Integer.parseInt(inputArr[1]);
                            if (taskToDelete > tasks.size()) {
                                throw new NoSuchDeleteException();
                            } else {
                                String whichTaskDelete = tasks.getTask(taskToDelete - 1).toString();
                                tasks.removeTask(taskToDelete - 1);
                                reply += "Okcan. I will remove this task:\n" + space + "  " + whichTaskDelete + "\n" + space
                                        + "But you still have " + tasks.size() + " task(s) in the list.";
                            }
                        }
                        break;
                    case CREATETODO:
                        reply = createNew(inputArr, tasks);
                        break;
                    case CREATEEVENT:
                        reply = createNew(inputArr, tasks);
                        break;
                    case CREATEDEADLINE:
                        reply = createNew(inputArr, tasks);
                        break;
                    default:   
                        throw new UnknownCommandException();
                }    
                //printing replies
                System.out.println(line);
                System.out.println(space + reply);
                System.out.println(line);
            }catch (DukeException e){
                System.err.println(e);
            } catch (IOException e){
                System.err.println(e);
            } catch (DateTimeParseException e) {
                System.err.println(errorLine + "\n    ☹ DATE FORMAT is yyyy/mm/dd!\n" + space + "  TIME FORMAT is HHmm!\n" + errorLine);
            }
            // next input
            input = s.nextLine();
        }
            
    }

    private static void goodBye() {
        System.out.println(line);
        System.out.println(space + "Yes. FINALLY. Hope never to see you again!");
        System.out.println(line);
    }
    
    private static void sayHi() {
         String logo = "\n\n                    ¶¶¶¶¶¶¶¶¶¶¶ \n" 
                + "               ¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶ \n"
                + "            ¶¶¶¶¶¶¶¶1111111111¶¶¶¶¶¶¶¶ \n" 
                + "          ¶¶¶¶¶11111111111111111111¶¶¶¶¶¶ \n"
                + "        ¶¶¶¶¶1111111111111111111111111¶¶¶¶¶ \n" 
                + "       ¶¶¶¶11111111111111111111111111111¶¶¶¶ \n"
                + "     ¶¶¶¶1111¶¶¶1111111111111111111¶¶¶111¶¶¶¶ \n"
                + "    ¶¶¶¶11111¶¶¶1111111111111111111¶¶¶11111¶¶¶ \n"
                + "   ¶¶¶1111111¶¶¶1111111111111111111¶¶¶111111¶¶¶ \n"
                + "  ¶¶¶¶1111¶¶¶¶¶¶1111111111111111111¶¶¶¶¶¶1111¶¶¶ \n"
                + "  ¶¶¶111¶¶¶¶¶¶¶¶1111111111111111111¶¶¶¶¶¶¶¶111¶¶¶ \n"
                + " ¶¶¶111¶¶¶¶   ¶¶¶11111111111111111¶¶¶   ¶¶¶¶11¶¶¶ \n"
                + " ¶¶¶11¶¶¶    ¶¶¶¶¶1111111111111111¶¶¶¶    ¶¶¶11¶¶ \n"
                + " ¶¶11¶¶¶     ¶¶¶¶¶¶11111111111111¶¶¶¶¶     ¶¶11¶¶¶ \n"
                + "¶¶¶11¶¶¶     ¶¶¶¶¶¶¶¶11111111111¶¶¶¶¶¶     ¶¶¶1¶¶¶ \n"
                + "¶¶¶11¶¶¶     ¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶     ¶¶111¶¶ \n"
                + "¶¶¶111¶¶¶    ¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶    ¶¶¶111¶¶ \n"
                + "¶¶¶111¶¶¶¶   ¶¶¶¶¶¶¶¶¶¶11111¶¶¶¶¶¶¶¶¶¶   ¶¶¶111¶¶¶ \n"
                + "¶¶¶1111¶¶¶¶   ¶¶¶¶¶¶¶¶111111¶¶¶¶¶¶¶¶¶   ¶¶¶¶111¶¶¶ \n"
                + " ¶¶111111¶¶¶¶¶¶¶¶¶¶¶¶111111111¶¶¶¶¶¶¶¶¶¶¶¶11111¶¶¶ \n"
                + " ¶¶¶1111111¶¶¶¶¶¶¶¶1111111111111¶¶¶¶¶¶¶¶1111111¶¶ \n"
                + " ¶¶¶111111111111111111111111111111111111111111¶¶¶ \n"
                + "  ¶¶¶1111111111111111¶¶¶¶¶¶¶¶¶111111111111111¶¶¶ \n"
                + "   ¶¶¶11111111111111¶¶¶¶¶¶¶¶¶¶¶11111111111111¶¶¶ \n"
                + "    ¶¶¶11111111111¶¶¶¶1111111¶¶¶¶11111111111¶¶¶ \n"
                + "    ¶¶¶¶1111111111¶¶¶111111111¶¶¶111111111¶¶¶¶ \n"
                + "      ¶¶¶¶1111111111111111111111111111111¶¶¶¶ \n" 
                + "       ¶¶¶¶1111111111111111111111111111¶¶¶¶ \n"
                + "         ¶¶¶¶¶11111111111111111111111¶¶¶¶¶ \n" 
                + "           ¶¶¶¶¶¶11111111111111111¶¶¶¶¶¶ \n"
                + "             ¶¶¶¶¶¶¶¶¶¶1111¶¶¶¶¶¶¶¶¶¶ \n" 
                + "                 ¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n\n";
        System.out.println(logo);
        System.out.println(line);
        System.out.println(space + "Arghhhh... It's you again.");
        System.out.println(line);
    }

    private static String list(String[] arr, TaskList tasks) throws DateTimeParseException{ 
        String reply = "";
        
        if (arr.length == 1) {
            for (int i = 0; i < tasks.size(); i++) {
                int numbering = i + 1;
                reply += (numbering + ".");
                reply += (tasks.getTask(i) + "\n" + space);
            }
            reply += "\n" + space + "I told you save liao loh........";
        } else {
            String dateS = arr[1];
            LocalDate date = LocalDate.parse(dateS, inputFormatter);
            int numbering = 1;
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.getTask(i);
                if(currentTask instanceof Deadline || currentTask instanceof Event){
                    if(currentTask.compareDate(date)){
                        reply += (numbering++ + ".");
                        reply += (currentTask + "\n" + space );
                    }
                } 
            }
            reply += ("\n" + space + "This are all the tasks with that date");
        }
        return reply;
    }

    private static String createNew(String[] inputArr, TaskList tasks) throws DukeException {
        int pointer;
        String nameOfEvent;
        String dateTime;
        String date;
        
        int arrLength = inputArr.length;

        String saveReply = "Saving now....:\n     ";
        
        if (inputArr[0].equals("event")) {
            pointer = findIndex("/at", inputArr);
            if (pointer == -1 || pointer == arrLength - 1) {
                throw new NoDateTimeException();
            }
            nameOfEvent = combineString(inputArr, 1, pointer);
            dateTime = combineString(inputArr, pointer + 1, arrLength);
            saveReply += tasks.addEvent(nameOfEvent, dateTime);
        } else if (inputArr[0].equals("deadline")) {
            pointer = findIndex("/by", inputArr);
            if (pointer == -1 || pointer == arrLength - 1) {
                throw new NoDateException();
            }
            nameOfEvent = combineString(inputArr, 1, pointer);
            date = combineString(inputArr, pointer + 1, arrLength);
            saveReply += tasks.addDeadline(nameOfEvent, date);
        } else if (inputArr[0].equals("todo")) {
            nameOfEvent = combineString(inputArr, 1, arrLength);
            saveReply += tasks.addTodo(nameOfEvent);
        } else {
            throw new UnknownCommandException();
         }

        return saveReply  + "\n" + space + "Aiyo still got " + tasks.size() + " task(s), what you doing sia";
    }

    private static int findIndex(String s, String[] arr){
        for (int i = 0; i < arr.length; i++){
            if (arr[i].equals(s)){
                return i;
            }
        }
        return -1;
    }

    private static String combineString(String[] arr, int start, int end){
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
