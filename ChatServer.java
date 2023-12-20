import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clients = new ArrayList<>();

    public ChatServer() {
        try {
            // Create a server socket and listen for incoming client connections on port
            // 2211
            serverSocket = new ServerSocket(2211);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                // Accept a new client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create a new client handler for the connected client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);

                // Start a new thread to handle communication with this client
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create and start the server
        new ChatServer();
    }

    // Inner class to handle communication with each connected client
    class ClientHandler implements Runnable {
        private Socket clientSocket;
        private DataInputStream dis;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                dis = new DataInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Read messages sent by the client
                    String message = dis.readUTF();
                    System.out.println("Client: " + message);

                    // Broadcast the received message to all connected clients
                    for (ClientHandler client : clients) {
                        if (client != this) {
                            try {
                                // Send the message to other clients using their output streams
                                DataOutputStream dos = new DataOutputStream(client.clientSocket.getOutputStream());
                                dos.writeUTF(message);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
