public class PaintManager {
    public enum PaintMode{
        DOT,
        CIRCLE,
        RECT,
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
    PaintMode paintMode = PaintMode.CIRCLE;
    PaintColor paintColor = PaintColor.BLACK;

    public Figure GetObject(){
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

    public void SetPaintMode(PaintMode mode){
        this.paintMode = mode;
    }
    public  PaintMode GetPaintMode(){
        return this.paintMode;
    }
    public  void SetPaintColor(PaintColor color){
        this.paintColor = color;
    }

}
