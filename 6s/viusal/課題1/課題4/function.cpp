#define _CRT_SECURE_NO_WARNINGS  //���֐���warning�o�͖���

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#include "sample.h"

typedef	unsigned char	uchar;


//----------------------------------------------------------------
//  Function-ID	rounding(double dv)�@�_�u���^�l��0-255�͈̔͂Ɋۂ߂�
//----------------------------------------------------------------
unsigned char rounding(double dv)
{
    unsigned char ucv; 
	int iv = (int)(dv + 0.5);
	if (iv < 0) {
		iv=0;
	}
	else if (iv > 255) {
		iv=255;
	}
    ucv = (unsigned char)iv;
    return ucv;
}

//----------------------------------------------------------------
//  Function-ID	rounding_integer(int idv) �����l��0-255�͈̔͂Ɋۂ߂�
//----------------------------------------------------------------
unsigned char rounding_integer(int idv)
{
    unsigned char ucv; 
	int iv;
	iv=idv;
	if (idv < 0){
		iv=0;
	}
	else if (idv > 255){
		iv=255;
	}
    ucv = (unsigned char)iv;
    return ucv;
}

//------------------------------------------------------------------------------
//	Function-ID	m_alloc
//	Function-Name	�������m��
//	Abstract	�ُ�I�����ɃG���[���b�Z�[�W��Ԃ�malloc�֐�
//	Argument	int	size	= �m�ۂ��郁�����T�C�Y
//	Return-Value	void	*a	= �m�ۂ����������̃A�h���X
//	Special_Desc
//------------------------------------------------------------------------------
void *
m_alloc(int	size)
{
    void	*a;

    if ((a = malloc(size)) == NULL) {
	fprintf(stderr, "m_alloc: memory allocation error.\n");
	exit(-1);
    }

    return (a);
}



//----------------------------------------------------------------
//	Function-ID	alloc_IMG_YUV
//	Function-Name	IMG�f�[�^�̈�m��
//	Abstract	IMG_YUV�f�[�^�̃��������m�ۂ���
//	Argument	int	width	= �摜���T�C�Y
//				int	height	= �摜�c�T�C�Y
//	Return-Value	IMG_YUV *		= �m�ۂ����̈�̃|�C���^
//	Special_Desc	
//----------------------------------------------------------------
IMG_YUV *
alloc_IMG_YUV(int	width,  int	height)
{
    IMG_YUV	*img;

    img		= (IMG_YUV   *) m_alloc(sizeof (IMG_YUV));
    img->width	= width;
    img->height	= height;
    img->pixel	= img->width * img->height;
    img->Y	= (uchar *) m_alloc(sizeof (uchar) * img->pixel);
	img->U	= (uchar *) m_alloc(sizeof (uchar) * img->pixel);
	img->V	= (uchar *) m_alloc(sizeof (uchar) * img->pixel);

    return (img);
}

//----------------------------------------------------------------
//	Function-ID	alloc_IMG_RGB
//	Function-Name	IMG�f�[�^�̈�m��
//	Abstract	IMG_RGB�f�[�^�̃��������m�ۂ���
//	Argument	int	width	= �摜���T�C�Y
//				int	height	= �摜�c�T�C�Y
//	Return-Value	IMG_RGB *		= �m�ۂ����̈�̃|�C���^
//	Special_Desc	
//----------------------------------------------------------------
IMG_RGB *
alloc_IMG_RGB(int	width,  int	height)
{
    IMG_RGB	*img;

    img		= (IMG_RGB   *) m_alloc(sizeof (IMG_RGB));
    img->width	= width;
    img->height	= height;
    img->pixel	= img->width * img->height;
    img->R	= (uchar *) m_alloc(sizeof (uchar) * img->pixel);
	img->G	= (uchar *) m_alloc(sizeof (uchar) * img->pixel);
	img->B	= (uchar *) m_alloc(sizeof (uchar) * img->pixel);

    return (img);
}

//------------------------------------------------------------------------------
//	Function-ID	free_IMG_YUV
//	Function-Name	IMG_YUV�f�[�^���������
//	Abstract	IMG_YUV�f�[�^�̗̈���������
//	Argument	IMG_YUV	*img	= �摜�\����
//	Return-Value	Nothing
//	Special_Desc	
//------------------------------------------------------------------------------
void
free_IMG_YUV(IMG_YUV	*img)
{
    if (img->Y)		free(img->Y);
    if (img->U)		free(img->U);
    if (img->V)		free(img->V);
    free(img);
}

//------------------------------------------------------------------------------
//	Function-ID	free_IMG_RGB
//	Function-Name	IMG_RGB�f�[�^���������
//	Abstract	IMG_RGB�f�[�^�̗̈���������
//	Argument	IMG_RGB	*img	= �摜�\����
//	Return-Value	Nothing
//	Special_Desc	
//------------------------------------------------------------------------------
void
free_IMG_RGB(IMG_RGB	*img)
{
    if (img->R)		free(img->R);
    if (img->G)		free(img->G);
    if (img->B)		free(img->B);
    free(img);
}

