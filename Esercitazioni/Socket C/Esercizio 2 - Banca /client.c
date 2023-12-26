#include<stdio.h>
#include<string.h>
#include<arpa/inet.h>

#define MAXSIZE 100
#define PORT 7777
#define ADDRESS "127.0.0.1"


int main(int argc, char** argv){
    char buffer[MAXSIZE];
    struct sockaddr_in server;
    socklen_t len = sizeof(server);
    int sock;

    if((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("socket()\n");
        return -1;
    }

    //provo a non mettere la setcoskopt

    memset(&server, 0, len);
    server.sin_family = AF_INET;
    //server.sin_addr.s_addr = ADDRESS;
    //inet_pton(AF_INET, ADDRESS, &server.sin_addr);
    server.sin_addr.s_addr = inet_addr(ADDRESS);
    server.sin_port = htons(PORT);

    if((connect(sock, (struct sockaddr *)&server, len)) < 0){
        perror("connect()\n");
        return -1;
    }

    int n;
    while(1){
        scanf("%s", buffer);
        write(sock, &buffer, strlen(buffer));


        n = read(sock, &buffer, MAXSIZE);
        buffer[n] = '\0';

        printf("%s\n", buffer);
    }


}