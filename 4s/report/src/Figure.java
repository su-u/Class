import java.awt.*;

class Figure extends Coord implements java.io.Serializable{
    protected Color color;
    private static float hue = 0;
    private static final float saturation = 1.0f,brightness = 1.0f;
    private static final float DIFF = (1.0f / 360);

    Figure(){
        this.color = Color.getHSBColor(Figure.hue, Figure.saturation, Figure.brightness);
        this.NextColor();
    }

    Figure(int x, int y){
        super(x,y);
        this.color = Color.getHSBColor(Figure.hue, Figure.saturation, Figure.brightness);
        this.NextColor();
    }

    Figure(Paint.PaintColor color_){
        switch (color_){
            case Red:	this.color = Color.RED;		break;
            case Blue:	this.color = Color.BLUE;	break;
            case Green:	this.color = Color.GREEN;	break;
            case White:	this.color = Color.WHITE;	break;
            case Gray: 	this.color = Color.GRAY;	break;
            case Black:	this.color = Color.BLACK;	break;
            case Gradation:
                this.color = Color.getHSBColor(Figure.hue, Figure.saturation, Figure.brightness);
                NextColor();
                break;
        }

//        System.out.println("this color \t:" + this.color.toString());
    }

    void NextColor(){
//        System.out.println("hue\t\t:" + Figure.hue);
//        System.out.println("saturation\t:" + Figure.saturation);
//        System.out.println("brightness\t:" + Figure.brightness);
        Figure.hue += this.DIFF;
        if(Figure.hue > 1.0f)Figure.hue = 0.0f;
    }
}