import java.awt.*;
import java.io.Serializable;

public class Rect extends Figure implements Serializable {
    int x1 = 0,y1 = 0,x2 = 0,y2 = 0;
    private static boolean isFill = false;
    Rect(){}
    @Override public void paint(Graphics g){
        this.setPosition();
        if(Rect.isFill){
            g.fillRect(x1,y1,x2,y2);
        }else {
            g.drawRect(x1,y1,x2,y2);
        }
    }

    protected void setPosition(){
        this.x1 = this.x;
        this.y1 = this.y;
        this.x2 = this.w;
        this.y2 = this.h;

        if(this.w <= 0) {
            this.x1 = this.x + this.w;
            this.x2 = -this.w;
        }
        if(this.h <= 0) {
            this.y1 = this.y + this.h;
            this.y2 = -this.h;
        }
    }

    public static void changeFillMode(){
        if(Rect.isFill){
            Rect.isFill = false;
        } else {
            Rect.isFill = true;
        }
    }
    public static void setUnFill(){
        Rect.isFill = false;
    }

    public static void setFill(){
        Rect.isFill = true;
    }

}
