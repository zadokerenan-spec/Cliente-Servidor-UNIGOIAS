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

            while (true) {
                System.out.println("Informe dois números inteiros e a operação desejada (Adicao, Subtracao, Multiplicacao, Divisao)");
                System.out.print("Exemplo: 5 3 Multiplicacao  (ou digite 'sair' para encerrar): ");
                String mensagem = scanner.nextLine();

                out.println(mensagem);

                String resposta = in.readLine();
                if (resposta == null) {
                    System.out.println("Conexão perdida com o servidor.");
                    break;
                }
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