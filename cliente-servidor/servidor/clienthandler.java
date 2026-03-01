import java.io.*;
import java.net.Socket;

public class clienthandler extends Thread {

    private Socket socket;

    public clienthandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
        ) {

            out.println("conectado ao servidor!");

            String mensagem;

            while ((mensagem = in.readLine()) != null) {
                System.out.println("cliente disse: " + mensagem);
                out.println("servidor respondeu: " + mensagem);

                if (mensagem.equalsIgnoreCase("sair")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}