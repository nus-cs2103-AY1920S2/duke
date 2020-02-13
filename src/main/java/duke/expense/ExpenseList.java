package duke.expense;

import java.util.ArrayList;

public class ExpenseList {

    private ArrayList<Expense> expenses;

    public ExpenseList() {
        this.expenses = new ArrayList<>();
    }

    public ExpenseList(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public void add(Expense expense) {
        expenses.add(expense);
    }

    public Expense getExpense(int index) {
        return expenses.get(index - 1);
    }

    public void deleteExpense(int index) {
        expenses.remove(index - 1);
    }

    public int getLength() {
        return expenses.size();
    }

    public double getTotalExpense() {
        double totalExpense = 0;
        for (Expense expense : expenses) {
            totalExpense += expense.getExpense();
        }
        return totalExpense;
    }

    /**
     * Formats the expense list to be saved.
     * @return The expense in save format.
     */
    public String toSaveFormat() {
        String output = "";
        for (Expense expense : expenses) {
            output += expense.getDate() + " | "
                    + expense.getExpense() + " | "
                    + expense.getDescription() + "\n";
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "----------\n";
        for (int i = 0; i < expenses.size(); i++) {
            output += String.format("%d. %s\n", i + 1, expenses.get(i));
        }
        output += "----------\n"
                + "Total: $" + String.format("%.2f", getTotalExpense()) + "\n"
                + "----------";

        return output;
    }
}