

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author pnguyen
 */
public class EmptySwingWindow {

    private JFrame frame = new JFrame("SwingDemo");

    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(200, 200));
        frame.pack();
        frame.setVisible(true);
    }
}
