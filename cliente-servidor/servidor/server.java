import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    private static final int port = 5000;

    public static void main(String[] args) {
        System.out.println("servidor iniciado na porta " + port);

        try (ServerSocket serversocket = new ServerSocket(port)) {

            while (true) {
                Socket socket = serversocket.accept();
                System.out.println("novo cliente conectado!");
                new clienthandler(socket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}