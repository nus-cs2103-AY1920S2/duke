public class Parser {
    public Parser() {}

    public static Command parse(String command) throws DukeException {
        String[] str = command.split(" ");
        if (command.equals("list")) {
            ListCommand listCommand = new ListCommand(command);
            return listCommand;
        } else if (command.equals("bye")) {
            ByeCommand byeCommand = new ByeCommand(command);
            return byeCommand;
        } else if (str[0].equals("done")) {
            try {
                if (command.length() <= 4) {
                    throw new DoneException();
                } else {
                    DoneCommand doneCommand = new DoneCommand(str[1]);
                    return doneCommand;
                }
            } catch (DoneException doneErr) {
                System.out.println(doneErr);
            }
        } else if (str[0].equals("deadline")) {
            try {
                if (command.length() <= 8) {
                    throw new EmptyException();
                } else {
                    String[] d = command.split(" ", 2);
                    String[] helper = d[1].split("/", 2);
                    String[] b = helper[1].split(" ", 2);
                    Deadline deadline = new Deadline(helper[0], b[1]);
                    AddCommand addCommand = new AddCommand(command, deadline);
                    return addCommand;
                }
            } catch (EmptyException emErr) {
                System.out.println(emErr);
            }
        } else if (str[0].equals("event")) {
            try {
                if (command.length() <= 5) {
                    throw new EmptyException();
                } else {
                    String[] e = command.split(" ", 2);
                    String[] helper = e[1].split("/", 2);
                    String[] a = helper[1].split(" ", 2);
                    Event event = new Event(helper[0], a[1]);
                    AddCommand addCommand = new AddCommand(command, event);
                    return addCommand;
                }
            } catch (EmptyException emErr) {
                System.out.println(emErr);
            }
        } else if (str[0].equals("todo")) {
            try {
                if (command.length() <= 4) {
                    throw new EmptyException();
                } else {
                    String[] e = command.split(" ", 2);
                    Todo todo = new Todo(e[1]);
                    AddCommand addCommand = new AddCommand(command, todo);
                    return addCommand;
                }
            } catch (EmptyException emErr) {
                System.out.println(emErr);
            }
        } else if (str[0].equals("delete")) {
            try {
                if (command.length() <= 6) {
                    throw new EmptyException();
                } else {
                    int num = Integer.parseInt(str[1]);
                    DeleteCommand deleteCommand = new DeleteCommand(command, num);
                    return deleteCommand;
                }
            } catch (EmptyException emErr) {
                System.out.println(emErr);
            }
        } else if (str[0].equals("find")) {
            try {
                if (command.length() <= 4) {
                    throw new EmptyException();
                } else {
                    FindCommand findCommand = new FindCommand(command, str[1]);
                    return findCommand;
                }
            } catch (EmptyException emErr){
                System.out.println(emErr);
            }
        } else {
            try {
                throw new UnknownException();
            } catch (UnknownException unknownErr) {
                System.out.println(unknownErr);
            }
        }
        EmptyCommand emptyCommand = new EmptyCommand();
        return emptyCommand;
    }
}
