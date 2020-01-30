public class Ui {
    public static void greet() {
        System.out.println("Greeting, traveler. My name is Andrew. What can I do for you?");
    }

    public static void goodbye() {
        System.out.println("I shall not trouble you anymore. Farewell, partner.");
    }

    public static void printAdd(String str, int size) {
        System.out.printf("Added:\t %s\n", str);
        System.out.printf("You now have %d tasks in your list\n", size);
    }

    public static void printDel(String str, int size) {
        System.out.printf("Your burden has been lifted, removed: \n\t %s\n", str);
        System.out.printf("You now have %d tasks in your list\n", size);
    }

    public static void printDone(String str, int size) {
        System.out.printf("Task successfully completed: \n\t %s\n", str);
        System.out.printf("You now have %d tasks in your list\n", size);
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }


}
