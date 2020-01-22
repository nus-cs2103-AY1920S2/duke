public class Duke {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke"+ "\n" + "What can I do for you?");

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




