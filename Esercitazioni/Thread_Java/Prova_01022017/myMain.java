/*Scrivere un programma in C in cui tre thread aggiornano la stessa variabile globale condivisa sample, 
inizialmente posta a 50.

Ogni thread ciclicamente genera un numero casuale compreso tra 10 e 90 e prova a sovrascrivere 
il valore corrente di sample. 
Il thread mostra in output un messaggio che segue il modello “Sono il thread X: sample valeva Y, 
adesso vale Z”, con opportuni valori al posto di X, Y e Z.
Se il valore casuale generato è uguale al valore attuale di sample il thread termina il suo ciclo,
comunicando in output la propria terminazione.

Quando tutti i thread sono terminati, il programma principale termina e mostra in output il valore finale di sample. */

public class myMain {
    private static Sample sample = new Sample();

    public static void main(String[] argc){
        Thread tp1 = new Thread(new myRunnable(sample));        
        Thread tp2 = new Thread(new myRunnable(sample));
        Thread tp3 = new Thread(new myRunnable(sample));

        tp1.start();        
        tp2.start();
        tp3.start();

        try{
            tp1.join();
            tp2.join();
            tp3.join();
        }catch(InterruptedException e){}

        System.out.println("I threads hanno terminato, l'ultimo valore di sample era " + sample.read());

    }
}
