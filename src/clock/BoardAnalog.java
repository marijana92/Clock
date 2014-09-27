package clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Marijana
 */

/*
Uključujemo interface Runnable radi niti. Zato što se nešto deševa, svaki sekund imamo neku promjenu.
*/

public class BoardAnalog extends JPanel implements Runnable
{
    /*
    Sat radi na principu niti jer se svake sekunde desi promjena.
    */
    Thread thread = null;
    SimpleDateFormat formatter = new SimpleDateFormat("s", Locale.getDefault());
    Date currentDate;
    int xcenter = 175, ycenter = 175;
    
    public void start() 
    {
        if (thread == null) 
        {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() 
    {
        thread = null;
    }
    
    /**
     *
     */
    @Override
    public void run() 
    {
        while (thread != null) 
        {
            try 
            {
                Thread.sleep(1000);
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(BoardAnalog.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            repaint();
        }
        thread = null;
    }
    
    @Override
    public void update(Graphics g)
    {
        paint(g);
    }
    
    private void drawStructure(Graphics g)
    {
        g.setFont(new Font("Kristen ITC", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.fillOval(xcenter - 150, ycenter - 150, 300, 300);
        
        g.setColor(Color.RED);
        g.setFont(new Font("Kristen ITC", Font.BOLD, 25));
        g.drawString("12", xcenter - 10, ycenter - 130);
        g.drawString("3", xcenter + 135, ycenter + 10);
        g.drawString("6", xcenter - 10, ycenter + 145);
        g.drawString("9", xcenter - 145, ycenter + 10);
    }
    
    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);

        int xhour, yhour, xminute, yminute, xsecond, ysecond, second, minute, hour;
        drawStructure(g);

        currentDate = new Date(); //automacki učitavnaje lokalnog vremena
        
        formatter.applyPattern("s"); //primjenjivanje formatera za sekunde
        second = Integer.parseInt(formatter.format(currentDate));
        
        formatter.applyPattern("m");
        minute = Integer.parseInt(formatter.format(currentDate));
        
        formatter.applyPattern("h");
        hour = Integer.parseInt(formatter.format(currentDate));
        
        xsecond = (int) (Math.cos(second * 3.14f / 30 - 3.14f / 2) * 120 + xcenter);
        ysecond = (int) (Math.sin(second * 3.14f / 30 - 3.14f / 2) * 120 + ycenter);
        
        xminute = (int) (Math.cos(minute * 3.14f / 30 - 3.14f / 2) * 100 + xcenter);
        yminute = (int) (Math.sin(minute * 3.14f / 30 - 3.14f / 2) * 100 + ycenter);
        
        xhour = (int) (Math.cos((hour * 30 + minute / 2) * 3.14f / 180 - 3.14f
                / 2) * 80 + xcenter);
        yhour = (int) (Math.sin((hour * 30 + minute / 2) * 3.14f / 180 - 3.14f
                / 2) * 80 + ycenter);
        
        g.setColor(Color.magenta);
        g.drawLine(xcenter, ycenter, xsecond, ysecond); //iscrtavanje sekundarice

        g.setColor(Color.red);
        g.drawLine(xcenter, ycenter - 1, xminute, yminute);

        g.setColor(Color.green);
        g.drawLine(xcenter, ycenter - 1, xhour, yhour);

        // Sinhronizovanje sa grafičkom kartom
        Toolkit.getDefaultToolkit().sync();

        // Optimizacija upotrebe RAM-a
        g.dispose();
    }
    
}
