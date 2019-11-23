#define _CRT_SECURE_NO_WARNINGS  //���֐���warning�o�͖���

#include "sample.h"
void  histogram_image(IMG_YUV *img, int hist[], int levelmax);

int main(int argc, char* argv[])
{
     FILE *infile, *outfile, *fp;
	 char infname1[512];
     char outfname1[512];
	 char csvfname[512];
//	 int palette[1024];			//�p���b�g�p�z��
	 int head[54];				//�w�b�_�f�[�^�z��

	 int size;
	 int height,width;

     IMG_YUV *img, *img_out;        //8bit-YUV�f�[�^�̃|�C���^
	 IMG_RGB *img_rgb, *img_rgb2;   //8bit-RGB�f�[�^�̃|�C���^

	 int hist_original[256], hist_output[256];

     /* �p�����[�^�`�F�b�N */
     if (argc < 4) {    // (�ǂݍ��݃p�����[�^��+1)��菬�����Ă͂����Ȃ�
		 fprintf(stderr, "Usage: %s infname1 outfname1 csvfname \n", argv[0]);
	     printf("        infname1: input file name \n");
	     printf("        outfname1: output file name \n");
		 printf("        csvfname: output csv file name \n");
		 exit(-1);
     }

	 /* �p�����[�^�ݒ� */
	 strcpy(infname1, argv[1]);      /* ���͉摜�t�@�C���� */
     strcpy(outfname1, argv[2]);     /* �o�͉摜�t�@�C���� */
	 strcpy(csvfname, argv[3]);    /* �q�X�g�O�����o��csv�t�@�C���� */

	 printf(" -Input parameters --------\n");
	 printf("   -- Input file= %s \n", infname1);
     printf("   -- Output file= %s \n", outfname1);
	 printf("   -- Output csv file= %s \n", csvfname);

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

	 fp = fopen(csvfname, "w");
	 if (fp == NULL) {
		 printf("CSV File open error = %s\n", csvfname);
		 return -1;
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

    auto cvalue = (height * width) / 256;
    printf("cvalue: %d\n", cvalue);

    auto max = 0;
    for (auto k = 0; k < height; k++) {
        for (auto j = 0; j < width; j++) {
             max = img_out->Y[j + k * width] > max ? img_out->Y[j + k * width]: max;
        }
    }
    printf("max: %d\n", max);

    for (auto k = 0; k < height; k++) {
        for (auto j = 0; j < width; j++) {
            auto value = img->Y[j + k * width] + 100;	  // �P�x��cvalue�������Z
            img_out->Y[j + k*width] = rounding_integer(value);	  // rounding_integer()��0�`255�͈̔͂Ɏ��߂�֐�
        }
    }

    histogram_image(img, hist_original, 256);  // ����
    histogram_image(img_out, hist_output, 256); // ������

    const auto h = static_cast<int>(max / (height * width));
    const auto s = (height * width);
    printf("h:%d\n", h);
    printf("s:%d\n", s);

	for(auto k = 0; k<height; k++){
		for(auto j = 0; j<width; j++){
            //auto value = img->Y[j + k * width] + cvalue;	  // �P�x��cvalue�������Z
            const auto z = array_sum(hist_output, img_out->Y[j + k * width]);

            const auto dst = static_cast<double>(z) / s * max;
            //printf("z:%d\n", z);
            //printf("s:%d\n", s);
            //printf("max:%d\n", max);
            //printf("dst:%lf\n", dst);

			img_out->Y[j + k * width] = rounding_integer(static_cast<int>(dst));	  // rounding_integer()��0�`255�͈̔͂Ɏ��߂�֐�
		}
	}

	/* �q�X�g�O�����쐬 */

    histogram_image(img, hist_original, 256);  // ����
	histogram_image(img_out, hist_output, 256); // ������

	for (auto i = 0; i < 256; i++) {
		fprintf(fp, "%d,%d, %d \n", i, hist_original[i], hist_output[i]);       // �q�X�g�O�����̃t�@�C���o��
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
	fclose(fp);
	free_IMG_YUV(img);
    free_IMG_YUV(img_out);
    free_IMG_RGB(img_rgb);
    free_IMG_RGB(img_rgb2);

	return 0;
}

//----------------------------------------------------------------
//	Function-ID	histogram_image
//	Function-Name	
//	Abstract	YUV�摜�̋P�x�M���ɑ΂��ĂP�����q�X�g�O���������߂�
//
//	Argument	IMG_YUV *img: ���摜�f�[�^(YUV444)
//		        int hist[] :    �o�̓q�X�g�O����������z��
//              int levelmax : �q�X�g�O������bin���i256�Ǝw��j
//
//	Return-Value	
//	Special_Desc: 
//                
//----------------------------------------------------------------
void  histogram_image(IMG_YUV *img, int hist[], int levelmax)
{

	int j, k;
	int width, height;
	int level;

	width = img->width;
	height = img->height;

	for (level = 0; level<levelmax; level++){
		hist[level] = 0;
	}

	for (k = 0; k <height; k++){
		for (j = 0; j < width; j++){
			level = img->Y[j + k*width];
			hist[level] = hist[level] + 1;
		}
	}

}

auto array_sum(const int* array, const size_t max_index) -> int
{
    auto sum = 0;

    for (size_t i = 0; i < max_index; ++i)
    {
        sum += array[i];
    }

    return sum;
}
