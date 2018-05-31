int printf(const char * restrict, ...) __attribute__((__format__ (__printf__, 1, 2)));
int scanf(const char *format, ...);
int rand(void);
void srand(unsigned int seed);
int fortune(unsigned int seed);

int main(void){
	unsigned int seed;
	
	printf("seed>>");
	scanf("%u",&seed);
		
	switch(fortune(seed)){
		case 0:
			printf("Worst luck");
			break;
		case 1:
			printf("Bad luck");
			break;
		case 2:
			printf("Good luck");
			break;
		case 3:
			printf("Better luck");
			break;
		case 4:
			printf("Excellent luck");
			break;
		default:
			return -1;
	}
	printf("\n");
	
	return 0;
}

int fortune(unsigned int seed){
	srand(seed);
	int ran = rand() % 100;
	
	if(ran <= 5){
		return 4;
	}else if(ran <= 25){
		return 3;
	}else if(ran <= 75){
		return 2;
	}else if(ran <= 95){
		return 1;
	}else{
		return 0;
	}
}

/*
@mv8d:~> gcc omikuji.c
@mv8d:~> ./a.out 
seed>>222
Bad luck
@mv8d:~> ./a.out 
seed>>111
Good luck
@mv8d:~> ./a.out 
seed>>14252353
Good luck
@mv8d:~> ./a.out 
seed>>2244
Good luck
@mv8d:~> ./a.out 
seed>>44567
Good luck
 */