package com.nus.duke.tasks;

import com.nus.duke.parser.Parser;
import javafx.util.Pair;

public class DisplayTaskFormatter {
    private static String changeExprOnAt(String initialExpr) {
        final String token = "/at";
        Pair<String, String> splittedExpr = Parser.tokenize(initialExpr, token);
        return String.format("%s (at: %s)", splittedExpr.getKey(), splittedExpr.getValue());
    }

    private static String changeExprOnBy(String initialExpr) {
        final String token = "/by";
        Pair<String, String> splittedExpr = Parser.tokenize(initialExpr, token);
        return String.format("%s (by: %s)", splittedExpr.getKey(), splittedExpr.getValue());
    }

    /**
     * Defines the string expression for a Task
     *
     * @param   task    Tasks class object
     * @return  String  String representation of the class
     * @see com.nus.duke.tasks.Tasks
     */
    public static String stringify(Tasks task){
        StringBuilder strBldr = new StringBuilder();
        strBldr.append(String.format("[%s][%s] ", task.getStatus().toString(), task.getType().toString()));

        if (task.getName().contains("/by"))
            strBldr.append(changeExprOnBy(task.getName()));

        else if (task.getName().contains("/at"))
            strBldr.append(changeExprOnAt(task.getName()));

        else
            strBldr.append(String.format("%s", task.getName()));

        return strBldr.toString();
    }
}
