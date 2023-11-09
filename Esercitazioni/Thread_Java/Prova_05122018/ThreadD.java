import java.util.Random;

public class ThreadD extends Thread{
    variable m;

    ThreadD(variable m){
        this.m = m;
    }

    @Override
    public void run(){
        while(true){
            try{
                sleep(300);
            }catch(InterruptedException e){}
            
            if(m.get() % 2 == 1){
                System.out.println("Sono il ThreadD e M vale " + m.get());
                Random r = new Random();
                int rnd = r.nextInt(10);
                m.set(rnd);
                System.out.println("Sono il ThreadD e ho cambiato il valore di M a: " + m.get());
                try{
                    m.notifyM();
                }catch(InterruptedException e){}
            } else{
               
                try{
                    m.waitM();
                }catch(InterruptedException e){}
            }
        }
    }
}
