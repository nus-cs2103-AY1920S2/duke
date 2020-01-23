import java.lang.String;

public class Parser {

    String[] commandArr;
    String command;
    String date;

    public Parser() {

    }

    public String[] parseInput(String input) {

        String[] inputArr = new String[3];

       if(input.equals("list") || input.equals("bye")) {

           inputArr[0] = input;
           inputArr[1] = "";
           inputArr[2] = "";

           return inputArr;

       } else if (input.startsWith("done") || input.startsWith("delete")) {

         String[] splitInput = input.split(" ");

           if( splitInput.length < 2) {

               inputArr[0] = "MissingTaskNumber";
               inputArr[1] = "";
               inputArr[2] = "";

               return inputArr;

           } else {

               return splitInput;

           }

       }

        int index = input.indexOf("/");
        int whiteSpaceIndex = input.indexOf(" ");

        if(whiteSpaceIndex == -1) {

            inputArr[0] = input;
            inputArr[1] = "EmptyDescription";
            inputArr[2] = "";

        } else if (input.startsWith("todo")) {

            inputArr[0] = "todo";
            inputArr[1] = input.substring(whiteSpaceIndex+ 1);
            inputArr[2] = "";

        } else if (index == -1) {

            inputArr[0] = input.substring(0, whiteSpaceIndex);
            inputArr[1] = "EmptyDate";
            inputArr[2] = "";

        } else {

            inputArr[0] = input.substring(0, whiteSpaceIndex);
            inputArr[1] = input.substring(whiteSpaceIndex + 1, index);
            inputArr[2] = input.substring(index + 4);

        }
        return inputArr;
    }


}
