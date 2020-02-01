import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

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

    public String todoTaskCommand(String command, ArrayList<Task> tasks,Ui uiDisplay,File f)throws Exception{
        String [] s = command.split("todo ");
        tasks.add(new Task(s[1]));
        String customeriseTopMes = uiDisplay.getTopTwoLine();
        String taskMes = "   "+tasks.get(tasks.size()-1).toString();
        String customeriseBottomMes = uiDisplay.getBottomTwoLine(tasks);
        fileStorage.writeFile("todo",s[1],f);
        return uiDisplay.parserOutputMess(customeriseTopMes,taskMes,customeriseBottomMes);
    }

    public String eventCommand(String command,ArrayList<Task> tasks,Ui uiDisplay,File f) throws Exception{
        String [] s = command.split("event ");
        String [] temp = s[1].split("/");
        String taskName = temp[0];
        String date = formatDate(temp[1].substring(3,temp[1].length()));
        String customeriseTopMes = uiDisplay.getTopTwoLine();
        tasks.add(new Deadline(taskName,date));
        String taskMes = "     "+tasks.get(tasks.size()-1).toString();
        String customeriseBottomMes = uiDisplay.getBottomTwoLine(tasks);
        fileStorage.writeFile("deadline",taskName+date,f);
        return uiDisplay.parserOutputMess(customeriseTopMes,taskMes,customeriseBottomMes);
    }

    public String deadlineCommand(String command,ArrayList<Task> tasks,Ui uiDisplay,File f) throws Exception{
        String [] s = command.split("deadline ");
        String[] temp = s[1].split("/");
        String taskName = temp[0];
        String date = formatDate(temp[1].substring(3,temp[1].length()));
        String customeriseTopMes = uiDisplay.getTopTwoLine();
        tasks.add(new Event(taskName, date));
        String taskMes = "     " + tasks.get(tasks.size()-1).toString();
        String customeriseBottomMes = uiDisplay.getBottomTwoLine(tasks);
        fileStorage.writeFile("deadline",taskName+date,f);
        return uiDisplay.parserOutputMess(customeriseTopMes,taskMes,customeriseBottomMes);
    }

    public String doneCommand(String command,ArrayList<Task> tasks,Ui uiDisplay){
        if(tasks.size()>0){
            String [] temp = command.split(" ");
            int arrPos = Integer.parseInt(temp[1]);
            tasks.get(arrPos-1).setDone();
            String doneMesTop = uiDisplay.markAsDone();
            String doneResult = "    ["+tasks.get(arrPos-1).getStatusIcon()+"] "+tasks.get(arrPos-1).getDescription();
            String doneMesBot = "  -------------";
            return uiDisplay.parserOutputMess(doneMesTop,doneResult,doneMesBot);
        }else{
            return "  Sorry there is no any task for you to do the command";
        }

    }

    public void deleteCommand(String command,ArrayList<Task> tasks,Ui uiDisplay){
        String [] temp = command.split(" ");
        int arrPos = Integer.parseInt(temp[1]);
        uiDisplay.removeTaskMes();
        System.out.println("    "+tasks.get(arrPos-1).toString());
        tasks.remove(arrPos-1);
        uiDisplay.getBottomTwoLine(tasks);
    }

    public static String formatDate(String date) throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1=formatter.parse(date);
        formatter = new SimpleDateFormat("E, MMM d yyyy");
        return (formatter.format(date1));
    }
}


