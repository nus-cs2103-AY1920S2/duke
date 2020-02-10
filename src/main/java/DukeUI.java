
public class DukeUI {

    public DukeUI() {
    }

    public static String showWelcomeMessage () {
        StringBuilder sb = new StringBuilder();
        sb.append("DEMOCRACY IS NON NEGOTIABLE\n");
        sb.append( " _     ___________ ___________ _______   __ ____________ ________  ___ _____\n"
                + "| |   |_   _| ___ \\  ___| ___ \\_   _\\ \\ / / | ___ \\ ___ \\_   _|  \\/  ||  ___|\n"
                + "| |     | | | |_/ / |__ | |_/ / | |  \\ V /  | |_/ / |_/ / | | | .  . || |__  \n"
                + "| |     | | | ___ \\  __||    /  | |   \\ /   |  __/|    /  | | | |\\/| ||  __|\n"
                + "| |_____| |_| |_/ / |___| |\\ \\  | |   | |   | |   | |\\ \\ _| |_| |  | || |___\n"
                + "\\_____/\\___/\\____/\\____/\\_| \\_| \\_/   \\_/   \\_|   \\_| \\_|\\___/\\_|  |_/\\____/\n");

        return sb.toString();
    }

    public static String showByeMsg() {
        return "DEATH IS A PREFERABLE ALTERNATIVE TO COMMUNISM";
    }

    public static String showLoadingError() {
        return "USERTEXT DATA IS NOT FOUND";
    }

    public static String showDoneMsg() {
        return "TASK COMPLETED";
    }
    public static String showCreationMsg() {
        return "UNDERSTOOD";
    }

    public static String showDeleteMsg() {
        return "TASK DELETED";
    }

    public static String showFindMsg() {
        return "TARGET FOUND";
    }

    public static String showCurrentListSize(int size) {
        return "NOW YOU HAVE " + size + " TASKS";}
}
