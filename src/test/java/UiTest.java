import duke.Ui;

/**
 * A class that tests the UI class.
 */
class UiTest {

    /**
     * Driver function of the test class.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Ui testUi = new Ui();
        testUiLine(testUi);
        testUiEnd(testUi);
    }

    /**
     * Tests the printing line function of the UI.
     * @param ui the Ui that needs to be tested.
     */
    static void testUiLine(Ui ui) {
        ui.showLine();
    }

    /**
     * Tests the terminating message function of the UI.
     * @param ui the UI that needs to be tested.
     */
    static void testUiEnd(Ui ui) {
        ui.end();
    }
}