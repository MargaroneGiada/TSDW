/*Esercizio 1: Socket (C o Java) -- Tempo a disposizione: 30 minuti.
Realizzare un server (in C o in Java) che si metta in ascolto sul port 3333, 
sul quale accetta richieste di connessione da un client. 
Sulla connessione stabilita, il server attende una stringa, terminata dal carattere '\n'.
N.B. Questo riquadro grigio vi riguarda solo se sviluppate il server in C: 
potete supporre che al server basti una sola operazione di lettura per ricevere la stringa inviata dal client;
potete, inoltre, se volete, omettere di verificare che la stringa ricevuta termini con '\n'.
Il server, quindi, stampa la stringa ricevuta sullo standard output e, chiusa la connessione in corso, 
si rimette in attesa di eventuali nuove richieste di connessione. 
Chiamate il file sorgente server_A.c o ServerA.java, secondo i casi. Testate questo server_A usando telnet. 
Conservando la versione (A) del server, realizzatene un’ulteriore versione (B), tale che per ciascuna stringa ricevuta,
 il server, oltre a stamparla sulla propria standard output, la invii nuovamente (senza alcuna modifica) al client come 
 risposta. Quindi, server_B chiude la connessione e si rimette in attesa di eventuali nuove richieste di connessione.
Testate il server server_B usando telnet.
Conservando la versione (B) del server, realizzatene un’ulteriore versione, server_C, identica a B, salvo che per la 
risposta inviata al client, che sarà la stringa più lunga tra quelle ricevute fino a quel momento (NB: il server C non 
invia al cliente anche la stringa appena ricevuta).
Testate il server server_C usando telnet.
(Opzionale, dà bonus) Realizzate un semplice client per testare i server creati ai punti precedenti.
Un ulteriore bonus è previsto se il client viene sviluppato nel linguaggio, tra C e Java, 
non adoperato in precedenza per i quesiti A-C.
*/

#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <stdio.h>

#define MAXBUFF 100
#define PORT 3333

int main(int argc, char** argv){
    struct sockaddr_in server, client;
    socklen_t len = sizeof(server);
    char buffer[MAXBUFF];
    char response[MAXBUFF];
    char longest[MAXBUFF];
    longest[0] = 0; 
    int s, check = 1, connSock;
    int n;

    if((s = socket(AF_INET, SOCK_STREAM, 0)) <0 ){
        return -1;
    }

    memset(&server, 0, len);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(PORT);

    if((setsockopt(s, 1, 2, &check, sizeof(check))) < 0){ //SOL_SOCKET = 1, SO_REUSEADDR = 2
        return -1;
    }

    if((bind(s, (struct sockaddr *)&server, len)) < 0){
        return -1;
    }

    if((listen(s, 1)) < 0){
        return -1;
    }

    printf("Server ready\n");

    while(1){
        if((connSock = accept(s, (struct sockaddr *)&client, &len)) < 0){
            return -1;
        }

        if((n = read(connSock, &buffer, MAXBUFF)) < 0){
            return -1;
        }

        buffer[n] = '\0';
        printf("Message: \"%s\" recieved \n", buffer);

        if(n > strlen(longest)){
            strncpy(longest, buffer, n);
        }
        strcpy(response, longest);
        response[strlen(longest)] = '\n';
        response[strlen(longest) + 1] = '\0';

        write(connSock, &response, strlen(response));

        close(connSock);
    }
}