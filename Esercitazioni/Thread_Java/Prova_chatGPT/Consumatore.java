import java.util.*;

public class Consumatore extends Thread {
    Value n;

    Consumatore(Value n){
        this.n = n;
    }

    public void run(){
        while(true){ 
            if(n.get() % 2 == 0){
                System.out.println("Quadrato: " + n.get()*n.get());
            }else{
                System.out.println("Doppio: " + n.get()*2);
            }
            try{
                sleep(300);
            }catch(InterruptedException e){}
        }
    }

    
}
