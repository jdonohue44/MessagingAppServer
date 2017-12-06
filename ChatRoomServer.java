import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class to hold a server socket and chatroom information.
 */
public class ChatRoomServer {
    private ServerSocket serverSocket;
    private ConnectionListener connectionListener;
    private Thread listenerThread;
    Queue<Message> messageQueue;

    protected static int numClients = 0;

    public ChatRoomServer() {
        this("Default");
    }

    public ChatRoomServer(String chatRoomName) {
        try {
            serverSocket = new ServerSocket(6789);
        } catch (IOException exception) {
            System.out.println("Failed to create ServerSocket.");
            System.out.println(exception.getMessage());
        }
        messageQueue = new LinkedList<>();
        connectionListener = new ConnectionListener(serverSocket, messageQueue);
        listenerThread = new Thread(connectionListener);
    }

    public void run() throws IOException {
        listenerThread.start();
    }

    public static class Message {
        public String message;
        public int num;
        public Message(String message, int num) {
            this.message = message;
            this.num = num;
        }
    }
}
