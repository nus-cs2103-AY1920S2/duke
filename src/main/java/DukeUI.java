
public class DukeUI {

    public DukeUI() {
    }

    public static void showWelcomeMessage () {
        String logo = " _     ___________ ___________ _______   __ ____________ ________  ___ _____\n"
                + "| |   |_   _| ___ \\  ___| ___ \\_   _\\ \\ / / | ___ \\ ___ \\_   _|  \\/  ||  ___|\n"
                + "| |     | | | |_/ / |__ | |_/ / | |  \\ V /  | |_/ / |_/ / | | | .  . || |__  \n"
                + "| |     | | | ___ \\  __||    /  | |   \\ /   |  __/|    /  | | | |\\/| ||  __|\n"
                + "| |_____| |_| |_/ / |___| |\\ \\  | |   | |   | |   | |\\ \\ _| |_| |  | || |___\n"
                + "\\_____/\\___/\\____/\\____/\\_| \\_| \\_/   \\_/   \\_|   \\_| \\_|\\___/\\_|  |_/\\____/\n";
        System.out.println(logo);
        System.out.println("LIBERTY PRIME IS ONLINE");
        System.out.println("DEMOCRACY IS NON NEGOTIABLE");
    }

    public static void showByeMsg() {
        System.out.println("DEATH IS A PREFERABLE ALTERNATIVE TO COMMUNISM");
    }

    public static void showLoadingError() {
        System.out.println("USERTEXT DATA IS NOT FOUND");
    }

    public static void showDoneMsg() {
        System.out.println("TASK COMPLETED. DEMOCRACY IS NON-NEGOTIABLE");
    }
    public static void showCreationMsg() {
        System.out.println("UNDERSTOOD. CHANCE OF FAILURE: 0%");
    }

    public static void showDeleteMsg() {
        System.out.println("OBSTRUCTION DETECTED. PROBABILITY OF DETERENCE: 0%");
    }

    public static void showFindMsg() {
        System.out.println("TARGET FOUND");
    }
}
