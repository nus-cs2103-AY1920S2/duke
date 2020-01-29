public class Ui {

    protected boolean hasEnded;
    protected TaskManager manager;
    protected String horizontalLine = "*******************************************";

    public Ui(TaskManager manager){
        this.hasEnded = false;
        this.manager = manager;
    }

    /**
     * Prints out the introduction text
     */
    public void introduction(){

        System.out.println(horizontalLine);
        //To run using gradle go and search in Help > Delegate Run > build tools > gradle
        String logo3 = " _____    |   _"
                +                     "    _| |_   |\n"
                +      "|_____   _|  | |  |_   _|  |__\n"
                +      "|_____  |_|  |_|    |_|    |  |";

        System.out.println("" + logo3);
        System.out.println("Hello!!!!! My name is Edith. Tony Stark's Personal Assistant"+ "\n"
                + "What can I do for you?");
        System.out.println(horizontalLine);

    }

    /**
     * Handles all the different kinds of inputs the user can have
     * @param textEntered textEntered represents whatever the user types
     */
    public void handleInputs(String textEntered){

        System.out.println(horizontalLine);

        if(textEntered.equals("list")){
            manager.listAllTasks();
        }else if(textEntered.contains("done")){
            String[] helper = textEntered.split(" ");
            int indexOfTaskDone;
            try {
                indexOfTaskDone = Integer.parseInt(helper[1]);
                manager.setTaskAsDone(indexOfTaskDone);
            } catch( ArrayIndexOutOfBoundsException ex){
                System.out.println("Done must be followed by a number");
            }
        }else if (textEntered.contains("todo") || (textEntered.contains("deadline"))
                || textEntered.contains("event")) {//create a task
            try {
                manager.addTask(textEntered);
            }catch (DukeException ex){
                System.out.println(ex.getMessage());
            }
        }else if (textEntered.contains("delete")) {
            try {
                String[] helper = textEntered.split(" ");
                int indexOfTaskDeleted = Integer.parseInt(helper[1]);
                manager.deleteTask(indexOfTaskDeleted);
            }catch (DukeException ex){
                System.out.println(ex);
            } catch (IndexOutOfBoundsException ex){
                System.out.println(ex);
            }
        }else if(textEntered.contains("bye")) {
            manager.saveExistingData();
            this.hasEnded = true;
        }else if(textEntered.contains("find")) {//contains 1 keyword only, as stated in the question
            String[] temp = textEntered.split(" ");
            manager.findTask(temp[1]);

        }else{ //nonsense input
            try{
                manager.nonsenseInput();
            }catch (DukeException ex){
                System.out.println(ex);
            }
        }

        if(textEntered.contains("bye")){
        }
        else{
            System.out.println(horizontalLine);
        }
    }
}
