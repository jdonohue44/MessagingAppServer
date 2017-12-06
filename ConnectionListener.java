import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Thread to always listen for new client connections.
 */
public class ConnectionListener extends Thread {
    private ServerSocket serverSocket;
    Queue<ChatRoomServer.Message> messageQueue;

    ConnectionListener(ServerSocket serverSocket, Queue<ChatRoomServer.Message> messageQueue) {
        this.serverSocket = serverSocket;
        this.messageQueue = messageQueue;
    }

    public void run() {
        // listen for connections and add the socket to the list.
        while (true) {
            try {
                listenForConnections();
            } catch(Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private synchronized void listenForConnections() throws IOException {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Connection established with " + clientSocket.getRemoteSocketAddress());
        ChatRoomServer.numClients ++;

        new Thread(new MessageReader(new Client(clientSocket), messageQueue)).start();
        new Thread(new MessageBroadcaster(new Client(clientSocket), messageQueue)).start();
    }
}
