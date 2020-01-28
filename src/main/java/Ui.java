public class Ui {
    public void onStart(TaskList tasks, String filepath) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("----------------------");
        System.out.println("Please enter your commands below:");
        Parser parser = new Parser();
        parser.startParsing(tasks.tasks, filepath);
    }

    public void showLoadingError() {
        System.out.println("ERROR. Previous data cannot be found. A new, empty task list is created.");
    }



}

