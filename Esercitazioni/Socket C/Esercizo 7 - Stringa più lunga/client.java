import java.util.*;
import java.net.*;
import java.io.*;

public class client{
    public static void main(String[] argv){
        Socket s = null;
        BufferedReader in = null, stdIn = null;
        PrintWriter out = null;

        try{
            s = new Socket(InetAddress.getByName("127.0.0.1"), 3333);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);

            String userInput;

            userInput = stdIn.readLine();
            out.println(userInput);

            System.out.println("Risposta: " + in.readLine());
            
            in.close();
            out.close();
            s.close();
            stdIn.close();
        
        }catch(IOException e){}
    }
}