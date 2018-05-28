#include<stdio.h>

int main(void){
    char text[] = "It is good to see you. Thank you for coming.";
    int i = 0;
    
    while (text[i] != '\0'){
        if(text[i] == ' ' || text[i] == '.'){
            i++;
            continue;
        }
        printf("[");
        while(text[i] != ' ' && text[i] != '.' && text[i] != '\0'){
            printf("%c", text[i]);
            i++;
        }
        printf("]\n");
        i++;
    }
    return 0;
}   

//int printf(const char * restrict, ...) __attribute__((__format__ (__printf__, 1, 2)));
/*
#include <stdio.h>
#include <string.h>

#define MAXLEN 64

int main(void){
	char s1[]="Network";
    char s2[MAXLEN],s3[MAXLEN];
	int i;

    for(i=0;s1[i]!='\0';i++){
        s2[i]=s1[i];
    }
	s2[i]=s1[i];
    
    strcpy(s3,s1);

    if(!(strcmp(s2,s3)))printf("s2=s3\n");
	
	return 0;
}

char *strcat(char *s1, const char *s2);
char *strcpy(char *s1, const char *s2);
int strcmp(const char *s1, const char *s2

typedef struct {
char mode;
char *ptr;
int rcount;
int wcount;
char *base;
unsigned bufsiz;
int fd;
char smallbuf[1];
} FILE;

#define EOF (-1)

int fclose(
    FILE *stream
);
FILE *fopen(
    const char * restrict filename,
    const char * restrict mode
);

*/
