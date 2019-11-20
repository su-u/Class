#define _CRT_SECURE_NO_WARNINGS  //旧関数のwarning出力無視

#include "sample.h"

//****************************************************************
// サンプルプログラム：filter_gaussian.cpp
// ガウシアンフィルタ処理を行う
//
//  実行方法： filter_sample.exe inputfilename outputfilename
//      inputfilename: 入力画像ファイル名(ビットマップ）
//      inputfilename: 出力画像ファイル名(ビットマップ）
//
//****************************************************************

int main(int argc, char* argv[])
{
	FILE *infile, *outfile;
	char infname1[512];
	char outfname1[512];
	//	 int palette[1024];			//パレット用配列
	int head[54];				//ヘッダデータ配列

	int size;
	int height, width;
	int k, j;
	int value;

	IMG_YUV *img, *img_out;        //8bit-YUVデータのポインタ
	IMG_RGB *img_rgb, *img_rgb2;   //8bit-RGBデータのポインタ

	/* パラメータチェック */
	if (argc < 3) {    // (読み込みパラメータ数+1)より小さくてはいけない
		fprintf(stderr, "Usage: %s infname1 outfname1 \n", argv[0]);
		printf("        infname1: input file name \n");
		printf("        outfname1: output file name \n");
		exit(-1);
	}

	/* パラメータ設定 */
	strcpy(infname1, argv[1]);      /* 入力画像ファイル名 */
	strcpy(outfname1, argv[2]);     /* 出力画像ファイル名 */

	printf(" -Input parameters --------\n");
	printf("   -- Input file= %s \n", infname1);
	printf("   -- Output file= %s \n", outfname1);

	/* 画像ファイルのオープン */
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

	//ビットマップファイルのヘッダ部を読み込み
	for (int c = 0; c < 54; c++) {
		head[c] = fgetc(infile);
	}

	//ヘッダからの幅と高さを算出
	width = head[18] | head[19] << 8 | head[20] << 16 | head[21] << 24;
	height = head[22] | head[23] << 8 | head[24] << 16 | head[25] << 24;
	size = width * height;
	printf(" Image size : width=%d height=%d\n", width, height);

	//  パレットデータを読み込む（このデータは本プログラムでは使わない）
//	for(int c=0; c<1024; c++){
//			palette[c] = getc(infile);
//  }

	// 画像メモリ確保
	img = alloc_IMG_YUV(width, height);
	img_out = alloc_IMG_YUV(width, height);
	img_rgb = alloc_IMG_RGB(width, height);  //作業用配列
	img_rgb2 = alloc_IMG_RGB(width, height); //作業用配列

   //ビットマップファイルの画像データ部読み込み
	read_bmp(img, img_rgb, infile);

	// imgにはYUV形式(444フォーマット）の画像データが入る //
	// img_rgbにはRGB形式（444フォーマット）の画像データが入る //

	printf(" Image processing start =====> \n");
	setvalue_image(img_out, 128, 128, 128);     /* 初期化．出力画像全体の画素に，Y=128, U,V=128（グレイレベル）をセットする */

//--------------------------処理はここへ（画像処理部分）---------------------------------------------

	int jj, kk;
	double dvalue;

	for (k = 1; k < height - 1; k++) {      /* 画像の上下のふち1列は計算しない */
		for (j = 1; j < width - 1; j++) {        /* 画像の左右のふち1列は計算しない */

			std::vector<int> v;
			int data[9] = { 0 };
			for (kk = -1; kk <= 1; kk++) {
				for (jj = -1; jj <= 1; jj++) {
					v.push_back(img->Y[j + jj + (k + kk)*width]);
				}
			}
			std::sort(v.begin(), v.end());
			dvalue = (double)v[5];

			img_out->Y[j + k * width] = rounding(dvalue);     // rounding()は，実数値を0〜255の範囲の整数値にする関数 

		}
	}

	//---ここまでの処理で，ファイル出力したい画像をimg_outに入れておくこと---
	//--------------------------処理はここまで（画像処理部分おわり）-----------------------------------------

	printf(" <==== Image processing end \n");

	//ビットマップ画像の書き込み
		// img_outに書き込むべきYUV形式の画像データが入っている //
		// img_rgb2は処理のための作業用メモリ //
	write_bmp(img_out, img_rgb2, outfile);

	//ファイルのクローズとメモリ解放
	fclose(infile);
	fclose(outfile);
	free_IMG_YUV(img);
	free_IMG_YUV(img_out);
	free_IMG_RGB(img_rgb);
	free_IMG_RGB(img_rgb2);

	return 0;
}