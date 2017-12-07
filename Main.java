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
