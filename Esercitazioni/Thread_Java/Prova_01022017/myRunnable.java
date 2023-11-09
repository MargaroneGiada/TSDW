import java.util.Random;

public class myRunnable implements Runnable {
    Sample sample;

    myRunnable(Sample s){
        sample = s;
    }

    @Override
    public void run(){
        while(true){
            Random r = new Random();
            int guess = r.nextInt(81) + 10;
            if( sample.check(guess) == 1){
                break;
            }
        }
    }
}
