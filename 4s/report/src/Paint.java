import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

class Paint extends Frame implements MouseListener, MouseMotionListener,ActionListener,KeyListener{

    private int x, y;
    private ArrayList<Figure> objList;
    private Figure obj;
    private static int visibleCount = 0;
    private BufferedImage img;
    private Graphics imgBuffer;
    private static final int width = 1280;
    private static final int height = 720;
    public enum PaintColor{
        Black,
        White,
        Red,
        Blue,
        Green,
        Gray,
        Gradation
    }
    private int paintSize = 30;
    private PaintColor paintColor = PaintColor.Gradation;
    CheckboxGroup cbg;
    Checkbox shapeCB1, shapeCB2, shapeCB3, shapeCB4;
    Button endButton;



    public static void main(String[] args){

        if(args.length != 0){
        	Paint.visibleCount = Integer.parseInt(args[0]);
        }
        if(visibleCount < 0)Paint.visibleCount = 0;

        Paint f = new Paint();
        f.setResizable(false);
        f.setBounds(500, 500, Paint.width, Paint.height);
        f.setTitle("Paint");
        f.addWindowListener(new WindowAdapter(){

            @Override public void windowClosing(WindowEvent e){
                System.exit(0);
            }});

        f.setVisible(true);

    }

    Paint() {
        objList = new ArrayList<Figure>();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        this.img = new BufferedImage(Paint.width,Paint.height, BufferedImage.TYPE_3BYTE_BGR);
        this.imgBuffer = img.getGraphics();

        MenuBar mb = new MenuBar();

        Menu backColor = mb.add(new Menu("背景色"));

        MenuItem backBlack = backColor.add(new MenuItem("backBlack"));
        MenuItem backWhite = backColor.add(new MenuItem("backWhite"));
        MenuItem backRed = backColor.add(new MenuItem(  "backRed"));
        MenuItem backBlue = backColor.add(new MenuItem( "backBlue"));
        MenuItem backGreen = backColor.add(new MenuItem("backGreen"));

        backRed.addActionListener(this);
        backBlue.addActionListener(this);
        backGreen.addActionListener(this);
        backBlack.addActionListener(this);
        backWhite.addActionListener(this);


        Menu PaintColor = mb.add(new Menu("描画色"));

        MenuItem paintBlack = PaintColor.add(new MenuItem(  "paintBlack"));
        MenuItem paintWhite = PaintColor.add(new MenuItem(  "paintWhite"));
        MenuItem paintRed = PaintColor.add(new MenuItem(    "paintRed"));
        MenuItem paintBlue = PaintColor.add(new MenuItem(   "paintBlue"));
        MenuItem paintGreen = PaintColor.add(new MenuItem(  "paintGreen"));
        MenuItem paintGray = PaintColor.add(new MenuItem(   "paintGray"));
        MenuItem paintGradation = PaintColor.add(new MenuItem("paintGradation"));

        paintBlack.addActionListener(this);
        paintWhite.addActionListener(this);
        paintRed.addActionListener(this);
        paintBlue.addActionListener(this);
        paintGreen.addActionListener(this);
        paintGray.addActionListener(this);
        paintGradation.addActionListener(this);


        Menu OtherSystem = mb.add(new Menu("その他"));

        MenuItem clear = OtherSystem.add(new MenuItem("全消去"));
        MenuItem writePng = OtherSystem.add(new MenuItem("png書き出し"));
        MenuItem writeJpg = OtherSystem.add(new MenuItem("jpg書き出し"));
        MenuItem paintSize = OtherSystem.add(new MenuItem("ペイントサイズ"));

        clear.addActionListener(this);
        writePng.addActionListener(this);
        writeJpg.addActionListener(this);
        paintSize.addActionListener(this);

        this.setMenuBar(mb);

    }


    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("backBlack"))    {setBackground(Color.BLACK); repaint();}
        if (cmd.equals("backWhite"))    {setBackground(Color.WHITE); repaint();}
        if (cmd.equals("backRed"))      {setBackground(Color.RED); repaint();}
        if (cmd.equals("backBlue"))     {setBackground(Color.BLUE); repaint();}
        if (cmd.equals("backGreen"))    {setBackground(Color.GREEN); repaint();}

        if (cmd.equals("paintBlack"))       this.paintColor = PaintColor.Black;
        if (cmd.equals("paintWhite"))       this.paintColor = PaintColor.White;
        if (cmd.equals("paintRed"))         this.paintColor = PaintColor.Red;
        if (cmd.equals("paintBlue"))        this.paintColor = PaintColor.Blue;
        if (cmd.equals("paintGreen"))       this.paintColor = PaintColor.Green;
        if (cmd.equals("paintGray"))        this.paintColor = PaintColor.Gray;
        if (cmd.equals("paintGradation"))   this.paintColor = PaintColor.Gradation;

        if (cmd.equals("png書き出し")){
            try {
                System.out.println("write png");
                ImageIO.write(this.img,"png",new File("./paint.png"));

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        if (cmd.equals("jpg書き出し")){
            try {
                System.out.println("write jpg");
                ImageIO.write(this.img,"jpg",new File("./paint.jpg"));

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        if (cmd.equals("全消去")) {
            objList.clear();
            repaint();
            System.out.println("--clear");
        }
        if (cmd.equals("ペイントサイズ")){
            JFrame frame = new JFrame();
            String value = JOptionPane.showInputDialog(frame, "ペイントサイズ(0 ~ 10000)",this.paintSize);
            if(value != null && value != "") {
                this.paintSize = Integer.parseInt(value);
                if (this.paintSize < 0) this.paintSize = 0;
                else if (this.paintSize > 10000) this.paintSize = 10000;
            }
        }
    }


    @Override public void paint(Graphics g){
        Figure f;
        Image back = back = createImage(getSize().width, getSize().height);
        Graphics buffer = back.getGraphics();
        int start;
        start = 0;
        if(objList.size() >= Paint.visibleCount && Paint.visibleCount != 0){
            start = objList.size() - Paint.visibleCount;
        }

        var visible = objList.size() - start;
//        System.out.println("Visible\t:" + visible);

        for(int i = start;i < objList.size();i++){
            f = objList.get(i);
            f.paint(buffer);
            //f.paint(g);

        }
        if(obj != null) obj.paint(buffer);

        g.drawImage(back, 0, 0, this);
        imgBuffer.drawImage(back, 0, 0, this);
    }

    @Override public void paintComponents(Graphics g){
        super.paintComponents(g);
        this.paint(g);
        System.out.println("paint");
    }

    @Override public void mousePressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1) {
            x = e.getX();
            y = e.getY();
            obj = new Circle(this.paintColor, paintSize);
            obj.moveto(x, y);
            repaint();
        }
    }
    @Override public void mouseReleased(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1) {
            x = e.getX();
            y = e.getY();
            obj.setWH(x - obj.x, y - obj.y);
            objList.add(obj);
            obj = null;
            repaint();
//            System.out.println("count\t:" + this.objList.size());
        }
    }
    @Override public void mouseClicked(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}
    @Override public void mouseDragged(MouseEvent e){
        x = e.getX();
        y = e.getY();
        obj = new Circle(this.paintColor,  paintSize);
        obj.moveto(x, y);
        objList.add(obj);
        repaint();
//        System.out.println("count\t:" + this.objList.size());
    }


    @Override public void mouseMoved(MouseEvent e){
    }

    @Override public void update(Graphics g){
        this.paint(g);
        this.paint(imgBuffer);
    }

    @Override public void keyPressed(KeyEvent e){
        int mod = e.getModifiersEx();
        if((e.getKeyCode() == KeyEvent.VK_Z) && (mod & InputEvent.CTRL_DOWN_MASK) != 0){
            if(objList.size() <= 0)return;
            objList.remove(objList.size() - 1);
            System.out.println("--undo--");
            repaint();
        }
    }

    @Override public void keyReleased(KeyEvent e){
    }

    @Override public void keyTyped(KeyEvent e){
    }

}





