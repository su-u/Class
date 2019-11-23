#define _CRT_SECURE_NO_WARNINGS  //旧関数のwarning出力無視

#include "sample.h"
void  histogram_image(IMG_YUV *img, int hist[], int levelmax);

int main(int argc, char* argv[])
{
     FILE *infile, *outfile, *fp;
	 char infname1[512];
     char outfname1[512];
	 char csvfname[512];
//	 int palette[1024];			//パレット用配列
	 int head[54];				//ヘッダデータ配列

	 int size;
	 int height,width;

     IMG_YUV *img, *img_out;        //8bit-YUVデータのポインタ
	 IMG_RGB *img_rgb, *img_rgb2;   //8bit-RGBデータのポインタ

	 int hist_original[256], hist_output[256];

     /* パラメータチェック */
     if (argc < 4) {    // (読み込みパラメータ数+1)より小さくてはいけない
		 fprintf(stderr, "Usage: %s infname1 outfname1 csvfname \n", argv[0]);
	     printf("        infname1: input file name \n");
	     printf("        outfname1: output file name \n");
		 printf("        csvfname: output csv file name \n");
		 exit(-1);
     }

	 /* パラメータ設定 */
	 strcpy(infname1, argv[1]);      /* 入力画像ファイル名 */
     strcpy(outfname1, argv[2]);     /* 出力画像ファイル名 */
	 strcpy(csvfname, argv[3]);    /* ヒストグラム出力csvファイル名 */

	 printf(" -Input parameters --------\n");
	 printf("   -- Input file= %s \n", infname1);
     printf("   -- Output file= %s \n", outfname1);
	 printf("   -- Output csv file= %s \n", csvfname);

	 /* 画像ファイルのオープン */
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

	//ビットマップファイルのヘッダ部を読み込み
	for(int c=0; c<54; c++){
		head[c] = fgetc(infile);
	}

	//ヘッダからの幅と高さを算出
	width  = head[18] | head[19]<<8 | head[20]<<16 | head[21]<<24;
	height = head[22] | head[23]<<8 | head[24]<<16 | head[25]<<24;
	size = width*height;
	printf(" Image size : width=%d height=%d\n",width,height);

    //  パレットデータを読み込む（このデータは本プログラムでは使わない）
//	for(int c=0; c<1024; c++){
//			palette[c] = getc(infile);
//  }

	// 画像メモリ確保
	img = alloc_IMG_YUV(width, height);
	img_out = alloc_IMG_YUV(width, height);
	img_rgb = alloc_IMG_RGB(width, height);
	img_rgb2 = alloc_IMG_RGB(width, height);

	//ビットマップファイルの画像データ部読み込み
	read_bmp(img, img_rgb, infile);

	    // imgにはYUV形式(444フォーマット）の画像データが入る //
	    // img_rgbにはRGB形式（444フォーマット）の画像データが入る //

	setvalue_image(img_out, 128, 128, 128);  //出力画像の初期値にグレー画像をセット

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
            auto value = img->Y[j + k * width] + 100;	  // 輝度をcvalueだけ加算
            img_out->Y[j + k*width] = rounding_integer(value);	  // rounding_integer()は0～255の範囲に収める関数
        }
    }

    histogram_image(img, hist_original, 256);  // 原画
    histogram_image(img_out, hist_output, 256); // 処理画

    const auto h = static_cast<int>(max / (height * width));
    const auto s = (height * width);
    printf("h:%d\n", h);
    printf("s:%d\n", s);

	for(auto k = 0; k<height; k++){
		for(auto j = 0; j<width; j++){
            //auto value = img->Y[j + k * width] + cvalue;	  // 輝度をcvalueだけ加算
            const auto z = array_sum(hist_output, img_out->Y[j + k * width]);

            const auto dst = static_cast<double>(z) / s * max;
            //printf("z:%d\n", z);
            //printf("s:%d\n", s);
            //printf("max:%d\n", max);
            //printf("dst:%lf\n", dst);

			img_out->Y[j + k * width] = rounding_integer(static_cast<int>(dst));	  // rounding_integer()は0～255の範囲に収める関数
		}
	}

	/* ヒストグラム作成 */

    histogram_image(img, hist_original, 256);  // 原画
	histogram_image(img_out, hist_output, 256); // 処理画

	for (auto i = 0; i < 256; i++) {
		fprintf(fp, "%d,%d, %d \n", i, hist_original[i], hist_output[i]);       // ヒストグラムのファイル出力
	}

	//---ここまでの処理で，ファイル出力したい画像をimg_outに入れておくこと---
//--------------------------処理はここまで------------------------------------------------

	printf(" <==== Image processing end \n");
 
	//ビットマップ画像の書き込み
	    // img_outに書き込むべきYUV形式の画像データが入っている //
	    // img_rgb2は処理のための作業用メモリ //
	write_bmp(img_out, img_rgb2, outfile);

	//ファイルのクローズとメモリ解放
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
//	Abstract	YUV画像の輝度信号に対して１次元ヒストグラムを求める
//
//	Argument	IMG_YUV *img: 原画像データ(YUV444)
//		        int hist[] :    出力ヒストグラムが入る配列
//              int levelmax : ヒストグラムのbin数（256と指定）
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
