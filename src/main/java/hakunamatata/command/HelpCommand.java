package hakunamatata.command;

import hakunamatata.HakunaMatataException;
import hakunamatata.Storage;
import hakunamatata.Ui;
import hakunamatata.task.TaskList;

public class HelpCommand extends Command {

    private String helpMessage = "🅷🅰🅺🆄🅽🅰🅼🅰🆃🅰🆃🅰 🅷🅴🆁🅴 ʕ•ω•ʔ\n\n"
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


    public HelpCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws HakunaMatataException {
        return helpMessage;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
