public class PaintManager {
    public enum PaintMode{
        DOT,
        CIRCLE,
        FILL_CIRCLE,
        RECT,
        FILL_RECT,
        LINE,
    }

    public enum PaintColor{
        BLACK,
        WHITE,
        RED,
        BLUE,
        GREEN,
        GRAY,
        GRADATION
    }

    int paintSize = 30;
    PaintMode paintMode = PaintMode.DOT;
    PaintColor paintColor = PaintColor.BLACK;

    public Figure getObject(){
        switch (paintMode){
            case DOT:
                return new Dot(paintColor, paintSize);
            case CIRCLE:
                return new Circle(paintColor, paintSize);
            case LINE:
                return new Line();
            case RECT:
                return new Rect();
        }
        return new Figure();
    }

    public void setPaintMode(PaintMode mode){
        this.paintMode = mode;
    }
    public  PaintMode getPaintMode(){
        return this.paintMode;
    }
    public  void setPaintColor(PaintColor color){
        this.paintColor = color;
    }

}
