/***
 * Realizzare un server (in C o in Java) che si metta in ascolto sul port 3333, sul quale accetta richieste di connessione da un client.
Sulla connessione stabilita, il server riceve una stringa, terminata dal carattere In° (si supponga che basti una sola operazione 
di lettura in ricezione da parte del server, per ricevere ciascuna stringa inviata dal client).
Il server quindi scrive la stringa ricevuta sullo standard output e, chiusa la connessione, si rimette in attesa di eventuali 
richieste di connessione.

Testare il server server_A usando telnet.
B. Conservando la versione (A) del server, realizzare un'ulteriore versione (B), 
tale che ciascuna stringa ricevuta dal server, oltre ad essere scritta dal server sulla propria standard output, 
sia inviata nuovamente al client come risposta (senza alcuna modifica).

Testare il server server_B usando telnet.
C. Conservando la versione (B) del server, realizzarne un'ulteriore versione tale che il server implementi un metodo/funzione
sommacifre (s)
che accetta per argomento una stringa se restituisce un intero; per il momento, 
ai fini di questo quesito (C), si supponga che, per qualunque stringa argomento s, venga restituito l'intero 0. 
In questa versione, il server, ricevuta una stringa s la scrive sulla standard output, calcola × - sommacifre (s) 
e invia al client un messaggio in forma di stringa del tipo n, x
dove n è un id progressivo della richiesta fatta dal client, mentre x è l'output del metodo sommaci fre (s). 
L'id, che fa parte dello stato del server, parte da 0 e viene incrementato ad ogni nuova richiesta da parte di un client 
fino a quando il server non viene riavviato.
Testare il server server_C usando telnet*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <netdb.h>
#include <ctype.h>

#define PORT 3333
#define MAXSIZE 2048

int main(int argc, char** argv){
    char buffer[MAXSIZE];
    struct sockaddr_in server, client;
    socklen_t clientLen = sizeof(client);
    int check = 1;

    int s;
    if((s = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("socket()\n");
        return -1;
    }

    if((setsockopt(s, SOL_SOCKET, SO_REUSEADDR, &check, sizeof(check))) < 0){
        perror("setsockopt()\n");
        return -1;
    }

    memset(&server, 0, sizeof(server));
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(PORT);

    if((bind(s, (struct sockaddr *)&server, sizeof(server))) < 0){
        perror("bind()\n");
        return -1;
    }

    if(listen(s, 1) != 0){
        perror("listen()\n");
        return -1;
    }

    printf("Server ready \n");
    
    int connectionSock;
    if((connectionSock = accept(s, (struct sockaddr *)&client, &clientLen)) < 0){
        perror("accept()\n");
        return -1;
    }  

    int id = 0;

    while(1){      
            // PUNTO A: stampare a video ciò che si riceve dal client
        int retcode;
        if ( (retcode = read(connectionSock, &buffer, MAXSIZE)) < 0 ) {
            perror("read()\n");
            exit(-1);
        }

        buffer[retcode] = '\0';

        printf("%s", buffer);

            // PUNTO B: fare da eco al client
        
        write(connectionSock, &buffer, strlen(buffer));

            // PUNTO C: ritornare al client una stringa del tipo n, x. n è un id progressivo che parte da 0 e x è il risultato della funzione
            // sommacifre(s), che calcola la somma delle cifre

        id++;
        sprintf(buffer, "%d, %d\n\0", id, sommacifre(buffer));

        write(connectionSock, &buffer, strlen(buffer));

    }


}

int sommacifre(char* s){
    int len = strlen(s);
    int x = 0;
    for(int i = 0; i<len; i++){
        if(isdigit(s[i])){
            x += s[i] - '0';
        }
    }
    return x;
}