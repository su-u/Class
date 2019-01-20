import java.awt.*;
import java.io.Serializable;

public class Dot extends Figure implements Serializable {
    int size = 30;

    Dot(){}

    Dot(PaintManager.PaintColor color, int paintsize){
        super(color);
        this.size = paintsize;
    }

    @Override public void paint(Graphics g){
        g.setColor(this.color);
        g.fillOval(x - size / 2, y - size / 2, size, size);
    }

}
