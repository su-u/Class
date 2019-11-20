#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

typedef	unsigned char	uchar;

// YUV画像データ構造体宣言 --------------------------------
typedef struct IMAGE_DATA {
    int		width;		/* 横のサイズ			*/
    int		height;		/* 縦のサイズ			*/
    int		pixel;		/* 画素数			*/
    uchar 	*Y;		/* 輝度データのポインタ		*/
    uchar	*U;		/* 色差Uデータのポインタ	*/
    uchar	*V;		/* 色差Vデータのポインタ	*/
} IMG_YUV;

// RGB画像データ構造体宣言 --------------------------------
typedef struct IMAGE_DATA2 {
    int		width;		/* 横のサイズ			*/
    int		height;		/* 縦のサイズ			*/
    int		pixel;		/* 画素数			*/
    uchar 	*R;		/* Rデータのポインタ		*/
    uchar	*G;		/* Gデータのポインタ	*/
    uchar	*B;		/* Bデータのポインタ	*/
} IMG_RGB;

void *m_alloc(int	size);
IMG_YUV *alloc_IMG_YUV(int	width,  int	height);
IMG_RGB *alloc_IMG_RGB(int	width,  int	height);
void free_IMG_YUV(IMG_YUV	*img);
void free_IMG_RGB(IMG_RGB	*img);

void read_bmp(IMG_YUV *img, IMG_RGB *img_rgb, FILE *fp);
void write_bmp(IMG_YUV *img_work, IMG_RGB *img_rgb2, FILE *outfile);
void  copy_image(IMG_YUV *img1, IMG_YUV *img2, int param);
void  setvalue_image(IMG_YUV *img1, int Yvalue, int Uvalue, int Vvalue);

unsigned char rounding(double dv);
unsigned char rounding_integer(int idv);