//----------------------------------------------------------------
//	Function-ID	read_bmp
//	Function-Name	�r�b�g�}�b�v�ǂݍ���
//	Abstract	img_rgb�ɓǂݍ��݁A YUV�ϊ�����img�Ɋi�[
//	Argument	IMG_YUV *img:	YUV�M�����i�[����̈�
//              IMG_RGB *img_rgb:	�ǂݍ���BMP���i�[����̈�
//              FILE *infile:       �ǂݍ��ݐ�|�C���^
//	Special_Desc	24�r�b�g�̂ݑΉ�
//
//            +---------------------------------
//               Coded by Takashi Yoshino
//               Revised by Y.Yashima 2012.09.15
//----------------------------------------------------------------
void read_bmp(IMG_YUV *img,IMG_RGB *img_rgb, FILE *infile){
	int width, height;
	int size;

	width = img->width;
    height = img->height;
	size = width * height;
	int k,j;
	double y,u,v;
//	double r,g,b;

	//�摜�f�[�^��ǂݍ���
	for( k=height-1; k>=0; k--){
		for( j=0; j<width; j++){
			img_rgb->B[j+k*width] = getc(infile);
			img_rgb->G[j+k*width] = getc(infile);
			img_rgb->R[j+k*width] = getc(infile);
		}
	}

	for(int k=0;k<height;k++){
		for(int j=0;j<width;j++){
				y = 0.2126 * img_rgb->R[ j+k*width ] + 0.7152 * img_rgb->G[ j+k*width ] + 0.0722 * img_rgb->B[ j+k*width ];
				u = -0.1146 * img_rgb->R[ j+k*width ] - 0.3854 * img_rgb->G[ j+k*width ] + 0.5 * img_rgb->B[ j+k*width ];
				v = 0.5 * img_rgb->R[ j+k*width ] - 0.4542 * img_rgb->G[ j+k*width ] - 0.0458 * img_rgb->B[ j+k*width ];
				img->Y[ j + k*width ] = rounding(y);       /* Y conponent */
				img->U[ j + k*width ] = rounding(u+128);   /* U conponent */
				img->V[ j + k*width ] = rounding(v+128);   /* V conponent */
		}
	}
}
//----------------------------------------------------------------
//	Function-ID	write_bmp
//	Function-Name	�r�b�g�}�b�v��������
//	Abstract	YUV��RGB�ϊ�����img_rbg2�Ɋi�[
//	Argument	IMG_YUV *img:	YUV�M�����i�[����Ă���摜�f�[�^
//              IMG_RGB *img_rgb:       �F�ϊ���̉摜�i�[�̈�i��Ɨp�z��j   
//              FILE *outfile:       �������ݐ�|�C���^
//	Special_Desc	24�r�b�g�̂ݑΉ�
//
//            +---------------------------------
//               Coded by Takashi Yoshino
//               Revised by Y.Yashima 2012.09.15
//----------------------------------------------------------------
void write_bmp(IMG_YUV *img, IMG_RGB *img_rgb, FILE *outfile){
    int width, height;
	int size;

	width = img->width;
    height = img->height;
	size = width * height;
	int k,j;
	double r,g,b;

	  	//YUV-RGB�ϊ��ƕ��בւ�
		for(k=0;k<height;k++){
			for(j=0; j<width; j++){
			  	r = img->Y[ j + k*width ] + 1.5748 * (img->V[ j + k*width ]-128);
				g = img->Y[ j + k*width ] - 0.1873 * (img->U[ j + k*width ]-128) - 0.4681 * (img->V[ j + k*width ]-128);
				b = img->Y[ j + k*width ] + 1.8556 * (img->U[ j + k*width ]-128) ;
				img_rgb->R[ j + (height-k-1)*width ] = rounding(r);
				img_rgb->G[ j + (height-k-1)*width ] = rounding(g);
				img_rgb->B[ j + (height-k-1)*width ] = rounding(b);
			}
		}

		 //�w�b�_��������
	  uchar header_buf[54];
	  unsigned int file_size;
	  unsigned int offset_to_data;
	  unsigned long info_header_size;
	  unsigned int planes;
	  unsigned int color;
	  unsigned long compress;
	  unsigned long data_size;
	  long xppm;
	  long yppm;

	  file_size=size+54;
	  offset_to_data=54;
	  info_header_size=40;
	  planes = 1;
	  color = 24;
	  compress = 0;
	  data_size = size;
	  xppm = 1;
	  yppm = 1;

	  header_buf[0]='B';
	  header_buf[1]='M';
	  memcpy(header_buf+2,&file_size,sizeof(file_size));
	  header_buf[6]=0;
	  header_buf[7]=0;
	  header_buf[8]=0;
	  header_buf[9]=0;
	  memcpy(header_buf + 10, &offset_to_data, sizeof(file_size));
	  header_buf[11]=0;
	  header_buf[12]=0;
	  header_buf[13]=0;
	  memcpy(header_buf + 14, &info_header_size, sizeof(info_header_size));
	  header_buf[15] = 0;
	  header_buf[16] = 0;
	  header_buf[17] = 0;
	  memcpy(header_buf + 18, &width, sizeof(width));
	  memcpy(header_buf + 22, &height, sizeof(height));
	  memcpy(header_buf + 26, &planes, sizeof(planes));
	  memcpy(header_buf + 28, &color, sizeof(color));
	  memcpy(header_buf + 30, &compress, sizeof(compress));
	  memcpy(header_buf + 34, &data_size, sizeof(data_size));
	  memcpy(header_buf + 38, &xppm, sizeof(xppm));
	  memcpy(header_buf + 42, &yppm, sizeof(yppm));
	  header_buf[46] = 0;
	  header_buf[47] = 0;
	  header_buf[48] = 0;
	  header_buf[49] = 0;
	  header_buf[50] = 0;
	  header_buf[51] = 0;
	  header_buf[52] = 0;
	  header_buf[53] = 0;

	  fwrite(header_buf, sizeof(unsigned char), 54, outfile);

	  for(int kk=0;kk<size;kk++){
		  fputc(img_rgb->B[kk],outfile);		//B���o��
		  fputc(img_rgb->G[kk],outfile);		//G
		  fputc(img_rgb->R[kk],outfile);		//R
	  }
}

