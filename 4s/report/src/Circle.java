import java.awt.*;

class Circle extends Figure implements java.io.Serializable {
    private int size = 30;

    Circle() {
    }

    Circle(int x, int y){
        super(x, y);
    }
    Circle(PaintManager.PaintColor color_ , int size_){
        super(color_);
        this.size = size_;
//        System.out.println("size\t\t:" + this.size);
    }
    Circle(PaintManager.PaintColor color_){
        super(color_);
//        System.out.println("size\t\t:" + this.size);
    }

    @Override public void paint(Graphics g){
        g.setColor(this.color);
        int r = (int)Math.sqrt((double)(w * w + h * h)); // 半径
        g.drawOval(x - r, y - r, r * 2 , r * 2);
    }

}