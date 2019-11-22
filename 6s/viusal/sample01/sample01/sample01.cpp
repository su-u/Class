#define _CRT_SECURE_NO_WARNINGS  //���֐���warning�o�͖���

#include "sample.h"

int main(int argc, char* argv[])
{
     FILE *infile,*outfile;
	 char infname1[512];
     char outfname1[512];
//	 int palette[1024];			//�p���b�g�p�z��
	 int head[54];				//�w�b�_�f�[�^�z��

	 int size;
	 int height,width;
	 int k,j;
	 int cvalue;

	 IMG_YUV *img, *img_out;        //8bit-YUV�f�[�^�̃|�C���^
	 IMG_RGB *img_rgb, *img_rgb2;   //8bit-RGB�f�[�^�̃|�C���^

     /* �p�����[�^�`�F�b�N */
     if (argc < 4) {    // (�ǂݍ��݃p�����[�^��+1)��菬�����Ă͂����Ȃ�
		 fprintf(stderr, "Usage: %s infname1 outfname1 cvalue \n", argv[0]);
	     printf("        infname1: input file name \n");
	     printf("        outfname1: output file name \n");
		 printf("        cvalue: value to be added \n");
	     exit(-1);
     }

	 /* �p�����[�^�ݒ� */
	 strcpy(infname1, argv[1]);      /* ���͉摜�t�@�C���� */
     strcpy(outfname1, argv[2]);     /* �o�͉摜�t�@�C���� */
	 cvalue = atoi(argv[3]);         /* ���Z����l */

	 printf(" -Input parameters --------\n");
	 printf("   -- Input file= %s \n", infname1);
     printf("   -- Output file= %s \n", outfname1);
     printf("   -- cvalue= %d \n", cvalue);

	 /* �摜�t�@�C���̃I�[�v�� */
     if( !strcmp(infname1,"-") ){
		 infile = stdin;
     }
     else if ((infile = fopen(infname1, "rb")) == NULL) {
		 fprintf(stderr, "File open error = %s\n", infname1);
		 exit(-1);
     }

	 sprintf(outfname1, "%s", argv[2]);
     if ((outfile = fopen(outfname1, "wb")) == NULL) {
		 fprintf(stderr, "OutPut File open error = %s\n", outfname1);
		 exit(-1);
     }

	//�r�b�g�}�b�v�t�@�C���̃w�b�_����ǂݍ���
	for(int c=0; c<54; c++){
		head[c] = fgetc(infile);
	}

	//�w�b�_����̕��ƍ������Z�o
	width  = head[18] | head[19]<<8 | head[20]<<16 | head[21]<<24;
	height = head[22] | head[23]<<8 | head[24]<<16 | head[25]<<24;
	size = width*height;
	printf(" Image size : width=%d height=%d\n",width,height);

    //  �p���b�g�f�[�^��ǂݍ��ށi���̃f�[�^�͖{�v���O�����ł͎g��Ȃ��j
//	for(int c=0; c<1024; c++){
//			palette[c] = getc(infile);
//  }

	// �摜�������m��
	img = alloc_IMG_YUV(width, height);
	img_out = alloc_IMG_YUV(width, height);
	img_rgb = alloc_IMG_RGB(width, height);
	img_rgb2 = alloc_IMG_RGB(width, height);

	//�r�b�g�}�b�v�t�@�C���̉摜�f�[�^���ǂݍ���
	read_bmp(img, img_rgb, infile);

	    // img�ɂ�YUV�`��(444�t�H�[�}�b�g�j�̉摜�f�[�^������ //
	    // img_rgb�ɂ�RGB�`���i444�t�H�[�}�b�g�j�̉摜�f�[�^������ //

	setvalue_image(img_out, 128, 128, 128);  //�o�͉摜�̏����l�ɃO���[�摜���Z�b�g

	printf(" Image processing start =====> \n");

//--------------------------�����͂�����--------------------------------------------------

	for(k=0; k<height; k++){
		for(j=0; j<width; j++){

			const int bit = img->Y[j + k * width];
			const int value = bit < 128 ? 0 : 255;

			img_out->Y[j + k*width] = rounding_integer(value);	  // rounding_integer()��0�`255�͈̔͂Ɏ��߂�֐�

		}
	}

//---�����܂ł̏����ŁC�t�@�C���o�͂������摜��img_out�ɓ���Ă�������---
//--------------------------�����͂����܂�------------------------------------------------

	printf(" <==== Image processing end \n");
 
	//�r�b�g�}�b�v�摜�̏�������
	    // img_out�ɏ������ނׂ�YUV�`���̉摜�f�[�^�������Ă��� //
	    // img_rgb2�͏����̂��߂̍�Ɨp������ //
	write_bmp(img_out, img_rgb2, outfile);

	//�t�@�C���̃N���[�Y�ƃ��������
	fclose(infile);
	fclose(outfile);
    free_IMG_YUV(img);
    free_IMG_YUV(img_out);
    free_IMG_RGB(img_rgb);
    free_IMG_RGB(img_rgb2);

	return 0;
}