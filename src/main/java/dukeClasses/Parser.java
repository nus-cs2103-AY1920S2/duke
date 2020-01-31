package dukeClasses;

public class Parser {



    public Parser(){
    }

    public int handleDoneCommands(String textEntered){
        String[] temporary = textEntered.split(" ");
        int indexOfTaskDone = Integer.parseInt(temporary[1]);
        return indexOfTaskDone;
    }

    public int handleDeleteCommands(String textEntered){
        String[] temporary = textEntered.split(" ");
        int indexOfTaskToDelete = Integer.parseInt(temporary[1]);
        return indexOfTaskToDelete;
    }

    public String[] handleFindCommands(String textEntered){
        return textEntered.split(" ");
    }


}
