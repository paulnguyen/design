

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author pnguyen
 */
public class SwingExampleWithButton implements ActionListener, MouseListener {

    private JFrame frame = new JFrame("SwingDemo");
    private JPanel panel = new JPanel();
    private JButton sayButton = new JButton("I say!");
    private JLabel sayLabel = new JLabel("Say something:");
    private JTextArea sayText = new JTextArea();

    private void addMenu(JFrame frame) {
        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('x');
        exitItem.addActionListener(this);
        file.add(exitItem);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        frame.setJMenuBar(menuBar);
    }

    private void arrangeComponents(JFrame frame) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        sayText.setPreferredSize(new Dimension(200, 50));
        sayText.setAlignmentX(Component.CENTER_ALIGNMENT);
        sayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sayButton.addMouseListener(this);
        panel.add(sayLabel);
        panel.add(sayText);
        panel.add(sayButton);
        frame.add(panel);
    }

    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMenu(frame);
        arrangeComponents(frame);
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == sayButton) {
            JOptionPane.showMessageDialog(frame, sayText.getText(),
                    "You said", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
