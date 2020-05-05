package hakunamatata;

import java.util.Scanner;

/**
 * Represents the program ui which handles the interaction with user.
 *
 * @author Wang Yuting
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private String greeting = "🅷🅴🅻🅻🅾! 🅸'🅼 🅷🅰🅺🆄🅽🅰🅼🅰🆃🅰🆃🅰 ʕ•ω•ʔ\n\n"
            + "I can help you to keep track of you tasks ~!\n\n"
            + "= Guide = \n\n"
            + "    ✎ Create Tasks(Todo, Deadline and Event):\n"
            + "     🇹 🇴 🇩 🇴\n"
            + "        Use \"todo + description\" to create\n"
            + "        E.g. todo taskInfo.\n"
            + "     🇩 🇪 🇦 🇩 🇱 🇮 🇳 🇪\n"
            + "        Use \"deadline + description + /by + date\" to create\n"
            + "        E.g. deadline taskInfo /by 12:00 01/01/2020.\n"
            + "     🇪 🇻 🇪 🇳 🇹\n"
            + "        Use \"event + description + /at + start_date-end_date\" to create\n"
            + "        E.g. event taskInfo /at 12:00 01/01/2020-14:00 01/01/2020\n\n"
            + "    ✎ Other functions: \n"
            + "    ✧ Use \"list\" to check the recorded tasks,\n"
            + "    ✧ Use \"delete + index\" to delete a recorded tasks,\n"
            + "    ✧ Use \"find + search_term\" to find tasks base on their description,\n"
            + "    ✧ Use \"sort + sort_term(description or deadline)\" to sort tasks,\n"
            + "    ✧ Use \"done + task_index\" to mark the task as done. \n"
            + "    ✧ Use \"help\" check the command guide again. \n"
            + "    ✧ Use \"bye\" to exit.\n\n"
            + "ʕ•ω•ʔ 🆆🅷🅰🆃 🅲🅰🅽 🅸 🅳🅾 🅵🅾🆁 🆈🅾🆄 ?";

    /**
     * Displays the welcome message.
     */
    public String getWelcome() {
        return greeting;
    }

    /**
     * Displays an error message.
     *
     * @param ex The exception encountered.
     */
    public String getErrorMessage(HakunaMatataException ex) {
        return ex.errorMessage;
    }

    /**
     * Reads user input.
     *
     * @return The input read from keyboard.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Displays the outpur.
     *
     * @param output The output which will be displayed.
     */
    public void displayOutput(String output) {
        System.out.println(output);
    }
}
