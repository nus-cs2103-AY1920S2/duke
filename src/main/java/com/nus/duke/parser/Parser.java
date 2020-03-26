package com.nus.duke.parser;

import javafx.util.Pair;

public class Parser {

    /**
     * <p> Splits an input string based on a white space character </p>
     *
     * @param   input       Input string
     * @return              (left, right) split of the string
     * @see                 com.nus.duke.parser.Parser#tokenize(String, String)
     */
    public static Pair<String, String> tokenize(String input) {
        return tokenize(input, " ");
    }

    /**
     * <p>  Splits a string into a (left, right) pair based on a supplied expression
     *      For this application, the left is deemed as the command issued by the user
     *      and the right is deemed as the condition
     * </p>
     *
     * @param    input          text string, such as "list books"
     * @param    expr           Split-by expression
     * @return                  Pair of (command, condition)
     */
    public static Pair<String, String> tokenize(String input, String expr) {

        String[] res        = input.split(expr, 2);
        String command      = res[0];
        String condition    = res.length > 1 ? res[1] : null;
        return new Pair<>(command, condition);
    }
}
