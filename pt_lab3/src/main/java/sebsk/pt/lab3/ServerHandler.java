package sebsk.pt.lab3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerHandler implements Runnable {
    Socket socket;
    int clientNumber;

    public ServerHandler(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }
    private void handleConnection() {
        System.out.println("Client connected from: " + socket.getInetAddress().getHostAddress());

        // Send "ready" to client
        try {

            System.out.println("[" + clientNumber + "] Sending \"ready\"");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(new String("ready"));
            objectOutputStream.flush();

            // Receive number of messages
            Integer receivedNumberOfMessages;
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                receivedNumberOfMessages = (Integer) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new IOException("[" + clientNumber + "] Couldn't get number of messages from client");
            }
            System.out.println("[" + clientNumber + "] Received number of messages: " + receivedNumberOfMessages);

            System.out.println("[" + clientNumber + "] Sending \"ready for messages\"");
            objectOutputStream.writeObject(new String("ready for messages"));
            objectOutputStream.flush();

            getMessages(receivedNumberOfMessages, socket);
            objectOutputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void getMessages(int n, Socket socket) throws IOException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            for (int i=0; i<n; i++) {
                Message receivedMessage = (Message) objectInputStream.readObject();
                System.out.println("[" + clientNumber + "] Message number " + (i+1) + ": " + receivedMessage.getNumber() + ", " + receivedMessage.getContent());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        handleConnection();
    }

}
