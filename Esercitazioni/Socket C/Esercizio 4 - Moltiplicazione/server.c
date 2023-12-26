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
#include<string.h>
#include<ctype.h>

#define MAXSIZE 1000

int MULT(char* buffer){
    int x = 1;
    for(int i = 0; i < strlen(buffer); i++){
        if(isdigit(buffer[i])){
            x *= buffer[i] - '0';
        }
    }
    return x;
}

int main(int argc, char **argv){
    char buffer[MAXSIZE];
    char resp[MAXSIZE+12];
    struct sockaddr_in server, client;
    socklen_t len = sizeof(server);
    int check=1;
    int s, connSock;
    int n;

    if((s = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("socket\n");
        return -1;
    }

    memset(&server, 0, len);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(7777);

    if((setsockopt(s, SOL_SOCKET, SO_REUSEADDR, &check, sizeof(check))) < 0){
        perror("setsockopt\n");
        return -1;
    }

    if((bind(s, (struct sockaddr *)&server, len)) < 0){
        perror("bind\n");
        return -1;
    }

    if((listen(s, 1)) < 0){
        perror("listen\n");
        return -1;
    }

    if((connSock = accept(s, (struct sockaddr*)&client, &len)) < 0){
        perror("accept\n");
        return -1;
    }

    printf("Server ready\n");

    while(1){
        if((n = read(connSock, &buffer, sizeof(buffer))) < 0){
            return -1;
        }

        buffer[n-1] = '\0';

        sprintf(resp, "%s, MULT: %d\n", buffer, MULT(buffer));

        write(connSock, &resp, strlen(resp));
    }
}


