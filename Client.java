import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Class to describe a client.
 */
public class Client {
    private Socket clientSocket;
    private BufferedReader clientMessageReader;
    private DataOutputStream clientMessageWriter;

    Client(Socket socket) {
        clientSocket = socket;
        try {
            clientMessageReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientMessageWriter = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public BufferedReader getClientMessageReader() {
        return clientMessageReader;
    }

    public DataOutputStream getClientMessageWriter() {
        return clientMessageWriter;
    }
}
