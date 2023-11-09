/*Il client manda una stringa al server che gliela ritorna invertita*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<arpa/inet.h>


#define MAXBUFF 100
#define PORT 7777

int main(int argc, char **argv){
    char buffer[MAXBUFF];
    struct sockaddr_in server, client;
    socklen_t len = sizeof(server);
    int check = 1;
    int s, connSock;

    if((s = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("socket()\n");
        return -1;
    }

    memset(&server, 0, len);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(PORT);

    if((setsockopt(s,SOL_SOCKET, SO_REUSEADDR, &check, sizeof(check))) < 0){
        perror("setsockopt()\n");
        return -1;
    }

    if((bind(s, (struct sockadd *)&server, len)) < 0) {
        perror("bind()\n");
        return -1;
    }

    if((listen(s, 1)) < 0){
        perror("listen()\n");
        return -1;
    }

    if((connSock = accept(s, (struct sockaddr *)&client, &len)) < 0){
        perror("accept()\n");
        return -1;
    }

    

    int n;
    while(1){
        if((n = read(connSock, &buffer, sizeof(buffer))) <= 0){
            return -1;
        }
        printf("N: %d\n", n);

        //buffer[n] = '\0';

        char tmp[n+1];
        for(int i = n-2; i >= 0; i--){
            tmp[n - i -2] = buffer[i];
        }
        tmp[n-1]='\n';
        tmp[n]='\0';

        printf("Stringa convertita %s \n", tmp);
        write(connSock, &tmp, sizeof(tmp));

    }
}




