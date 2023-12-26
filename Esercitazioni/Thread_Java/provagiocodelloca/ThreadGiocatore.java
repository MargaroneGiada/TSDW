package provagiocodelloca;

import java.util.Random;

public class ThreadGiocatore extends Thread{
    Turno turno = new Turno();
    int k;
    int posizione = 0;


    ThreadGiocatore(Turno t, int k){
        this.turno = t;
        this.k = k;
    }

    public void run(){
        while(true){
            try{ 
                if(turno.get() == -1){
                    break;
                }else if(turno.get() != k){
                    turno.waitTurno();
                }else{
                    Random r = new Random();
                    int d = r.nextInt(10) + 1;
                    posizione += d;
                    System.out.println("La posizione del giocatore " + k + " è " + posizione);
                    if(posizione > 100){
                        System.out.println("100 superato! Il giocatore " + k + " ha vinto");
                        turno.set(-1);
                        turno.notifyTurno();
                    }else{
                        turno.set(1-k);
                        turno.notifyTurno();
                        sleep(500);
                    }
                }
            }catch(InterruptedException e){

            }
        }
    }
}
