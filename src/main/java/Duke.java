public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Oi\n" + logo + "\n" +
                "     What you waaaaaant?\n" +
                "    _________________________________");

        TaskList taskList = new TaskList();
        taskList.inputLoop();
    }

}

