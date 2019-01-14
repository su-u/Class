import java.awt.*;
import java.io.Serializable;

public class Rect extends Figure implements Serializable {
    Rect(){}
    @Override public void paint(Graphics g){
        g.drawRect(this.x, this.y, this.w, this.h);
    }

}
