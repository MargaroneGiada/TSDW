public class variable {
    private int m = 0;

    public synchronized int get(){
        return m;
    }

    public synchronized void set(int m){
        this.m = m;
    }

    public synchronized void notifyM() throws InterruptedException{
        notifyAll();
    }

    public synchronized void waitM() throws InterruptedException{
        wait();
    }

}
