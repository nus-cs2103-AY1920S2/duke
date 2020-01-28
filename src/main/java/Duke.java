public class Duke {


    public static void main(String[] args) {

        String horizontalLine = "*******************************************";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
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



        FastReader fr = new FastReader();


        TaskManager manager = new TaskManager();

        manager.loadExistingData();
        String textEntered = fr.nextLine();

        while(!textEntered.equals("bye")){

            System.out.println(horizontalLine);

            if(textEntered.equals("list")){
                manager.listAllTasks();
                System.out.println(horizontalLine);

            }else if(textEntered.contains("done")){

                String[] helper = textEntered.split(" ");
                int indexOfTaskDone;
                try {
                    indexOfTaskDone = Integer.parseInt(helper[1]);
                    manager.setTaskAsDone(indexOfTaskDone);
                    System.out.println(horizontalLine);
                } catch( ArrayIndexOutOfBoundsException ex){
                    System.out.println("Done must be followed by a number");
                }

            }else if (textEntered.contains("todo") || (textEntered.contains("deadline"))
                    || textEntered.contains("event")) {//create a task
                try {
                    manager.addTask(textEntered);
                }catch (DukeException ex){
                    System.out.println(ex);
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

            }else{ //nonsense input
                    try{
                        manager.nonsenseInput();

                    }catch (DukeException ex){
                        System.out.println(ex);
                    }
                }

            System.out.println(horizontalLine);
            textEntered = fr.nextLine();

        }


        //Must say bye to save data!
        manager.saveExistingData();


        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }


}




