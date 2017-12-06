import java.io.IOException;

/**
 * Runner.
 */
public class Main {
    public static void main(String[] args) {
        // start up a ChatRoomServer listening for connections on a certain port
        ChatRoomServer chatRoomServer = new ChatRoomServer();
        try {
            chatRoomServer.run();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

// Queue<String> messageQueue;
// ServerSocket server;
/* Make a generic server class that extends thread and takes the
   messageQueue as a parameter. Every time a new client connects,
   spawn a new thread to wait for messages from it's client. Each
   server will wait() on the messageQueue and when a server thread
   adds a new message to the queue it will notify() all other threads,
   triggering them to write the message to it's client.
*/
