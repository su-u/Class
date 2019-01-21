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
    private ArrayList<Figure> undoList;
    private Figure obj;
    private static int visibleCount = 0;
    private BufferedImage img;
    private Graphics imgBuffer;
    private static final int width = 1280;
    private static final int height = 720;

    CheckboxGroup shapeCBG;
    Checkbox shapeCB1, shapeCB2, shapeCB3, shapeCB4,shapeCB5;
    Button endButton;

    CheckboxGroup fillModeCBG;
    Checkbox fillModeCB1,fillModeCB2;

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
        undoList = new ArrayList<Figure>();
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
        shapeCB3 = new Checkbox("四角", shapeCBG,false);
        shapeCB4 = new Checkbox("線", shapeCBG,false);
        shapeCB5 = new Checkbox("ペン", shapeCBG, false);

        fillModeCBG = new CheckboxGroup();
        fillModeCB1 = new Checkbox("unfill",fillModeCBG,true);
        fillModeCB2 = new Checkbox("fill",fillModeCBG,false);

        final int cbWidth = 80;


        shapeCB1.setBounds(Paint.width - cbWidth,50,cbWidth,20);
        shapeCB2.setBounds(Paint.width - cbWidth,70,cbWidth,20);
        shapeCB3.setBounds(Paint.width - cbWidth,90,cbWidth,20);
        shapeCB4.setBounds(Paint.width - cbWidth,110,cbWidth,20);
        shapeCB5.setBounds(Paint.width - cbWidth, 130, cbWidth, 20);

        fillModeCB1.setBounds(Paint.width - cbWidth, 180,cbWidth,20);
        fillModeCB2.setBounds(Paint.width - cbWidth, 200,cbWidth,20);

        final int fontSize = 16;
        final Font checkBoxFont = new Font("ＭＳ ゴシック", Font.BOLD, fontSize);

        shapeCB1.setFont(checkBoxFont);   this.add(shapeCB1);   shapeCB1.addItemListener(this);
        shapeCB2.setFont(checkBoxFont);   this.add(shapeCB2);   shapeCB2.addItemListener(this);
        shapeCB3.setFont(checkBoxFont);   this.add(shapeCB3);   shapeCB3.addItemListener(this);
        shapeCB4.setFont(checkBoxFont);   this.add(shapeCB4);   shapeCB4.addItemListener(this);
        shapeCB5.setFont(checkBoxFont);   this.add(shapeCB5);   shapeCB5.addItemListener(this);

        fillModeCB1.setFont(checkBoxFont);   this.add(fillModeCB1);   fillModeCB1.addItemListener(this);
        fillModeCB2.setFont(checkBoxFont);   this.add(fillModeCB2);   fillModeCB2.addItemListener(this);

        setLayout(null);

        pm = new PaintManager();
    }
    public void itemStateChanged(ItemEvent e) {
        Checkbox ch = (Checkbox)e.getItemSelectable();
        String cmd = ch.getLabel();

        if(cmd.equals("丸"))     {pm.setPaintMode(PaintManager.PaintMode.DOT);repaint();System.out.println("Mode Change:丸");}
        if(cmd.equals("円"))     {pm.setPaintMode(PaintManager.PaintMode.CIRCLE);repaint();System.out.println("Mode Change:円");}
        if(cmd.equals("四角"))   {pm.setPaintMode(PaintManager.PaintMode.RECT);repaint();System.out.println("Mode Change:四角");}
        if(cmd.equals("線"))     {pm.setPaintMode(PaintManager.PaintMode.LINE);repaint();System.out.println("Mode Change:線");}
        if(cmd.equals("ペン"))     {pm.setPaintMode(PaintManager.PaintMode.PEN);repaint();System.out.println("Mode Change:ペン");}

        if(cmd.equals("unfill"))    {pm.setUnFillMode();repaint();System.out.println("Mode Change:unfill");}
        if(cmd.equals("fill"))      {pm.setFillMode();repaint();System.out.println("Mode Change:fill");}
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("backBlack"))    {setBackground(Color.BLACK); repaint();}
        if (cmd.equals("backWhite"))    {setBackground(Color.WHITE); repaint();}
        if (cmd.equals("backRed"))      {setBackground(Color.RED); repaint();}
        if (cmd.equals("backBlue"))     {setBackground(Color.BLUE); repaint();}
        if (cmd.equals("backGreen"))    {setBackground(Color.GREEN); repaint();}

        if (cmd.equals("paintBlack"))       pm.setPaintColor(PaintManager.PaintColor.BLACK);
        if (cmd.equals("paintWhite"))       pm.setPaintColor(PaintManager.PaintColor.WHITE);
        if (cmd.equals("paintRed"))         pm.setPaintColor(PaintManager.PaintColor.RED);
        if (cmd.equals("paintBlue"))        pm.setPaintColor(PaintManager.PaintColor.BLUE);
        if (cmd.equals("paintGreen"))       pm.setPaintColor(PaintManager.PaintColor.GREEN);
        if (cmd.equals("paintGray"))        pm.setPaintColor(PaintManager.PaintColor.GRAY);
        if (cmd.equals("paintGradation"))   pm.setPaintColor(PaintManager.PaintColor.GRADATION);

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
            String value = JOptionPane.showInputDialog(frame, "ペイントサイズ(0 ~ 10000)", pm.getPaintSize());
            if(value != null && value != "") {
                pm.setPaintSize(Integer.parseInt(value));
                if (pm.getPaintSize() < 0) pm.setPaintSize(0);
                else if (pm.getPaintSize() > 10000) pm.setPaintSize(10000);
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
            obj = pm.getObject();
            if(obj != null){
                obj.moveto(x, y);
            }
            repaint();
        }
    }
    @Override public void mouseReleased(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1) {
            x = e.getX();
            y = e.getY();
            if(pm.getPaintMode() == PaintManager.PaintMode.DOT) {
                obj.moveto(x,y);
            } else if(pm.getPaintMode() == PaintManager.PaintMode.CIRCLE){
                obj.setWH(x - obj.x, y - obj.y);
            } else if(pm.getPaintMode() == PaintManager.PaintMode.PEN){
                objList.add(obj);
            }

            if(pm.getPaintMode() != PaintManager.PaintMode.DOT){
                objList.add(obj);
                obj = null;
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
        if(pm.getPaintMode() == PaintManager.PaintMode.DOT){
            obj.moveto(x,y);
//            objList.add(obj);
        }else if(
                pm.getPaintMode() == PaintManager.PaintMode.CIRCLE ||
                pm.getPaintMode() == PaintManager.PaintMode.RECT ||
                pm.getPaintMode() == PaintManager.PaintMode.LINE){
            obj.setWH(x - obj.x,y - obj.y);
        }else if(pm.getPaintMode() == PaintManager.PaintMode.PEN){
            obj = pm.getObject();
            obj.moveto(x, y);
            objList.add(obj);
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
            undoList.add(objList.get(objList.size() - 1));
            objList.remove(objList.size() - 1);
            System.out.println("--undo--");
            repaint();
        }
        if((e.getKeyCode() == KeyEvent.VK_Y) && (mod & InputEvent.CTRL_DOWN_MASK) != 0){
            if(undoList.size() <= 0)return;
            objList.add(undoList.get(undoList.size() - 1));
            undoList.remove(undoList.size() - 1);
            System.out.println("--redo--");
            repaint();
        }
    }

    @Override public void keyReleased(KeyEvent e){
    }

    @Override public void keyTyped(KeyEvent e){
    }

}





