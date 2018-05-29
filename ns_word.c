#include<stdio.h>

int main(void){
    FILE *fp1,*fp2;
    int i = 0;
	int count = 1;
	int c, isword = 0, isout = 0;
	
	
	if((fp1 = fopen("NS.txt","r")) == NULL){
		return -1;
	}
	if((fp2 = fopen("word.txt","w")) == NULL){
		return -1;
	}
    
    
	while((c = fgetc(fp1)) != EOF){
		if((!(c == ' ' || c == '.' || c == ',' || c == '\n')) && isword == 0){
			fprintf(fp2,"[");
			isword = 1;
			fprintf(fp2,"%c",c);
			isout = 1;
		}else if(!(c == ' ' || c == '.' || c == ',' || c == '\n')){
			fprintf(fp2,"%c",c);
			isout = 1;
		}else if(isout && isword){
			fprintf(fp2,"]");
			isword = 0;
			if(count == 5){
				fprintf(fp2,"\n");
				count = 1;
			}else{
				count++;
			}
		}
	}
    fclose(fp1);
	fclose(fp2);
	
	int hh = 1;
	printf("%i\n",hh);
	
	
    return 0;
}

/*
s1732087@mv8d:~> gcc ns_word.c
s1732087@mv8d:~> ./a.out
*/