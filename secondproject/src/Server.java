import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Server {
    private static final int PORT = 12345;

    //static File userInformation = new File("c://Users//ASUS//Desktop//he.txt");

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port " + PORT);

            ExecutorService executor = Executors.newFixedThreadPool(10);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                ClientHandeler clientHandler = new ClientHandeler(clientSocket);
                clientHandler.start();
                executor.execute(clientHandler);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}