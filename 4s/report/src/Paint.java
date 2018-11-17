import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

class Paint extends Frame implements MouseListener, MouseMotionListener{

	int x, y;
	ArrayList<Figure> objList;
	Figure obj;

	public static void main(String[] args){
		Paint f = new Paint();
		f.setSize(1280, 720);
		f.setTitle("Paint Sample");
		f.addWindowListener(new WindowAdapter(){
			@Override public void windowClosing(WindowEvent e){
				System.exit(0);
			}});
		f.setVisible(true);
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

		for(int i = 0;i < objList.size();i++){
			f = objList.get(i);
			f.paint(buffer);
		}
		if(obj != null) obj.paint(buffer);

		g.drawImage(back, 0, 0, this);
	}

	@Override public void mousePressed(MouseEvent e){
		x = e.getX();
		y = e.getY();
		obj = new Circle();
		obj.moveto(x,y);
		repaint();
	}
	@Override public void mouseReleased(MouseEvent e){
		x = e.getX();
		y = e.getY();
		obj.moveto(x,y);
		objList.add(obj);
		repaint();
		System.out.println(this.objList.size());
	}
	@Override public void mouseClicked(MouseEvent e){
		x = e.getX();
		y = e.getY();
		if(obj != null)obj.moveto(x,y);
	}
	@Override public void mouseEntered(MouseEvent e){}
	@Override public void mouseExited(MouseEvent e){}
	@Override public void mouseDragged(MouseEvent e){
		x = e.getX();
		y = e.getY();
		obj = new Circle();
		obj.moveto(x,y);
		objList.add(obj);
		obj = null;
		repaint();
	}

	@Override public void update(Graphics g){
		paint(g);
	}

	@Override public void mouseMoved(MouseEvent e){}	
}

class Coord {
	int x, y;
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
	int color;
	Figure(){
		this.color = 0;
	}
	Figure(int x, int y){
		super(x,y);
		this.color = 0;
	}
}


class Circle extends Figure {
	int size;
	int color_flag;
	Color color;

	Circle(){
		size = 30;
		Random rnd = new Random();
		this.color = new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
	}
	
	Circle(int x, int y){
		super(x, y);
		this.size = 30;
	}

	@Override public void paint(Graphics g){
		g.setColor(this.color);
		g.fillOval(x - size / 2, y - size / 2, size, size);
	}
}

class Box extends Coord {
	int color, size;
	
	Box(){
		color = 0;
		size = 30;
	}
	
	Box(int x, int y){
		super(x, y);
		this.size = 5000;
	}
	@Override public void paint(Graphics g){


		g.fillRect(x - size / 2, y - size / 2, size, size);
	}
}