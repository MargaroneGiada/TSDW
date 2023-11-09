public class Sample {
    int s = 50;

    public synchronized int read(){
        return s;
    }

    public synchronized void write(int x){
        s = x;
    }

    public synchronized int check(int guess){
        if(this.read() == guess){
            System.out.println("Sono " + Thread.currentThread().getName() + " e ho indovinato il valore " + this.read() + " di sample.");
            return 1;
        }else{
            int tmp = this.read();
            this.write(guess);
            System.out.println("Sono " + Thread.currentThread().getName() + ". Sample valeva " + tmp + ", ora vale " + guess);
            return 0;
        }
    }
}
