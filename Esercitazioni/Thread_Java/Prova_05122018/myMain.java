public class myMain {
    public static variable m = new variable();

    public static void main(String[] argv){
        ThreadP TP = new ThreadP(m);
        ThreadD TD = new ThreadD(m);

        TP.start();
        TD.start();
    }
}
