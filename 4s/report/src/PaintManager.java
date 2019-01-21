public class PaintManager {
    public enum PaintMode{
        DOT,
        CIRCLE,
        RECT,
        LINE,
        PEN
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

    public enum PaintFillMode{
        UNFILL,
        FILL
    }

    int paintSize = 30;
    PaintMode paintMode = PaintMode.DOT;
    PaintColor paintColor = PaintColor.BLACK;
    PaintFillMode fillMode = PaintFillMode.UNFILL;

    public Figure getObject(){
        switch (paintMode){
            case DOT:
                return new Dot(paintColor, paintSize);
            case CIRCLE:
                return new Circle(paintColor, paintSize, this.isfillMode());
            case LINE:
                return new Line();
            case RECT:
                return new Rect(this.isfillMode());
            case PEN:
                return new Circle(paintColor,paintSize,true);
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

    public void setUnFillMode(){
        this.fillMode = PaintFillMode.UNFILL;
    }

    public void setFillMode(){

        this.fillMode = PaintFillMode.FILL;
    }

    private boolean isfillMode(){
        switch (this.fillMode) {
            case FILL:
                return true;
            case UNFILL:
                return false;
        }
        return false;
    }

    public void setPaintSize(int size_){
        this.paintSize = size_;
    }

    public int getPaintSize(){
        return this.paintSize;
    }


}
