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
                Scanner scanner = new Scanner(System.in);) {

            System.out.println(in.readLine());

            while (true) {
                System.out.println(
                        "Informe dois números (float) e a operação desejada (Adicao, Subtracao, Multiplicacao, Divisao)");
                System.out.print("Exemplo: 5.25 3.00 Multiplicacao  (ou digite 'sair' para encerrar): ");
                String linha = scanner.nextLine().trim();

                if (linha.equalsIgnoreCase("sair")) {
                    out.println("sair");
                    break;
                }

                // tenta formatar os dois primeiros valores para 2 casas
                String mensagem = linha;
                String[] tokens = linha.split("\\s+");
                if (tokens.length >= 3) {
                    try {
                        // normaliza vírgula para ponto
                        String n1 = tokens[0].replace(',', '.');
                        String n2 = tokens[1].replace(',', '.');
                        float x = Float.parseFloat(n1);
                        float y = Float.parseFloat(n2);
                        String op = tokens[2];
                        mensagem = String.format("%.2f %.2f %s", x, y, op);
                    } catch (NumberFormatException ex) {
                        // mantém linha original se falhar parsing
                    }
                }

                out.println(mensagem);

                String resposta = in.readLine();
                if (resposta == null) {
                    System.out.println("Conexão perdida com o servidor.");
                    break;
                }
                // formatar resposta numérica se possível
                if (resposta.startsWith("Resultado:")) {
                    String[] parts = resposta.split(":", 2);
                    if (parts.length == 2) {
                        try {
                            float val = Float.parseFloat(parts[1].trim());
                            System.out.println("Resultado: " + String.format("%.2f", val));
                        } catch (NumberFormatException ex) {
                            System.out.println(resposta);
                        }
                    } else {
                        System.out.println(resposta);
                    }
                } else {
                    System.out.println(resposta);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}