import java.awt.*;

class Coord implements java.io.Serializable {
    protected int x, y;
    Coord(){
        x = y = 0;
    }
    Coord(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }

    public void moveto(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void paint(Graphics g){}
}
