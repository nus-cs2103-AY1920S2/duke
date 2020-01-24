import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    private static final String space = "   ";
    private static final String line = "____________________________________________________________";
    private static final String home = System.getProperty("user.dir");
    private static final Path path = Paths.get(home, "..", "..", "..", "data", "duke.txt");
    public static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static void main(String[] args) {
        //initialise scanner
        Scanner s = new Scanner(System.in);
        List<Task> tasks;

        tasks = readFromSave();

        sayHi();

        String input = "";

        //reply to input
        input = s.nextLine();
        while(!input.equals("bye")){    
            String reply = "";   
            String[] inputArr = input.split(" ");

            try{
                if (inputArr[0].equals("list")){
                    reply = list(inputArr, tasks);
                } else if (inputArr[0].equals("done")){
                    int taskNo = Integer.parseInt(inputArr[1]) - 1;
                    tasks.set(taskNo, tasks.get(taskNo).complete());
                    reply += "Okcan, I mark this task as done:\n" + space + tasks.get(taskNo);
                    saveToSave(tasks);
                } else if (inputArr[0].equals("delete")) {
                    if (inputArr.length < 2){
                        throw new NoNumberDeleteException();
                    } else {
                        int taskToDelete = Integer.parseInt(inputArr[1]);
                        if (taskToDelete > tasks.size()) {
                            throw new NoSuchDeleteException();
                        } else {
                            String whichTaskDelete = tasks.get(taskToDelete - 1).toString();
                            tasks.remove(taskToDelete - 1);
                            reply += "Okcan. I will remove this task:\n" + space + "  " + whichTaskDelete 
                                    + "\n" + space + "But you still have " + tasks.size() + " task(s) in the list.";
                        }
                    }
                } else {
                    //check which type of task
                    reply = createNew(inputArr, tasks);
                }
                //printing replies
                System.out.println(space + line);
                System.out.println(space + reply);
                System.out.println(space + line);
                
            } catch (DukeException e){
                System.err.println(e);
            } catch (IOException e){
                System.err.println(e);
            } catch (DateTimeParseException e) {
                System.err.println("*************************************************************\n" 
                    + "☹ DATE FORMAT is yyyy/mm/dd!\n  TIME FORMAT is HHmm!"
                    + "\n*************************************************************");
            }
            // next input
            input = s.nextLine();
        }
        System.out.println("\nYes. FINALLY. Hope never to see you again!");
    }
    
    private static void sayHi() {
         String logo = "\n\n____________________¶¶¶¶¶¶¶¶¶¶¶ \n" + "_______________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶ \n"
                + "____________¶¶¶¶¶¶¶¶1111111111¶¶¶¶¶¶¶¶ \n" + "__________¶¶¶¶¶11111111111111111111¶¶¶¶¶¶ \n"
                + "________¶¶¶¶¶1111111111111111111111111¶¶¶¶¶ \n" + "_______¶¶¶¶11111111111111111111111111111¶¶¶¶ \n"
                + "_____¶¶¶¶1111¶¶¶1111111111111111111¶¶¶111¶¶¶¶ \n"
                + "____¶¶¶¶11111¶¶¶1111111111111111111¶¶¶11111¶¶¶ \n"
                + "___¶¶¶1111111¶¶¶1111111111111111111¶¶¶111111¶¶¶ \n"
                + "__¶¶¶¶1111¶¶¶¶¶¶1111111111111111111¶¶¶¶¶¶1111¶¶¶ \n"
                + "__¶¶¶111¶¶¶¶¶¶¶¶1111111111111111111¶¶¶¶¶¶¶¶111¶¶¶ \n"
                + "_¶¶¶111¶¶¶¶___¶¶¶11111111111111111¶¶¶___¶¶¶¶11¶¶¶ \n"
                + "_¶¶¶11¶¶¶____¶¶¶¶¶1111111111111111¶¶¶¶____¶¶¶11¶¶ \n"
                + "_¶¶11¶¶¶_____¶¶¶¶¶¶11111111111111¶¶¶¶¶_____¶¶11¶¶¶ \n"
                + "¶¶¶11¶¶¶_____¶¶¶¶¶¶¶¶11111111111¶¶¶¶¶¶_____¶¶¶1¶¶¶ \n"
                + "¶¶¶11¶¶¶_____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶_____¶¶111¶¶ \n"
                + "¶¶¶111¶¶¶____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶____¶¶¶111¶¶ \n"
                + "¶¶¶111¶¶¶¶___¶¶¶¶¶¶¶¶¶¶11111¶¶¶¶¶¶¶¶¶¶___¶¶¶111¶¶¶ \n"
                + "¶¶¶1111¶¶¶¶___¶¶¶¶¶¶¶¶111111¶¶¶¶¶¶¶¶¶___¶¶¶¶111¶¶¶ \n"
                + "_¶¶111111¶¶¶¶¶¶¶¶¶¶¶¶111111111¶¶¶¶¶¶¶¶¶¶¶¶11111¶¶¶ \n"
                + "_¶¶¶1111111¶¶¶¶¶¶¶¶1111111111111¶¶¶¶¶¶¶¶1111111¶¶ \n"
                + "_¶¶¶111111111111111111111111111111111111111111¶¶¶ \n"
                + "__¶¶¶1111111111111111¶¶¶¶¶¶¶¶¶111111111111111¶¶¶ \n"
                + "___¶¶¶11111111111111¶¶¶¶¶¶¶¶¶¶¶11111111111111¶¶¶ \n"
                + "____¶¶¶11111111111¶¶¶¶1111111¶¶¶¶11111111111¶¶¶ \n"
                + "____¶¶¶¶1111111111¶¶¶111111111¶¶¶111111111¶¶¶¶ \n"
                + "______¶¶¶¶1111111111111111111111111111111¶¶¶¶ \n" + "_______¶¶¶¶1111111111111111111111111111¶¶¶¶ \n"
                + "_________¶¶¶¶¶11111111111111111111111¶¶¶¶¶ \n" + "___________¶¶¶¶¶¶11111111111111111¶¶¶¶¶¶ \n"
                + "_____________¶¶¶¶¶¶¶¶¶¶1111¶¶¶¶¶¶¶¶¶¶ \n" + "_________________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n\n";

        System.out.println(logo + "Arghhhh... It's you again.\n");
    }

    private static List<Task> readFromSave() {
        List<Task> tasks = new ArrayList<Task>();
        try{
            List<String> lines = Files.readAllLines(path);
            String outputLine;
            String[] arr;
            String type;
            boolean completed;
            String name;
            String others;

            for (int i = 0; i < lines.size(); i++) {
                outputLine = lines.get(i);
                arr = outputLine.split("\\| \\|");
                type = arr[0];
                completed = (arr[1].equals("true"));
                name = arr[2];

                switch (type) {
                case "E":
                    others = arr[3];
                    tasks.add(new Event(name, completed, others));
                    break;
                case "D":
                    others = arr[3];
                    tasks.add(new Deadline(name, completed, others));
                    break;
                case "T":
                    tasks.add(new Todo(name, completed));
                    break;
                default:
                }
            }
        } catch(IOException e) {}
        return tasks;
    }
    
    private static void saveToSave(List<Task> tasks) throws IOException {
        String content = "";
        for (int i = 0; i < tasks.size(); i++) {
            content += (tasks.get(i).storeFormat() + "\n");
        }
        Files.writeString(path, content);
    }

    private static String list(String[] arr, List<Task> tasks) throws DateTimeParseException{ 
        String reply = "";
        
        if (arr.length == 1) {
            for (int i = 0; i < tasks.size(); i++) {
                int numbering = i + 1;
                reply += (numbering + ".");
                reply += (tasks.get(i) + "\n    ");
            }
            reply += "\n    I told you save liao loh........";
        } else {
            String dateS = arr[1];
            LocalDate date = LocalDate.parse(dateS, inputFormatter);
            int numbering = 1;
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                if(currentTask instanceof Deadline || currentTask instanceof Event){
                    if(currentTask.compareDate(date)){
                        reply += (numbering++ + ".");
                        reply += (currentTask + "\n    ");
                    }
                } 
            }
            reply += "\n    This are all the tasks with that date";
        }
        return reply;
    }

    private static String createNew(String[] inputArr, List<Task> tasks) throws DukeException {
        int pointer;
        String nameOfEvent;
        Task newTask;
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

            newTask = new Event(nameOfEvent, dateTime);
            
        } else if (inputArr[0].equals("deadline")) {
            pointer = findIndex("/by", inputArr);
            if (pointer == -1 || pointer == arrLength - 1) {
                throw new NoDateException();
            }
            nameOfEvent = combineString(inputArr, 1, pointer);
            date = combineString(inputArr, pointer + 1, arrLength);

            newTask = new Deadline(nameOfEvent, date);
        } else if (inputArr[0].equals("todo")) {
            nameOfEvent = combineString(inputArr, 1, arrLength);
            newTask = new Todo(nameOfEvent);
        } else {
            throw new UnknownCommandException();
         }

        tasks.add(newTask);
        return saveReply + newTask + "\n" + space + "Aiyo still got " + tasks.size() + " task(s), what you doing sia";
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
