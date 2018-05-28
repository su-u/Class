#include<stdio.h>

int main(void){
    char text[] = "It is good to see you. Thank you for coming.";
    int i = 0;
    
    while (text[i] != '\0')
    {
        if(text[i] == ' ' || text[i] == '.'){
            i++;
            continue;
        }
        printf("[");
        while(text[i] != ' ' && text[i] != '.'){
            printf("%c", text[i]);
            i++;
        }
        printf("]\n");
        i++;
    }
    return 0;
}   