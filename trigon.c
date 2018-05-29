#include <math.h>
#include <stdio.h>
#define PI 3.14159265

double deg2rad(double degree);

int main(void){
	double degree;
	FILE *fp;
	
	if((fp = fopen("result.txt","w")) == NULL){
		return -1;
	}
	printf("degree>>");
	scanf("%lf",&degree);
	
	fprintf(fp,"sin = %lf\n",sin(deg2rad(degree)));
	fprintf(fp,"cos = %lf\n",cos(deg2rad(degree)));
	if(degree == 90.0 || degree == 270.0){
		fprintf(fp,"Not defined\n");
	}else{
		fprintf(fp,"tan = %lf\n",tan(deg2rad(degree)));
	}
	fclose(fp);
	return 0;
}

double deg2rad(double degree){
	double rad = degree * PI / 180;
	
	return	rad;	
}

/*
s1732087@mv8d:~> gcc trigon.c -lm
s1732087@mv8d:~> ./a.out 
degree>>30
s1732087@mv8d:~> cat result.txt
sin = 0.500000
cos = 0.866025
tan = 0.577350
s1732087@mv8d:~> ./a.out 
degree>>90
s1732087@mv8d:~> cat result.txt
sin = 1.000000
cos = 0.000000
Not defined
*/