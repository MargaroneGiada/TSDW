public class Position {
    private int pos;

    Position(){
        pos = 0;
    }

    public synchronized void increase(int x){
        pos += x;
    }

    public synchronized void decrease(int x){
        pos -= x;
    }

    public synchronized void reset(){
        pos = 0;
    }

    public synchronized int readPos(){
        return pos;
    }

    public synchronized void gameWait() throws InterruptedException {
        wait();
    }

    public synchronized void gameNotify() throws InterruptedException {
        notifyAll();
    }

}
