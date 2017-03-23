import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.io.*; 
import java.util.*;

public class painter extends JFrame implements MouseListener,MouseMotionListener 
{ 
  int x = 0; 
  int y = 0; 
  int xb = 0; 
  int yb = 0; 
  int pen = 1; 
  int px = 0;
  int py = 0;
  boolean ispress = false; 
  boolean isNew = true; 
  int setcolor = 1; 
  Color C; 
  private JButton b1,b2,b3,b4,b5,b6; 

  public painter() 
  { 
    addWindowListener(
      new WindowAdapter()
      { 
        public void windowClosing(WindowEvent e) { System.exit(0); }
      }
    ); 

    Container container1 = getContentPane(); 
    container1.addMouseListener(this); 
    container1.addMouseMotionListener(this); 
    //JPanel p1 = new JPanel(); 
  } 

  public void mousePressed(MouseEvent e) 
  { 
    xb = x;
    yb = y;
    x = e.getX(); 
    y = e.getY(); 
    ispress = true; 
    setTitle("x:"+x + " y:" + y); 
    repaint(); 
  } 

  public void mouseReleased(MouseEvent e) 
  { 
    xb = x;
    yb = y;
    x = e.getX(); 
    y = e.getY(); 
    ispress = false; 
    setTitle("x:"+x + " y:" + y); 
    repaint(); 
  } 

  public void mouseEntered(MouseEvent e) 
  { } 

  public void mouseExited(MouseEvent e) 
  { } 

  public void mouseClicked(MouseEvent e) 
  { } 

  public void mouseMoved(MouseEvent e) 
  { 
    xb = x;
    yb = y;
    x = e.getX(); 
    y = e.getY(); 
    setTitle("x:"+x + " y:" + y); 
    repaint(); 
  } 

  public void mouseDragged(MouseEvent e) 
  { 
    xb = x;
    yb = y;
    x = e.getX(); 
    y = e.getY(); 
    setTitle("x:"+x + " y:" + y); 
    repaint(); 
  } 

  private Color grayColor(int gray)
  {
    int RV=299, GV=587, BV=114;
    int r = 0;
    int g = 0;
    int b = 0;
    int tmprgb = 0;
    int tmpgray = 0;
    int tmp = 0;
    int rgbmax = 0;
    Random rand = new Random();

    if(gray > 255 || gray < 0)
    {
      gray = 128;
    }
    
    r = rand.nextInt(256);
    g = rand.nextInt(256);
    b = rand.nextInt(256);
          
    tmprgb = r*RV + g*GV + b*BV;
    tmpgray = gray*1000;
    if(tmprgb != tmpgray)
    {
      r = r *tmpgray/tmprgb;
      g = g *tmpgray/tmprgb;
      b = b *tmpgray/tmprgb;

      r = r&0xff; //  max 255
      g = g&0xff; //  max 255
      b = b&0xff; //  max 255

      tmpgray = (gray*1000) - (r*RV + g*GV + b*BV);

      if(r > g)
      { rgbmax = r; }
      else
      { rgbmax = g; }

      if(rgbmax > b)
      {}
      else
      { rgbmax = b; }

// increases equally 
      tmp = tmpgray/1000;
      if(rgbmax+tmp > 255)
      { tmp = 255 - rgbmax; }
      r += tmp;
      g += tmp;
      b += tmp;
      tmpgray = tmpgray - (tmp*1000);

      tmp = tmpgray/GV;

/////// tmp +g > 255 -> g = 255d
      if((tmp > 0))
      { 
        if(g + tmp > 255)
        { tmp = 255 - g;  }

        tmpgray -= tmp*GV;
        g += tmp;
      }

      tmp = tmpgray/RV;
      if((tmp > 0))
      { 
        if(r + tmp > 255)
        { tmp = 255 - r;  }
        tmpgray -= tmp*RV;
        r += tmp;
      }

      tmp = tmpgray/BV;
      if((tmp > 0))
      { 
        if(b + tmp > 255)
        { tmp = 255 - b;  }
        tmpgray -= tmp*BV;
        b += tmp;
      }
    }
    return new Color(r, g, b);
  }

  public void paint(Graphics g) { //滑鼠左鍵要按下才會畫 
    
    if(ispress)
    { 
      int width = 10;
      if((Math.abs(x-px) > (width)) || (Math.abs(y-py) > (width)))
      {
        px = x;
        py = y;
        Insets iii = getInsets();
    
        Random r = new Random();
        //Color ccc = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
        Color ccc = grayColor(r.nextInt(20)+100);
        g.setColor(ccc);
        //g.setColor(Color.black); 
        //g.fillOval(x+5,y+30, width, width);//以橢圓代替畫筆 
        //g.fillOval(x+iii.left,y+iii.top, width, width);//以橢圓代替畫筆 
        g.fillRect(x+iii.left,y+iii.top, width, width);
        //g.fillRect(x+iii.left+width,y+iii.top+width, width, width);
        g.fillRect(x+iii.left+r.nextInt(width*4)-(width*2),y+iii.top+r.nextInt(width*4)-(width*2), width, width);
        //g.fillOval(x+iii.left+width,y+iii.top+width, width, width);//以橢圓代替畫筆 
        //g.drawLine(x+7, y+37, xb+7, yb+37);     
        
      }
    } 

    if(isNew)
    //if(false)
    {
      isNew = false;

      Random r = new Random();      
      int max = 600;
      int maxcolor = 250;
      int mincolor = 100;
      int width = 12;
      for(int i = 0; i < max; i=i+(width))
      {       
        for(int j = 0; j < max; j=j+(width))
        {
//          Color ccc = new Color(r.nextInt(maxcolor), r.nextInt(maxcolor), r.nextInt(maxcolor));
          //Color ccc = new Color(r.nextInt(maxcolor-mincolor)+mincolor, r.nextInt(maxcolor-mincolor)+mincolor, r.nextInt(maxcolor-mincolor)+mincolor);
          //Color ccc = new Color(r.nextInt(50)+mincolor, r.nextInt(50)+mincolor, r.nextInt(50)+mincolor);
          Color ccc = grayColor(r.nextInt(maxcolor-mincolor)+mincolor);
          //Color ccc = grayColor(200);
          g.setColor(ccc);
          //g.fillOval(i,j, width, width);//以橢圓代替畫筆 
          g.fillRect(i,j, width, width);
        }
      }

      // random background again
      if(true)
      //if(false)
      {
        for(int i = 0; i < max; i=i+1)
        {       
          //Color ccc = new Color(r.nextInt(maxcolor-mincolor)+mincolor, r.nextInt(maxcolor-mincolor)+mincolor, r.nextInt(maxcolor-mincolor)+mincolor);
          Color ccc = grayColor(r.nextInt(maxcolor-mincolor)+mincolor);
          //Color ccc = grayColor(200);
          g.setColor(ccc);
          //g.fillOval(i,j, width, width);//以橢圓代替畫筆 
          g.fillRect(r.nextInt(600), r.nextInt(600), width, width);
        }
      }

    }
  } 

  public static void main(String[] args) 
  {   
    painter frame = new painter(); 
    BorderLayout br = new BorderLayout(); 
    frame.setLayout(br);
    frame.setTitle("TEST2"); 
    frame.setSize(600, 600); 
    frame.setLocation(300,300); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    frame.setVisible(true); 

  } 

} 
