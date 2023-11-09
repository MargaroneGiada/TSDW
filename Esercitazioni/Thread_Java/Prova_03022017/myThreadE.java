import java.util.Random;
import java.util.random.*;
public class myThreadE extends Thread{
    nValue n;
    int rnd;
    int count=0;
    
    myThreadE(nValue n){
        System.out.println("Constructor of "+getName());
        this.n = n;
    }

    @Override
    public void run(){
        for(;;){
            try {sleep(200);}
            catch(InterruptedException e){}

            Random r = new Random();
            rnd = (r.nextInt(50) * 2);
            n.increase(rnd);
            System.out.println("Value: " + n.getValue() + " from " + getName() );

            if(count >= 10 && (n.getValue()%2) == 0){
                break;
            }else if(count >= 1000){
                break;
            }

            count++;
        }
    }
}
