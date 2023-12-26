package provagiocodelloca;

public class myMain{

    public static void main(String[] argv){
        Turno turno = new Turno();
        ThreadGiocatore t0 = new ThreadGiocatore(turno, 0); 
        ThreadGiocatore t1 = new ThreadGiocatore(turno, 1); 

        t0.start();
        t1.start();
    }
}