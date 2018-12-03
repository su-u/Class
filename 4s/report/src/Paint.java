import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

class Paint extends Frame implements MouseListener, MouseMotionListener{

	private int x, y;
	private ArrayList<Figure> objList;
	private Figure obj;
	private static int visibleCount = 0;
	private boolean isDoragge = false;
	private Figure PressBuffer = null;

	public static void main(String[] args){
		Paint f = new Paint();
//		f.setSize(1280, 720);
		f.setBounds(500, 500, 1920, 1080);
		f.setTitle("Paint");
		//f.setBackground(Color.BLACK);
		f.addWindowListener(new WindowAdapter(){
			@Override public void windowClosing(WindowEvent e){
				System.exit(0);
			}});
		f.setVisible(true);
		if(args.length != 0){
			Paint.visibleCount = Integer.parseInt(args[0]);
		}
		if(visibleCount < 0)Paint.visibleCount = 0;
	}

	Paint() {
		objList = new ArrayList<Figure>();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override public void paint(Graphics g){
		Figure f;
		Image back = back = createImage(getSize().width, getSize().height);
		Graphics buffer = back.getGraphics();
		int start = 0;
		if(objList.size() >= Paint.visibleCount && Paint.visibleCount != 0){
			start = objList.size() - Paint.visibleCount;
		}
		for(int i = start;i < objList.size();i++){
			f = objList.get(i);
			f.paint(buffer);
		}
		if(obj != null) obj.paint(buffer);

		g.drawImage(back, 0, 0, this);
	}

	@Override public void mousePressed(MouseEvent e){
		this.isDoragge = false;
		x = e.getX();
		y = e.getY();
		obj = new Circle();
		obj.moveto(x,y);
		PressBuffer = obj;
		repaint();
	}
	@Override public void mouseReleased(MouseEvent e){
		x = e.getX();
		y = e.getY();
		if(isDoragge == false){
			obj = this.PressBuffer;
		}else {
			obj = new Circle();
			obj.moveto(x,y);
		}
		objList.add(obj);
		obj = null;
		repaint();
		System.out.println("count\t\t:" + this.objList.size());
	}
	@Override public void mouseClicked(MouseEvent e){
		int btn = e.getButton();
		if (btn == MouseEvent.BUTTON3){
			objList.clear();
		}
		System.out.println("--clear--");
	}
	@Override public void mouseEntered(MouseEvent e){}
	@Override public void mouseExited(MouseEvent e){}
	@Override public void mouseDragged(MouseEvent e){
		x = e.getX();
		y = e.getY();
		if(this.isDoragge == false){
			obj = this.PressBuffer;
		}else {
			obj = new Circle();
			obj.moveto(x,y);
		}
		this.isDoragge = true;
		objList.add(obj);
		obj = null;
		repaint();
		System.out.println("count\t\t:" + this.objList.size());
	}

	@Override public void update(Graphics g){
		paint(g);
	}

	@Override public void mouseMoved(MouseEvent e){}

}

class Coord {
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

class Figure extends Coord {
	protected Color color;
	private static float hue = 0;
	private static final float saturation = 1.0f,brightness = 1.0f;
	private static final float DIFF = (1.0f / 360);

	Figure(){
		this.color = Color.getHSBColor(Figure.hue, Figure.saturation, Figure.brightness);
		this.NextColor();
	}

	Figure(int x, int y){
		super(x,y);
		this.color = Color.getHSBColor(Figure.hue, Figure.saturation, Figure.brightness);
		this.NextColor();
	}

	void NextColor(){
		System.out.println("hue\t\t:" + Figure.hue);
		System.out.println("saturation\t:" + Figure.saturation);
		System.out.println("brightness\t:" + Figure.brightness);
		Figure.hue += this.DIFF;
		if(Figure.hue > 1.0f)Figure.hue = 0.0f;
	}
}


class Circle extends Figure {
	private int size;
	private static boolean size_flag = false;;

	Circle() {
		if (Circle.size_flag == true) {
			this.size = 60;
			Circle.size_flag = false;
		} else {
			this.size = 30;
			Circle.size_flag = true;
		}
		this.size = 30;
	}

	Circle(int x, int y){
		super(x, y);
	}

	@Override public void paint(Graphics g){
		g.setColor(this.color);
		g.fillOval(x - size / 2, y - size / 2, size, size);
	}
}

