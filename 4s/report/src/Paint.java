import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

class Paint extends Frame implements MouseListener, MouseMotionListener,ActionListener,KeyListener,ItemListener{

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
    CheckboxGroup shapeCBG;
    Checkbox shapeCB1, shapeCB2, shapeCB3, shapeCB4;
    Button endButton;

    PaintManager pm;


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


        shapeCBG = new CheckboxGroup();
        shapeCB1 = new Checkbox("丸", shapeCBG,true);
        shapeCB2 = new Checkbox("円", shapeCBG,false);
        shapeCB3 = new Checkbox("四角",shapeCBG,false);
        shapeCB4 = new Checkbox("線",shapeCBG,false);

        final int cbWidth = 60;


        shapeCB1.setBounds(Paint.width - cbWidth,50,cbWidth,20);
        shapeCB2.setBounds(Paint.width - cbWidth,70,cbWidth,20);
        shapeCB3.setBounds(Paint.width - cbWidth,90,cbWidth,20);
        shapeCB4.setBounds(Paint.width - cbWidth,110,cbWidth,20);

        final int fontSize = 16;
        shapeCB1.setFont(new Font("ＭＳ ゴシック", Font.BOLD, fontSize));   this.add(shapeCB1);   shapeCB1.addItemListener(this);
        shapeCB2.setFont(new Font("ＭＳ ゴシック", Font.BOLD, fontSize));   this.add(shapeCB2);   shapeCB2.addItemListener(this);
        shapeCB3.setFont(new Font("ＭＳ ゴシック", Font.BOLD, fontSize));   this.add(shapeCB3);   shapeCB3.addItemListener(this);
        shapeCB4.setFont(new Font("ＭＳ ゴシック", Font.BOLD, fontSize));   this.add(shapeCB4);   shapeCB4.addItemListener(this);

        setLayout(null);

        pm = new PaintManager();
    }
    public void itemStateChanged(ItemEvent e) {
        Checkbox ch = (Checkbox)e.getItemSelectable();
        String cmd = ch.getLabel();

        if(cmd.equals("丸"))     {pm.SetPaintMode(PaintManager.PaintMode.DOT);repaint();System.out.println("Mode Change:丸");}
        if(cmd.equals("円"))     {pm.SetPaintMode(PaintManager.PaintMode.CIRCLE);repaint();System.out.println("Mode Change:円");}
        if(cmd.equals("四角"))   {pm.SetPaintMode(PaintManager.PaintMode.RECT);repaint();System.out.println("Mode Change:四角");}
        if(cmd.equals("線"))     {pm.SetPaintMode(PaintManager.PaintMode.LINE);repaint();System.out.println("Mode Change:線");}
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("backBlack"))    {setBackground(Color.BLACK); repaint();}
        if (cmd.equals("backWhite"))    {setBackground(Color.WHITE); repaint();}
        if (cmd.equals("backRed"))      {setBackground(Color.RED); repaint();}
        if (cmd.equals("backBlue"))     {setBackground(Color.BLUE); repaint();}
        if (cmd.equals("backGreen"))    {setBackground(Color.GREEN); repaint();}

        if (cmd.equals("paintBlack"))       pm.SetPaintColor(PaintManager.PaintColor.BLACK);
        if (cmd.equals("paintWhite"))       pm.SetPaintColor(PaintManager.PaintColor.WHITE);
        if (cmd.equals("paintRed"))         pm.SetPaintColor(PaintManager.PaintColor.RED);
        if (cmd.equals("paintBlue"))        pm.SetPaintColor(PaintManager.PaintColor.BLUE);
        if (cmd.equals("paintGreen"))       pm.SetPaintColor(PaintManager.PaintColor.GREEN);
        if (cmd.equals("paintGray"))        pm.SetPaintColor(PaintManager.PaintColor.GRAY);
        if (cmd.equals("paintGradation"))   pm.SetPaintColor(PaintManager.PaintColor.GRADATION);

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
        obj  = null;
        if(e.getButton() == MouseEvent.BUTTON1) {
            x = e.getX();
            y = e.getY();
            obj = pm.GetObject();
            obj.moveto(x, y);
            repaint();
        }
    }
    @Override public void mouseReleased(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1) {
            x = e.getX();
            y = e.getY();
            if(pm.GetPaintMode() == PaintManager.PaintMode.DOT) {
                obj.moveto(x,y);
            } else if(pm.GetPaintMode() == PaintManager.PaintMode.CIRCLE){
                obj.setWH(x - obj.x, y - obj.y);
            }
            if(obj != null){
                objList.add(obj);
            }
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
        if(pm.GetPaintMode() == PaintManager.PaintMode.DOT){
            obj.moveto(x,y);
//            objList.add(obj);
        }else if(pm.GetPaintMode() == PaintManager.PaintMode.CIRCLE){
            obj.setWH(x - obj.x,y - obj.y);
        }
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





