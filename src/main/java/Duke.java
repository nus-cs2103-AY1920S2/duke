import java.time.format.DateTimeParseException;
import java.io.IOException;

public class Duke {
    private static final String space = "    ";
    private static final String home = System.getProperty("user.dir");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try{
            tasks = new TaskList(storage.loadFromSave());
        } catch(UnableToLoadException e){
            ui.showDukeError(e);
            storage.retryLocation(ui.getInput());
        }
    }

    public void run(){
        // initialise scanner
        ui.sayHi();

        // reply to input
        String input = ui.getCommand();

        while (!input.equals("bye")) {
            String reply = "";
            String[] inputArr = input.split(" ");
            Command command = parser.parse(input);
            try {
                switch (command) {
                case BYE:
                    ui.goodBye();
                    break;
                case LIST:
                    reply = tasks.list(inputArr);
                    break;
                case DONE:
                    reply += tasks.checkDone(inputArr);
                    storage.saveToSave(tasks);
                    break;
                case DELETE:
                    reply += tasks.removeTask(inputArr);
                    break;
                case CREATETODO:
                    reply = createNew(inputArr, tasks);
                    storage.saveToSave(tasks);
                    break;
                case CREATEEVENT:
                    reply = createNew(inputArr, tasks);
                    storage.saveToSave(tasks);
                    break;
                case CREATEDEADLINE:
                    reply = createNew(inputArr, tasks);
                    storage.saveToSave(tasks);
                    break;
                default:
                    throw new UnknownCommandException();
                }
                // printing replies
                ui.reply(reply);
            } catch (DukeException e) {
                ui.showDukeError(e);
            } catch (IOException e) {
                ui.showIOError();
            } catch (DateTimeParseException e) {
                ui.showDateTimeError();
            }
            // next input
            input = ui.getCommand();
        }

    }
    public static void main(String[] args) {
        new Duke(home).run();       
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
