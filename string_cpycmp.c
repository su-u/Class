int printf(const char * restrict, ...) __attribute__((__format__ (__printf__, 1, 2)));
char *strcpy(char *s1, const char *s2);
int strcmp(const char *s1, const char *s2);

#define MAXLEN 64

int main(void){
	char s1[]="Network";
    char s2[MAXLEN],s3[MAXLEN];
	int i = 0;
	
    while(s1[i] != '\0'){
        s2[i] = s1[i];
		i++;
    }
	s2[i] = s1[i];
    
    strcpy(s3,s1);

    if(!(strcmp(s2,s3)))printf("s2 = s3\n");
	
	return 0;
}

/*
s1732087@mv8d:~> gcc string_cpycmp.c
s1732087@mv8d:~> ./a.out 
s2 = s3

*/