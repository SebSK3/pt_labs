package sebsk.pt.lab3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;


public class Server {
    static final int PORT = 12345;
    public static void main(String[] args) {
        List<Thread> connections = new LinkedList<>();
        int clientNumber = 0;
        try {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Listening on port:" + PORT);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    Thread clientHandler = new Thread(new ServerHandler(clientSocket, clientNumber));
                    clientHandler.start();
                    connections.add(clientHandler);
                    clientNumber++;
                }
            }


       } catch (IOException e) {

           e.printStackTrace();
       }
    }

}
