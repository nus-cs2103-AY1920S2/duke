package duke;

public class Ui {
    private String logo;
    private String lineBreak;

    public void getIntroduction() {
        System.out.println(lineBreak);
        System.out.println("Hello I am \n" + logo
                + "\nWhat can I do for you?");
        System.out.println(lineBreak);
    }

    public void exit() {
        System.out.println(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }

    public void lineBreak() {
        System.out.println(lineBreak);
    }

    public Ui() {
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        lineBreak = "_____________________________" +
                "_______________________________";
    }
}
