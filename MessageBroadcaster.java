import java.io.IOException;
import java.util.Queue;

/**
 * Created by jareddonohue on 10/20/17.
 */
public class MessageBroadcaster extends Thread {
    Queue<ChatRoomServer.Message> messageQueue;
    Client client;
    private boolean running;

    MessageBroadcaster(Client client, Queue<ChatRoomServer.Message> messageQueue) {
        this.client = client;
        this.messageQueue = messageQueue;
        this.running = true;
    }

    public void run() {
        while (running) {
            synchronized (messageQueue) {
                try {
                    messageQueue.wait();
                    broadcast();
                } catch (InterruptedException | IOException e) {
                    System.out.println("Error writing message to client.");
                    System.out.println(e.getMessage());
                    this.running = false;
                }
            }
        }
    }

    private synchronized void broadcast() throws IOException {
        // check if the message has been sent to all the clients and remove it
        // from the queue if it has.
        ChatRoomServer.Message message = messageQueue.peek();
        System.out.println(client);
        if (message.num == 1) {
            client.getClientMessageWriter().writeBytes(messageQueue.poll().message + "\n");
        } else {
            client.getClientMessageWriter().writeBytes(messageQueue.peek().message + "\n");
            message.num --;
        }
    }
}
