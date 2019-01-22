import java.awt.*;
import java.io.Serializable;

public class Rect extends Figure implements Serializable {
    private boolean isFill = true;

    Rect(){}

    Rect(PaintManager.PaintColor color, boolean isFill_){
        super(color);
        this.isFill = isFill_;

    }

    Rect(boolean isFill_){
        this.isFill = isFill_;
    }

    @Override public void paint(Graphics g){
        int x1 = 0,y1 = 0,x2 = 0,y2 = 0;
        x1 = this.x;
        y1 = this.y;
        x2 = this.w;
        y2 = this.h;

        if(this.w <= 0) {
            x1 = this.x + this.w;
            x2 = -this.w;
        }
        if(this.h <= 0) {
            y1 = this.y + this.h;
            y2 = -this.h;
        }
        g.setColor(this.color);
        if(this .isFill){
            g.fillRect(x1,y1,x2,y2);
        }else {
            g.drawRect(x1,y1,x2,y2);
        }
    }


    public void changeFillMode(){
        if(this.isFill){
            this.isFill = false;
        } else {
            this.isFill = true;
        }
    }
    public void setUnFill(){
        this.isFill = false;
    }

    public void setFill(){
        this.isFill = true;
    }

}
