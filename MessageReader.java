import java.io.IOException;
import java.util.Queue;

/**
 * Created by jareddonohue on 10/20/17.
 */
public class MessageReader extends Thread {
    Client client;
    Queue<ChatRoomServer.Message> messageQueue;

    MessageReader(Client client, Queue<ChatRoomServer.Message> messageQueue) {
        this.client = client;
        this.messageQueue = messageQueue;
    }

    public void run() {
        while (true) {
            try {
                if (client.getClientMessageReader().ready()) {
                    synchronized (messageQueue) {
                        System.out.println(client);
                        messageQueue.add(new ChatRoomServer.Message(client.getClientMessageReader().readLine(),
                                ChatRoomServer.numClients));
                        messageQueue.notifyAll();
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading message from client.");
                System.out.println(e.getMessage());
            }
        }
    }

    //client.getClientMessageWriter().writeBytes(message);
}
