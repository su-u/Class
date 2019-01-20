import java.awt.*;
import java.io.Serializable;

public class Line extends Figure implements Serializable {
    Line(){}
    Line(PaintManager.PaintColor color, int size){
        super(color);

    }
    @Override public void paint(Graphics g){
        g.drawLine(this.x, this.y,this.x + this.w, this.y + this.h);
    }

}
