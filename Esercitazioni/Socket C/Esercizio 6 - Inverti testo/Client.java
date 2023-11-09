import java.net.*;
import java.util.*;
import java.io.*;


public class Client {
    public static void main(String[] argc) throws IOException{
        Socket socket = null;
        BufferedReader in = null;
        BufferedReader stdIn = null;
        PrintWriter out = null;
        try{
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 7777);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(socket.getOutputStream(), true);

            String userInput;

            while(true){
                userInput = stdIn.readLine();
                out.println(userInput);

                System.out.println("Risposta: " + in.readLine());
            }

        }catch(IOException e){

        }


        System.out.println("Connessione finita");
        out.close();
        in.close();
        stdIn.close();
        socket.close();

        
    }
}
