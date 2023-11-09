/*Prova in Itinere 18/12/2019 -- https://tinyurl.com/TSDW2019Socket

1. Socket (C o Java)
A) Realizzare un server (in C o in Java) chiamato Server A che si metta in ascolto sul port
7777 per ricevere una stringa str composta da una sequenza di lunghezza
arbitraria di caratteri numerici da (0 a 9) e terminata dal carattere \n. Il server dovrà
quindi stampare il messaggio ricevuto sullo standard output. Testare il server usando
telnet.
B) Estendere le funzionalità dal server definito al punto precedente realizzando un secondo
server chiamato Server B che oltre a stampare il messaggio ricevuto sullo standard
output, lo invia come risposta al client (senza modificarlo). Testare il server usando
telnet.
C) Estendere le funzionalità dal server definito al punto precedente realizzando un terzo
server chiamato Server C che oltre a stampare il messaggio ricevuto sullo standard
output, lo passa ad un metodo “int MUL(String str)” che per ora restituisce
sempre 0 per qualunque parametro di input str. Il risultato del metodo deve quindi
essere inviato come messaggio di risposta al client. Testare il server usando telnet.
D) Estendere le funzionalità dal server definito al punto precedente realizzando un quarto
server chiamato Server D modificando il comportamento del metodo “int
MUL(String str)” che dovrà restituire il prodotto delle singole cifre numeriche
presenti nella stringa in input, ad esempio per la stringa “1234” restituirà l’intero 24. Il
risultato del metodo deve quindi essere inviato come messaggio di risposta al client.
Testare il server usando telnet.
E) (Opzionale) Realizzare un semplice client per testare i server creati ai punti precedenti.*/

#include<stdio.h>
#include<arpa/inet.h>

#define MAXSIZE 1000

int main(int argc, char **argv){
    char x;
}
