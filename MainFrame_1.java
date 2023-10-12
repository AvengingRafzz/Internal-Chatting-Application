import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame_1 extends JFrame {

    // Method to initialize the main frame
    public void initialize(User user) {
        // Create a new JFrame with the title "Chat Application"
        JFrame frame = new JFrame("Chat Application");

        // Create a label with a welcome message and center it
        JLabel label = new JLabel("Welcome to the chat application");
        label.setHorizontalAlignment(JLabel.CENTER);

        // Create an "OK" button
        JButton okbutton = new JButton("OK");

        // Add an action listener to the "OK" button to close the frame when clicked
        okbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
            }
        });

        // Create a JPanel to organize the label and button using BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(okbutton, BorderLayout.SOUTH);
        panel.setBackground(new Color(0,123,167)); // Set background color

        // Add the panel to the frame
        frame.add(panel);

        // Set the frame's position, size, and other properties
        frame.setBounds(200, 325, 400, 200);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // Make the frame visible
    }
}
