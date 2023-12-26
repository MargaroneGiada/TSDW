import java.io.*;
import java.net.*;

public class client {
  public static void main(String args[]) throws IOException{
    InetAddress addr = InetAddress.getByName("127.0.0.1");

    Socket socket = null;
    BufferedReader in = null, stdIn = null;
    PrintWriter out = null;

    try{
      socket = new Socket(addr, 7777);
      System.out.println("Client socket: " + socket);

      //legge ciò che invia il server
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      
      //scrive al server
      out = new PrintWriter(socket.getOutputStream(), true); 

      //prende l'input da tastiera
      stdIn = new BufferedReader(new InputStreamReader(System.in));
      
      String userInput;

      while(true){
        userInput = stdIn.readLine();
        out.println(userInput); //manda al server (equivalente di write in c)

        System.out.println("Risposta del server: " + in.readLine());
      }
    }catch(UnknownHostException e){
      System.err.println("Indirizzo " + addr + " non riconosciuto");
      System.exit(-1);
    }catch(IOException e){
      System.err.println("Non è riuscito l'I/O dall'indirizzo: " + addr);
      System.exit(1);
    }
    System.out.println("Connessione finita");
    out.close();
    in.close();
    stdIn.close();
    socket.close();
  }
}
