public class PaintManager {
    public enum PaintMode{
        Dot,
        Circle,
        Rect,
        line,
    }

    public enum PaintColor{
        Black,
        White,
        Red,
        Blue,
        Green,
        Gray,
        Gradation
    }

    int paintSize = 30;
    PaintMode paintMode = PaintMode.Circle;
    PaintColor paintColor = PaintColor.Gradation;

    public Figure GetObject(){
        switch (paintMode){
            case Dot:
                return new Dot(paintColor, paintSize);
            case Circle:
                return new Circle(paintColor, paintSize);
            case line:
                return new Line();
            case Rect:
                return new Rect();
        }
        return new Figure();
    }


}
