/**
 * Scrivere un programma in Java che simuli un incontro di “tiro alla fune” tra due 2 thread “giocatori” tp[0], tp[1].

    E’ data una variabile globale intera posizione (con valore iniziale 0) condivisa da tutti i thread. 
    Sono date inoltre due variabili globali intere, vittorie_tp0 e vittorie_tp1.

    Ogni thread giocatore esegue un ciclo in cui:
    - genera un intero casuale recupero compreso tra 0 e 3
    - genera un intero casuale forza compreso tra 0 e 5
    - attende recupero secondi 

    - se tp[0]:
        - se posizione >= 10 riconosce la vittoria di tp[1] e: 
            - incrementa vittorie_tp1
            - setta posizione = 0
            - sveglia tp[1]
        -altrimenti: 
            - decrementa posizione di forza
            - se posizione <= -10 ha vinto, e si mette in attesa di tp[1]
            
    - se tp[1]:
        - se posizione <= -10 riconosce la vittoria di tp[0] e: 
            - incrementa vittorie_tp0
            - setta posizione = 0
            - sveglia tp[0]
        - altrimenti:
            - incrementa posizione di forza
            - se posizione >= 10 ha vinto, e si mette in attesa di tp[0]

    (Opzionale) quando uno dei giocatori ha raggiunto 10 vittorie interrompere il gioco, 
    entrambi i giocatori tp[0], tp[1] devono aver terminato la loro esecuzione, 
    e la funzione main() se ne deve accorgere scrivendo 
    sullo standard output il giocatore che ha totalizzato più vittorie.

 */
import java.util.Random;

public class fileMiriana {
    public static int vittorie_tp0 = 0;
    public static int vittorie_tp1 = 0;
    public static Position pos = new Position();

    public static void main(String[] args) {
        Thread[] tp = new Thread[2];
        

        tp[0] = new Thread(new Runnable() {
            @Override
            public void run() {
                while (vittorie_tp0 < 10 && vittorie_tp1 < 10) {
                    int recupero = new Random().nextInt(4);
                    int forza = new Random().nextInt(6);

                    try {
                        Thread.sleep(recupero * 1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    if (pos.readPos() >= 10) {
                        vittorie_tp1++;
                        pos.reset();
                        System.out.println("tp[1] ha vinto! Vittorie: " + vittorie_tp0 + " - " + vittorie_tp1);
                        try{
                            pos.gameNotify();
                        }catch(InterruptedException e){}
                    } else {
                        System.out.println("Sta tirato 0, posizione: " + pos.readPos() + ", forza: " + forza);
                        pos.decrease(forza);
                        System.out.println("Ha tirato 0, posizione: " + pos.readPos());
                        if (pos.readPos() <= -10) {
                            try{
                                pos.gameWait();
                            }catch(InterruptedException e){}
                        }
                    }
                }
            }
        });

        tp[1] = new Thread(new Runnable() {
            @Override
            public void run() {
                while (vittorie_tp0 < 10 && vittorie_tp1 < 10) {
                    int recupero = new Random().nextInt(4);
                    int forza = new Random().nextInt(6);

                    try {
                        Thread.sleep(recupero * 1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    if (pos.readPos() <= -10) {
                        vittorie_tp0++;
                        pos.reset();
                        System.out.println("tp[0] ha vinto! Vittorie: " + vittorie_tp0 + " - " + vittorie_tp1);
                        try{
                            pos.gameNotify();
                        }catch(InterruptedException e){}
                    } else {
                        System.out.println("Sta tirato 1, posizione: " + pos.readPos() + ", forza: " + forza);
                        pos.increase(forza);
                        System.out.println("Ha tirato 1, posizione: " + pos.readPos());
                        if (pos.readPos() >= 10) {
                            try{
                                pos.gameWait();
                            }catch(InterruptedException e){}
                        }
                    }
                }
            }
        });

        tp[0].start();
        tp[1].start();

        try{
            tp[0].join();
            tp[1].join();
        }catch(InterruptedException e){

        }

        System.out.println("La partita è finita");
    }
}