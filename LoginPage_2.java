import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class LoginPage_2 extends JFrame {
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    JTextField tfuserid;
    JPasswordField pfPassword;

    public void initialize() {
        /*************** page Panel ***************/

        // Create labels and input fields for User ID and Password
        JLabel lbLoginpage = new JLabel("Login page", SwingConstants.CENTER);
        lbLoginpage.setFont(mainFont);

        JLabel lbEmail = new JLabel("User ID");
        lbEmail.setFont(mainFont);

        tfuserid = new JTextField();
        tfuserid.setFont(mainFont);

        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        // Create and configure a panel to organize the login page elements
        JPanel pagePanel = new JPanel();
        pagePanel.setLayout(new GridLayout(0, 1, 10, 10));
        pagePanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        pagePanel.add(lbLoginpage);
        pagePanel.add(lbEmail);
        pagePanel.add(tfuserid);
        pagePanel.add(lbPassword);
        pagePanel.add(pfPassword);
        pagePanel.setBackground(new Color(0, 150, 255)); // Set background color
        this.getContentPane().setBackground(new Color(0, 150, 255));//Colors that blank space

        /*************** Buttons Panel ***************/

        // Create and configure the Login button
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(mainFont);
        btnLogin.setBackground(new Color(0, 255, 255)); // Set button backgroun color
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the Login button click
                String UserID = tfuserid.getText();
                String Password = String.valueOf(pfPassword.getPassword());

                User user = getAuthenticatedUser(UserID, Password);

                if (user != null) {
                    new ChatClientGUI_2();
                    MainFrame_2 mainFrame = new MainFrame_2();
                    mainFrame.initialize(user);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginPage_2.this,
                            "Email or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        // Create and configure the Cancel button
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);
        btnCancel.setBackground(new Color(0, 255, 255)); // Set button background color
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the Cancel button click
                dispose();
            }

        });

        // Create and configure a panel to organize the buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnCancel);
        buttonsPanel.setBackground(new Color(0, 150, 255)); // Set background color

        /*************** Initialise the frame ***************/

        // Add panels to the frame and configure frame properties
        add(pagePanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);

        setTitle("Login page");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setMinimumSize(new Dimension(350, 450));
        // setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to authenticate the user based on UserID and Password
    private User getAuthenticatedUser(String UserID, String Password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/chatting_application";
        final String USERNAME = "root";
        final String PASSWORD = "root";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to the database successfully...

            String sql = "SELECT * FROM login_info WHERE UserID=? AND Password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, UserID);
            preparedStatement.setString(2, Password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.UserID = resultSet.getString("UserID");
                user.Password = resultSet.getString("Password");
            }

            preparedStatement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Database connexion failed!");
        }

        return user;
    }

    public static void main(String[] args) {
        // The main method is currently empty
    }
}
