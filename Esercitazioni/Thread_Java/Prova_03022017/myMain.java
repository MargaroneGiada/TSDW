/*
Una variabile intera n, inizializzata a 0, è condivisa tra 2 thread tO, tE.
Il thread tE, ciclicamente:
-attende 200 ms (N.B.: la chiamata usleep(t) attende per t microsecondi)
-genera un int casuale pari e lo somma alla variabile condivisa n
-se ha eseguito almeno 10 cicli e n è pari termina
-altrimenti ricomincia dal passo (1), a meno che abbia già compiuto 1000 iterazioni, nel qual caso termina.
Il thread tO, ciclicamente:
-attende 200 ms (N.B.: la chiamata usleep(n) attende per n microsecondi)
-genera un int casuale dispari e lo somma alla variabile condivisa n
-se ha eseguito almeno 10 cicli e n è dispari termina
-altrimenti ricomincia dal passo (1), a meno che abbia già compiuto 1000 iterazioni, nel qual caso termina.

(Non ricorrere a un array di 2 thread per l’implementazione!)

Il programma termina quando tutti i thread hanno terminato la propria esecuzione. I thread scriveranno di essere terminati.
Possono anche visualizzare, a ogni ciclo, il valore trovato in n.
Nel codice, proteggere opportunamente la variabile n dagli accessi concorrenti. 
 */

public class myMain {
    public static void main(String[] args) throws InterruptedException{
        nValue n = new nValue();
        myThreadE tE = new myThreadE(n);
        myThreadO tO = new myThreadO(n);

        tE.run();
        tO.run();

        try{
            tE.join();
            tO.join();
        }catch(InterruptedException e){ }

        System.out.println("End of program");
    }
}
