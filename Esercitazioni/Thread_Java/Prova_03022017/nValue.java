public class nValue {
    int n = 0;

    nValue(){}

    public synchronized void increase(int x){
        n+=x;
    }

    public synchronized int getValue(){
        return n;
    }
}
