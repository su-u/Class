int printf(const char * restrict, ...) __attribute__((__format__ (__printf__, 1, 2)));

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
/*
@mv8d:~> gcc split_text.c
@mv8d:~> ./a.out 
[It]
[is]
[good]
[to]
[see]
[you]
[Thank]
[you]
[for]
[coming]
*/