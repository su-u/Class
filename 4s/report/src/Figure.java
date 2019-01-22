import java.awt.*;

class Figure extends Coord implements java.io.Serializable{
    protected Color color;
    private static float hue = 0;
    private static final float saturation = 1.0f,brightness = 1.0f;
    private static final float DIFF = (1.0f / 360);

    protected int w,h;

    Figure(){
        w = h = 0;
    }

    Figure(int x, int y){

        this.nextColor();
        w = h = 0;
    }

    Figure(PaintManager.PaintColor color_){
        switch (color_){
            case RED:	this.color = Color.RED;		break;
            case BLUE:	this.color = Color.BLUE;	break;
            case GREEN:	this.color = Color.GREEN;	break;
            case WHITE:	this.color = Color.WHITE;	break;
            case GRAY: 	this.color = Color.GRAY;	break;
            case BLACK:	this.color = Color.BLACK;	break;
            case GRADATION:
                this.color = Color.getHSBColor(Figure.hue, Figure.saturation, Figure.brightness);
                nextColor();
                break;
        }

        Log.info("this color \t:" + this.color.toString());
    }

    void nextColor(){
        Log.info("hue\t\t:" + Figure.hue);
        Log.info("saturation\t:" + Figure.saturation);
        Log.info("brightness\t:" + Figure.brightness);
        Figure.hue += this.DIFF;
        if(Figure.hue > 1.0f)Figure.hue = 0.0f;
    }

    public void setWH(int w,int h){
        this.w = w;
        this.h = h;
    }
}