import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ChatClientGUI_1 {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ChatClientGUI_1() {
        // Create the main JFrame for the chat client
        frame = new JFrame("Rafan Nadiadwala");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(1, 1));
        frame.setBounds(200, 180, 400, 500);

        JPanel p = new JPanel();
        JLabel l = new JLabel("You are chatting with Aditya");
        p.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align above text to the left
        p.setBackground(new Color(0, 206, 209));
        p.add(l);
        frame.add(p, BorderLayout.NORTH); // Add 'p' to the top of the frame

        // Create the chat area where messages will be displayed
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBackground(new Color(173, 216, 230));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create the input field and send button for sending messages
        inputField = new JTextField();
        sendButton = new JButton("Send");
        inputField.setBackground(new Color(176, 224, 230));
        sendButton.setBackground(new Color(115, 194, 251));
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Create a panel to hold the input field and send button at the botttom of the
        // frame
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(7, 94, 84));
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.SOUTH);

        try {
            // Create a socket and connect to the server on localhost at port 2211
            clientSocket = new Socket("127.0.0.1", 2211); // Connect to the server

            // Initialize input and output streams for communication with the server
            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());

            // Start a separate thread to listen for incoming messages from the server
            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        // Read and display messages received from the server
                        String message = inputStream.readUTF();
                        chatArea.append("Aditya: " + message + "\n"); // Incoming messages align on the left
                        chatArea.setCaretPosition(chatArea.getDocument().getLength()); // Scroll to the latest message
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            receiveThread.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Make the frame visible to the user
        frame.setVisible(true);
    }

    private void sendMessage() {
        // Get the message from the input field and send it to the server
        String message = inputField.getText();
        if (!message.isEmpty()) {
            try {
                outputStream.writeUTF(message);
                outputStream.flush();
                chatArea.append("You:" + message + "\n"); // Your messages align on the right
                inputField.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Create and run the chat client application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginPage_1 loginpage = new LoginPage_1();
                loginpage.initialize();
            }
        });
    }
}
