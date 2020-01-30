public class UserInterface {
    public static final int MAX_STRING_LENGTH = 60;

    public UserInterface() {}

    public void show(String message) {
        System.out.println("    ____________________________________________________________");

        for (String str : message.split("\n")) {
            int index = 0;
            while (index < str.length()) {
                String indentation = "      ";
                int print_length = Math.min(str.length() - index, MAX_STRING_LENGTH - 2);

                System.out.println(indentation + str.substring(index, index + print_length));
                index += print_length;
            }
        }
        
        System.out.println("    ____________________________________________________________");
    }
}