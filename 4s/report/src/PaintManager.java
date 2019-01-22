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

    PaintManager(){}

    public Figure getObject(){
        switch (paintMode){
            case DOT:
                return new Dot(this.paintColor, this.paintSize);
            case CIRCLE:
                return new Circle(this.paintColor, this.paintSize, this.isfillMode());
            case LINE:
                return new Line(paintColor);
            case RECT:
                return new Rect(this.paintColor, this.isfillMode());
            case PEN:
                return new Dot(this.paintColor,this.paintSize);
        }
        return new Figure();
    }

    public void setPaintMode(PaintMode mode){

        this.paintMode = mode;
        Log.debug("mode:" + this.paintMode.toString());
    }

    public  PaintMode getPaintMode(){
        return this.paintMode;
    }
    public  void setPaintColor(PaintManager.PaintColor color){
        this.paintColor = color;
        Log.debug("color:" + this.paintColor.toString());

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
        Log.debug("size:" + this.paintSize);
    }

    public int getPaintSize(){
        return this.paintSize;
    }


}
