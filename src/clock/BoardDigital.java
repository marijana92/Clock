package clock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
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


public class BoardDigital extends JPanel implements Runnable
{
    Thread thread = null;
    SimpleDateFormat formatter = new SimpleDateFormat("s", Locale.getDefault());
    Date currentDate;
    
    @Override
    public void run() 
    {
        while (thread != null) 
        {
            try 
            {
                Thread.sleep(1000);
                
                repaint();
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(BoardDigital.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        thread = null;
    }
    
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
    
    @Override
    public void paint(Graphics g)
    {
        int second, minute, hour;
        
        super.paint(g);
        
        currentDate = new Date();
        formatter.applyPattern("s");

        second = Integer.parseInt(formatter.format(currentDate));
        formatter.applyPattern("m");

        minute = Integer.parseInt(formatter.format(currentDate));
        formatter.applyPattern("h");
        hour = Integer.parseInt(formatter.format(currentDate));
        
        Dimension d = getSize();
        FontMetrics fm = g.getFontMetrics();
        String time = hour + ":" + minute + ":" + second;
        
        g.setColor(new Color(0, 165, 255));
        g.drawString(time, d.width/2 - fm.stringWidth(time) / 2, d.height/2 + fm.getDescent());

        // Sinhronizovanje sa grafičkom kartom
        Toolkit.getDefaultToolkit().sync();

        // Optimizacija upotrebe RAM-a, 
        g.dispose();
    }
    
}
