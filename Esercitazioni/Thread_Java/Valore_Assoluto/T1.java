/*
Scrivere in C/C++ o in Java un programma con due thread T1 e T2. I thread hanno una variabile condivisa x inizializzata
con un intero compreso tra 0 e 10 (*estremi inclusi*).
Il thread T1 ha una variabile privata m ed esegue un ciclo infinito, comportandosi in ciascun ciclo come segue:
- Attende 100ms
- Genera un valore casuale intero compreso tra 0 e 10 (estremi inclusi) e lo memorizza in m
- Se x è uguale a -1 termina l'esecuzione
- Altrimenti, confronta m con la variabile condivisa x:
    - Se m e x coincidono stampa un messaggio "RISPOSTA CORRETTA", setta x a -1 e termina l'esecuzione
    - Se la differenza in valore assoluto tra m ed x è maggiore di 5 stampa il messaggio "risposta MOLTO sbagliata" 
e si mette in attesa
    - Altrimenti, stampa il messaggio "risposta sbagliata"
Il Thread T2 invece:
- Attende 300ms
- Sveglia T1
- Se x è uguale a -1, termina l'esecuzione
- Altrimenti, ricomincia dal primo punto
*/

import java.util.*;

public class T1 extends Thread{
    Var x;

    T1(Var x){
        this.x = x;
    }

    int m;

    @Override
    public void run(){
        Random r = new Random();
        while(true){
            try{
                sleep(100); 
                m = r.nextInt(11);
                if(x.get() == -1){
                    System.out.println("Termino l'esecuzione");
                    break;
                }else if( x.get() == m){
                    System.out.println("RISPOSTA CORRETTA");
                    x.set(-1);
                    break;
                }
                else if(Math.abs(m-x.get()) > 5){
                    System.out.println("Risposta MOLTO sbagliata");
                    x.waitVar();
                }else{
                    System.out.println("Risposta sbagliata");
                }
            }catch(InterruptedException e){

            }
            
        }
    }
}
