import java.util.Random;

public class Generatore extends Thread {
    Value n;

    Generatore(Value n){
        this.n = n;
        n.set(0);
    }
    
    public void run(){
        while(true){
            Random r = new Random();
            int rnd = r.nextInt(100) + 1;
            n.set(rnd);
            System.out.println("Il numero generato è: " + n.get());

            try{
                sleep(500);
            }
            catch(InterruptedException e){}
        }
        
    }
}
