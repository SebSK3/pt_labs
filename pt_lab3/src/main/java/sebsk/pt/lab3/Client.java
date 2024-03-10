package sebsk.pt.lab3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    final static String SERVER_IP = "127.0.0.1";
    final static int SERVER_PORT = 12345;
    final static int NUMBER_OF_MESSAGES = 5;
    public static void main(String[] args) {

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected to server at " + SERVER_IP + ":" + SERVER_PORT);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            // Receive "ready"
            String receivedReady = receiveString(objectInputStream);
            if (!receivedReady.equals("ready")) {
                objectInputStream.close();
                socket.close();
                throw new IOException("Error when getting \"ready\"");
            }
            System.out.println("Got message from server: " + receivedReady);

            // Send number of messages
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                Integer message = NUMBER_OF_MESSAGES;
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
                System.out.println("Sent to server: " + NUMBER_OF_MESSAGES);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Receive "ready for messages"
            String receivedReadyForMessages = receiveString(objectInputStream);
            if (!receivedReadyForMessages.equals("ready for messages")) {
                objectInputStream.close();
                socket.close();
                throw new IOException("Error when getting \"ready for messages\"");
            }
            System.out.println("Got message from server: " + receivedReadyForMessages);

            try {
                sendMessages(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            objectInputStream.close();
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessages(Socket socket) throws IOException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        for (int i=0; i<NUMBER_OF_MESSAGES; i++) {
            System.out.print("Enter number: ");
            int number = scanner.nextInt();
            scanner.nextLine(); // get newline char
            System.out.print("Enter a message: ");
            String message = scanner.nextLine();
            objectOutputStream.writeObject(new Message(number, message));
        }
        scanner.close();
    }

    private static String receiveString(ObjectInputStream inputStream) throws IOException {
        String receivedMessage = "";
        try {
            receivedMessage = (String) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return receivedMessage;
    }

}
