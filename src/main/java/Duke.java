public class Duke {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String logo2 = "_______  \n"
                +      "|_   _|  ____    ___    "
                +      "      \n"
                +      "  | |    |  |   |   |  \\/\n"
                +      "  |_|    |__|   |   |  /";
        //To run using gradle go and search in Help > Delegate Run > build tools > gradle
        String logo3 = " _____    |   _" +
                "     _| |_    |\n"
                +      "|_____   _|  | |   |_   _|   |__\n"
                +      "|_____  |_|  |_|     |_|     |  |";
        System.out.println("" + logo3);
        System.out.println("Hello! My name is Edith. Tony Stark's Personal Assistant"+ "\n" + "What can I do for you?");

        FastReader fr = new FastReader();
        String textEntered = fr.nextLine();

        TaskManager manager = new TaskManager();

        while(!textEntered.equals("bye")){

            if(textEntered.equals("list")){
                manager.listAllTasks();
                textEntered = fr.nextLine();

            }else if(textEntered.contains("done")){
                String[] helper = textEntered.split(" ");
                int indexOfTaskDone = Integer.parseInt(helper[1]);
                manager.setTaskAsDone(indexOfTaskDone);
                textEntered = fr.nextLine();

            }else if (textEntered.contains("todo") || (textEntered.contains("deadline"))
                    || textEntered.contains("event")) {//create a task
                try {
                    manager.addTask(textEntered);
                }catch (DukeException ex){
                    System.out.println(ex);
                }
                textEntered = fr.nextLine();
            }else if (textEntered.contains("delete")) {
                String[] helper = textEntered.split(" ");
                int indexOfTaskDeleted = Integer.parseInt(helper[1]);
                manager.deleteTask(indexOfTaskDeleted);
                textEntered = fr.nextLine();
            }else{ //nonsence input

                try{
                    manager.nonsenceInput();

                }catch (DukeException ex){
                    System.out.println(ex);
                }
                textEntered = fr.nextLine();

            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }


}




