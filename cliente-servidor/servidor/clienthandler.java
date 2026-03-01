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
                        socket.getOutputStream(), true);) {

            out.println(
                    "conectado ao servidor! Envie dois inteiros e uma operação (Adicao, Subtracao, Multiplicacao, Divisao) separados por espaço ou 'sair' para encerrar.");

            String mensagem;

            while ((mensagem = in.readLine()) != null) {
                System.out.println("cliente disse: " + mensagem);

                if (mensagem.equalsIgnoreCase("sair")) {
                    out.println("Encerrando conexão...");
                    break;
                }

                // tenta interpretar como calculadora
                String[] parts = mensagem.trim().split("\\s+");
                if (parts.length == 3) {
                    try {
                        int a = Integer.parseInt(parts[0]);
                        int b = Integer.parseInt(parts[1]);
                        String op = parts[2].toLowerCase();
                        long resultado;
                        switch (op) {
                            case "adicao":
                            case "adição":
                            case "add":
                                resultado = a + b;
                                break;
                            case "subtracao":
                            case "subtração":
                            case "sub":
                                resultado = a - b;
                                break;
                            case "multiplicacao":
                            case "multiplicação":
                            case "mul":
                                resultado = (long) a * b;
                                break;
                            case "divisao":
                            case "divisãp":
                            case "div":
                                if (b == 0) {
                                    out.println("Erro: divisão por zero");
                                    continue;
                                }
                                // resultado inteiro
                                resultado = a / b;
                                break;
                            default:
                                out.println("Operação desconhecida: " + parts[2]);
                                continue;
                        }
                        out.println("Resultado: " + resultado);
                    } catch (NumberFormatException ex) {
                        out.println("Erro: ambos operandos devem ser inteiros.");
                    }
                } else {
                    out.println("Formato inválido. Use: <int1> <int2> <operacao>");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}