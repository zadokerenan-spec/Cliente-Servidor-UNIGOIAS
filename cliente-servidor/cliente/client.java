import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class client {

    private static final String server_address = "localhost";
    private static final int server_port = 5000;

    public static void main(String[] args) {

        try (
            Socket socket = new Socket(server_address, server_port);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
        ) {

            System.out.println(in.readLine());

            String mensagem;

            while (true) {
                System.out.print("digite uma mensagem: ");
                mensagem = scanner.nextLine();

                out.println(mensagem);

                String resposta = in.readLine();
                System.out.println(resposta);

                if (mensagem.equalsIgnoreCase("sair")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}