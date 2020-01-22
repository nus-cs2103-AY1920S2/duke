import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {

        String horizontalLine = "*******************************************";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //To run using gradle go and search in Help > Delegate Run > build tools > gradle
        String logo3 = " _____    |   _"
                +                     "    _| |_   |\n"
                +      "|_____   _|  | |  |_   _|  |__\n"
                +      "|_____  |_|  |_|    |_|    |  |";

        System.out.println("" + logo3);
        System.out.println("Hello! My name is Edith. Tony Stark's Personal Assistant"+ "\n"
                + "What can I do for you?");
        System.out.println(horizontalLine);

        FastReader fr = new FastReader();
        String textEntered = fr.nextLine();
        ArrayList<Task> level3List = new ArrayList<>();

        TaskManager manager = new TaskManager();

        while(!textEntered.equals("bye")){

            /*
            if(textEntered.equals("list")){
                manager.listAllTasks();
                System.out.println(horizontalLine);
                textEntered = fr.nextLine();


            }else if(textEntered.contains("done")){
                String[] helper = textEntered.split(" ");
                int indexOfTaskDone = Integer.parseInt(helper[1]);
                manager.setTaskAsDone(indexOfTaskDone);
                System.out.println(horizontalLine);
                textEntered = fr.nextLine();


            }else if (textEntered.contains("todo") || (textEntered.contains("deadline"))
                    || textEntered.contains("event")) {//create a task
                try {
                    manager.addTask(textEntered);
                }catch (DukeException ex){
                    System.out.println(ex);
                }
                System.out.println(horizontalLine);
                textEntered = fr.nextLine();

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
                System.out.println(horizontalLine);
                textEntered = fr.nextLine();

            }else{ //nonsense input

                try{
                    manager.nonsenseInput();

                }catch (DukeException ex){
                    System.out.println(ex);
                }
                System.out.println(horizontalLine);
                textEntered = fr.nextLine();


            }

             */
            System.out.println(horizontalLine);
            if(textEntered.contains("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0 ; i < level3List.size() ; i++){
                    System.out.println(i+1 + ". " + level3List.get(i));
                }
            }else if(textEntered.contains("done")) {

                System.out.println("Nice! I've marked this task as done: ");
                String[] helper = textEntered.split(" ");
                int indexOfTaskDone = Integer.parseInt(helper[1]);
                level3List.get(indexOfTaskDone-1).markAsDone();

                System.out.println("  " + level3List.get(indexOfTaskDone-1).toString());

            }else{

                level3List.add(new Task(textEntered));
                System.out.println("added: "+ textEntered);
            }
            System.out.println(horizontalLine);
            textEntered = fr.nextLine();




        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }


}




