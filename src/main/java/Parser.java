import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    private String command;
    private Ui uiDisplay;
    private Storage fileStorage;
    public Parser(){
    }

    public Parser(String command,Ui uiDisplay,Storage fileStorage){
        this.command = command ;
        this.uiDisplay = uiDisplay;
        this.fileStorage = fileStorage;
    }

    public String getCommandType (String command){
        String [] s ;
        s = command.split(" ");
        return s[0];
    }

    public void todoTaskCommand(String command, ArrayList<Task> tasks,Ui uiDisplay)throws Exception{
        String [] s = command.split("todo ");
        tasks.add(new Task(s[1]));
        uiDisplay.topTwoLine();
        System.out.println("   "+tasks.get(tasks.size()-1).toString());
        uiDisplay.bottomTwoLine(tasks);
        fileStorage.createFile("todo",s[1]);
    }
    public void eventCommand(String command,ArrayList<Task> tasks,Ui uiDisplay) throws Exception{
        String [] s = command.split("event ");
        String [] temp = s[1].split("/");
        String taskName = temp[0];
        String date = formatDate(temp[1].substring(3,temp[1].length()));
        uiDisplay.topTwoLine();
        tasks.add(new Deadline(taskName,date));
        System.out.println("     "+tasks.get(tasks.size()-1).toString());
        uiDisplay.bottomTwoLine(tasks);
        fileStorage.createFile("deadline",taskName+date);
    }

    public void deadlineCommand(String command,ArrayList<Task> tasks,Ui uiDisplay) throws Exception{
        String [] s = command.split("deadline ");
        String[] temp = s[1].split("/");
        String taskName = temp[0];
        String date = formatDate(temp[1].substring(3,temp[1].length()));
        uiDisplay.topTwoLine();
        tasks.add(new Event(taskName, date));
        System.out.println("     " + tasks.get(tasks.size()-1).toString());
        uiDisplay.bottomTwoLine(tasks);
        fileStorage.createFile("deadline",taskName+date);
    }

    public void doneCommand(String command,ArrayList<Task> tasks,Ui uiDisplay){
        String [] temp = command.split(" ");
        int arrPos = Integer.parseInt(temp[1]);
        tasks.get(arrPos-1).setDone();
        uiDisplay.markDone();
        System.out.println("    ["+tasks.get(arrPos-1).getStatusIcon()+"] "+tasks.get(arrPos-1).getDescription());
        System.out.println("  -------------");
    }

    public void deleteCommand(String command,ArrayList<Task> tasks,Ui uiDisplay){
        String [] temp = command.split(" ");
        int arrPos = Integer.parseInt(temp[1]);
        uiDisplay.removeMes();
        System.out.println("    "+tasks.get(arrPos-1).toString());
        tasks.remove(arrPos-1);
        uiDisplay.bottomTwoLine(tasks);
    }

    public static String formatDate(String date) throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1=formatter.parse(date);
        formatter = new SimpleDateFormat("E, MMM d yyyy");
        return (formatter.format(date1));
    }
}


