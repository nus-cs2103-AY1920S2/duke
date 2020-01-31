import java.util.Scanner;

import java.util.Arrays;
import java.util.List;

class Ui {
        static String stupidLogo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        static String resSpace = "    ";
        static String line = "____________________________________________________________";
        static String greetings = "Hello! I'm Duke\n" + "What can I do for you?";
        static String taskDoneNote = "Nice! I've marked this task as done:";
        static String bye = "Bye. Hope to see you again soon!";
        static String dunno = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
		public static String loadingErrorMsg = "File not exist";
        private boolean isOpen = false;
        private Scanner sc;

        public Ui(Scanner inputSc) {
            this.sc = inputSc;
        }

        void start(String... initials) {
            assert !this.isOpen : "illegal usage of responder";
            System.out.print(resSpace);
            System.out.println(line);
            Scanner sc2;
            for (String str : initials) {
                sc2 = new Scanner(str);
                while (sc2.hasNext()) {
                    System.out.print(resSpace + " ");
                    System.out.println(sc2.nextLine());
                }
            }
            this.isOpen = true;
        }

        void respondLine(String... respondStr) {
            respondLine(Arrays.asList(respondStr));
        }

        void respondLine(List<String> respondStr) {
            assert this.isOpen : "illegal usage of responder";
            Scanner sc2;
            for (String str : respondStr) {
                sc2 = new Scanner(str);
                while (sc2.hasNext()) {
                    System.out.print(resSpace + " ");
                    System.out.println(sc2.nextLine());
                }
            }
        }

        void over(String... remarks) {
            Scanner sc2;
            for (String str : remarks) {
                sc2 = new Scanner(str);
                while (sc2.hasNext()) {
                    System.out.print(resSpace + " ");
                    System.out.println(sc2.nextLine());
                }
            }
            System.out.print(resSpace);
            System.out.println(line);
            System.out.println();
            this.isOpen = false;
        }

        void respond(String... respondStr) {
            respond(Arrays.asList(respondStr));
        }

        void respond(List<String> respondStr) {
            start();
            respondLine(respondStr);
            over();
        }

        boolean hasNextLine() {
            return this.sc.hasNextLine();
        }

        String nextLine() {
            return this.sc.nextLine();
        }
}