public class Value {
    int n;

    public synchronized void set(int n){
        this.n = n;
    }

    public synchronized int get(){
        return n;
    }
}
