package duke.expense;

import java.util.ArrayList;

import duke.common.message.Message;

public class ExpenseList {

    private ArrayList<Expense> expenses;

    /**
     * Creates a new ExpenseList instance.
     */
    public ExpenseList() {
        this.expenses = new ArrayList<>();
    }

    /**
     * Creates new ExpenseList instance with an expense array list.
     * @param expenses The expenses list.
     */
    public ExpenseList(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    /**
     * Adds an expense to the expense list.
     * @param expense The expense to be added.
     */
    public void add(Expense expense) {
        expenses.add(expense);
    }

    /**
     * Gets the expense given an index.
     * @param index The index of the expense item.
     * @return The expense with the index.
     */
    public Expense getExpense(int index) {
        return expenses.get(index - 1);
    }

    /**
     * Deletes and expense given an index.
     * @param index The index of the expense to be deleted.
     */
    public void deleteExpense(int index) {
        expenses.remove(index - 1);
    }

    /**
     * Gets the length of the expense list.
     * @return The length of the list.
     */
    public int getLength() {
        return expenses.size();
    }

    /**
     * Calculates the total expenses in the expense list.
     * @return The total expenses amount.
     */
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
        String output = Message.DIVIDER + "\n";
        for (int i = 0; i < expenses.size(); i++) {
            output += String.format("%d. %s\n", i + 1, expenses.get(i));
        }
        output += Message.DIVIDER + "\n"
                + "Total: $" + String.format("%.2f", getTotalExpense());

        return output;
    }
}