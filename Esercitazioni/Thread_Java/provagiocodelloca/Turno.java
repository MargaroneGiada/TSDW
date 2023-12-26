package provagiocodelloca;

public class Turno {
    int turno = 0;

    public synchronized void set(int x){
        turno = x;
    }

    public synchronized int get(){
        return turno;
    }

    public synchronized void waitTurno() throws InterruptedException{
        wait();
    }
    public synchronized void notifyTurno() throws InterruptedException{
        notifyAll();
    }
}
