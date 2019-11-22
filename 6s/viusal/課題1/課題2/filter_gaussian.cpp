#define _CRT_SECURE_NO_WARNINGS  //���֐���warning�o�͖���

#include "sample.h"

//****************************************************************
// �T���v���v���O�����Ffilter_gaussian.cpp
// �K�E�V�A���t�B���^�������s��
//
//  ���s���@�F filter_sample.exe inputfilename outputfilename
//      inputfilename: ���͉摜�t�@�C����(�r�b�g�}�b�v�j
//      inputfilename: �o�͉摜�t�@�C����(�r�b�g�}�b�v�j
//
//****************************************************************

int main(int argc, char* argv[])
{
	FILE *infile, *outfile;
	char infname1[512];
	char outfname1[512];
	//	 int palette[1024];			//�p���b�g�p�z��
	int head[54];				//�w�b�_�f�[�^�z��

	int size;
	int height, width;

	IMG_YUV *img, *img_out;        //8bit-YUV�f�[�^�̃|�C���^
	IMG_RGB *img_rgb, *img_rgb2;   //8bit-RGB�f�[�^�̃|�C���^

	/* �p�����[�^�`�F�b�N */
	if (argc < 3) {    // (�ǂݍ��݃p�����[�^��+1)��菬�����Ă͂����Ȃ�
		fprintf(stderr, "Usage: %s infname1 outfname1 \n", argv[0]);
		printf("        infname1: input file name \n");
		printf("        outfname1: output file name \n");
		exit(-1);
	}

	/* �p�����[�^�ݒ� */
	strcpy(infname1, argv[1]);      /* ���͉摜�t�@�C���� */
	strcpy(outfname1, argv[2]);     /* �o�͉摜�t�@�C���� */

	printf(" -Input parameters --------\n");
	printf("   -- Input file= %s \n", infname1);
	printf("   -- Output file= %s \n", outfname1);

	/* �摜�t�@�C���̃I�[�v�� */
	if (!strcmp(infname1, "-")) {
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
	for (int c = 0; c < 54; c++) {
		head[c] = fgetc(infile);
	}

	//�w�b�_����̕��ƍ������Z�o
	width = head[18] | head[19] << 8 | head[20] << 16 | head[21] << 24;
	height = head[22] | head[23] << 8 | head[24] << 16 | head[25] << 24;
	size = width * height;
	printf(" Image size : width=%d height=%d\n", width, height);

	//  �p���b�g�f�[�^��ǂݍ��ށi���̃f�[�^�͖{�v���O�����ł͎g��Ȃ��j
//	for(int c=0; c<1024; c++){
//			palette[c] = getc(infile);
//  }

	// �摜�������m��
	img = alloc_IMG_YUV(width, height);
	img_out = alloc_IMG_YUV(width, height);
	img_rgb = alloc_IMG_RGB(width, height);  //��Ɨp�z��
	img_rgb2 = alloc_IMG_RGB(width, height); //��Ɨp�z��

   //�r�b�g�}�b�v�t�@�C���̉摜�f�[�^���ǂݍ���
	read_bmp(img, img_rgb, infile);

	// img�ɂ�YUV�`��(444�t�H�[�}�b�g�j�̉摜�f�[�^������ //
	// img_rgb�ɂ�RGB�`���i444�t�H�[�}�b�g�j�̉摜�f�[�^������ //

	printf(" Image processing start =====> \n");
	setvalue_image(img_out, 128, 128, 128);     /* �������D�o�͉摜�S�̂̉�f�ɁCY=128, U,V=128�i�O���C���x���j���Z�b�g���� */

//--------------------------�����͂����ցi�摜���������j---------------------------------------------


    for (auto k = 1; k < height - 1; k++) {      /* �摜�̏㉺�̂ӂ�1��͌v�Z���Ȃ� */
		for (auto j = 1; j < width - 1; j++) {        /* �摜�̍��E�̂ӂ�1��͌v�Z���Ȃ� */

			std::vector<int> v;
			int data[9] = { 0 };
			for (auto kk = -1; kk <= 1; kk++) {
				for (auto jj = -1; jj <= 1; jj++) {
					v.push_back(img->Y[j + jj + (k + kk)*width]);
				}
			}
			std::sort(v.begin(), v.end());
			const auto value = static_cast<double>(v[5]);

			img_out->Y[j + k * width] = rounding(value);     // rounding()�́C�����l��0�`255�͈̔͂̐����l�ɂ���֐� 

		}
	}

	//---�����܂ł̏����ŁC�t�@�C���o�͂������摜��img_out�ɓ���Ă�������---
	//--------------------------�����͂����܂Łi�摜�������������j-----------------------------------------

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