//----------------------------------------------------------------
//	Function-ID	copy_image
//	Function-Name	
//	Abstract	IMG_YUV�^�\���̂̉摜�f�[�^���R�s�[����i�\���̃|�C���^�������n���j
//
//	Argument	IMG_YUV *img1: copy���摜�f�[�^
//		        IMG_YUV *img2: copy��摜�f�[�^
//		        int param : param=1(0�ȊO)�̂Ƃ�YUV���ׂĂ��R�s�[�i�J���[�̂܂܃R�s�[�j
//                          param=0�̂Ƃ�Y�����R�s�[����U��V�ɂ�128������i���m�N�����j
//			
//	Return-Value	
//	Special_Desc
//
//            +---------------------------------
//               Coded by Y.Yashima
//
//----------------------------------------------------------------
void  copy_image(IMG_YUV *img1, IMG_YUV *img2, int param)
{

	 int j, k; 
	 int width, height;

	 width = img1->width;
     height = img1->height;

	 if(param==0) {
		 for(k=0;k<height;k++){
			 for(j=0; j<width; j++){
				 img2->Y[ j + k*width ] = img1->Y[ j + k*width ];
				 img2->U[ j + k*width ] = 128;
				 img2->V[ j + k*width ] = 128;
			 }
		 }
	 }
	 else{
		 for(k=0;k<height;k++){
			 for(j=0; j<width; j++){
				 img2->Y[ j + k*width ] = img1->Y[ j + k*width ];
				 img2->U[ j + k*width ] = img1->U[ j + k*width ];
				 img2->V[ j + k*width ] = img1->V[ j + k*width ];
			 }
		 }
	 }

}

//----------------------------------------------------------------
//	Function-ID	setvalue_image
//	Function-Name	
//	Abstract	IMG_YUV�^�\���̂̉摜�f�[�^�̊e�R���|�[�l���g�ɒ�߂�ꂽ�l���Z�b�g����
//
//	Argument	IMG_YUV *img1: �摜�f�[�^
//		        int Yvalue : �P�x�M���ɃZ�b�g����l�i0-255�j
//		        int Uvalue : �P�x�M���ɃZ�b�g����l�i0-255�j
//		        int Vvalue : �P�x�M���ɃZ�b�g����l�i0-255�j
//			
//	Return-Value	
//	Special_Desc	
//----------------------------------------------------------------
void  setvalue_image(IMG_YUV *img1, int Yvalue, int Uvalue, int Vvalue)
{

	int j, k; 
	int width, height;
	unsigned char Ydata, Udata, Vdata;

	width = img1->width;
    height = img1->height;

	if(Yvalue<0) {
		Ydata = 0;
	}
	else if(Yvalue>255){
		Ydata = 255;
	}
	else{
		Ydata = (unsigned char) Yvalue;
	}

	if(Uvalue<0) {
		Udata = 0;
	}
	else if(Uvalue>255){
		Udata = 255;
	}
	else{
		Udata = (unsigned char) Uvalue;
	}

	if(Vvalue<0) {
		Vdata = 0;
	}
	else if(Vvalue>255){
		Vdata = 255;
	}
	else{
		Vdata = (unsigned char) Vvalue;
	}

	for(k=0;k<height;k++){
		for(j=0; j<width; j++){
			img1->Y[ j + k*width ] = Ydata;
	    }
	}

	for(k=0;k<height;k++){
		for(j=0; j<width; j++){
			img1->U[ j + k*width ] = Udata;
		    img1->V[ j + k*width ] = Vdata;
		}
	}

}
