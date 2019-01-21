import java.awt.*;

class Circle extends Figure implements java.io.Serializable {
    private int size = 30;
    private boolean isFill = false;

    Circle() {
    }

    Circle(int x, int y){
        super(x, y);
    }
    Circle(PaintManager.PaintColor color_ , int size_, boolean isFill_){
        super(color_);
        this.size = size_;
        this.isFill = isFill_;
//        System.out.println("size\t\t:" + this.size);
    }


    @Override public void paint(Graphics g){
        g.setColor(this.color);
        int r = (int)Math.sqrt((double)(w * w + h * h)); // 半径
        if(this.isFill){
            g.fillOval(x - r, y - r, r * 2 , r * 2);
        } else {
            g.drawOval(x - r, y - r, r * 2 , r * 2);
        }
    }

    public void setUnFill(){

        this.isFill = false;
    }

    public void setFill(){
        this.isFill = true;
    }
}