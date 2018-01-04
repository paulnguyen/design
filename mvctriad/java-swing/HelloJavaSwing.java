

/**
 *
 * @author pnguyen
 */
public class HelloJavaSwing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // DEMO1 - Basic Swing Window
        EmptySwingWindow demo1 = new EmptySwingWindow();
        //demo1.createAndShowGUI();

        // DEMO2 - Swing Window with MenuBar
        MenuBarSwingDemo demo2 = new MenuBarSwingDemo();
        //demo2.createAndShowGUI();

        // DEMO3 - Swing Windows with MenuBar and Button
        SwingExampleWithButton demo3 = new SwingExampleWithButton();
        demo3.createAndShowGUI();

    }
}
