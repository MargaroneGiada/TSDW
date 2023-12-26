import java.util.*;
import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) throws IOException {
        Socket s = null;
        BufferedReader in = null, stdIn = null;
        PrintWriter out = null;

        try{
            s = new Socket(InetAddress.getByName("127.0.0.1"), 7777);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(s.getOutputStream(), true);

            String user;
            while(true){    
                user = stdIn.readLine();
                out.println(user);

                System.out.println(in.readLine());
            }

            
        }catch(IOException e){
            
        }  
    

        out.close();
        in.close();
        stdIn.close();
        s.close();

    }
}
