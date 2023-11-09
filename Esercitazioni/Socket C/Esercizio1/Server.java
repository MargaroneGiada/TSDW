//Fatto da ChatGPT
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        final int PORT = 3333;
        final int MAXSIZE = 2048;
        int id = 0;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server ready");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String buffer;
                while ((buffer = in.readLine()) != null) {
                    // PUNTO A: stampare a video ciò che si riceve dal client
                    System.out.println(buffer);

                    // PUNTO B: fare da eco al client
                    out.println(buffer);

                    // PUNTO C: ritornare al client una stringa del tipo n, x
                    int x = sommacifre(buffer);
                    out.println(id + ", " + x);
                    id++;
                }

                clientSocket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int sommacifre(String s) {
        int len = s.length();
        int x = 0;
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                x += Character.getNumericValue(s.charAt(i));
            }
        }
        return x;
    }
}
