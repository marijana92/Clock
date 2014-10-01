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
 *Uključujemo interface Runnable radi niti. Zato što se nešto deševa, svaki sekund imamo neku promjenu.
 * 
 * @author Marijana
 */
public class BoardDigital extends JPanel implements Runnable
{
     /*
    Sat radi na principu niti jer se svake sekunde desi promjena.
    */
    Thread thread = null;
    SimpleDateFormat formatter = new SimpleDateFormat("s", Locale.getDefault());
    Date currentDate;
    
    /**
     * Dogadja koji se desava kad je pokrenuto izvrsavanje niti.
     */
    @Override
    public void run() 
    {
        while (thread != null) 
        {
            try 
            {
                Thread.sleep(1000);//stopiramo izvrsavanje programa za 1 sekundu
                repaint(); //ponovno iscrtavanje, idemo na metodu paint
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(BoardDigital.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        thread = null;
    }
    
    /**
     * Funkcija startuje izvrsavanje niti za klasu
     * @see BoardAnalog
     */
    public void start() 
    {
        if (thread == null) 
        {
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Funkcija postavlja vrijednost niti
     * @see #thread na null.
     */
    public void stop() 
    {
        thread = null;
    }
    
    /**
     * Funkcija kreira osnovnu strukturu sata.
     * Postavlja boju, crta oblik, i postvlja cetri broja, 3, 6, 9 i 12.
     * 
     * @param g objekat klase koji u sebi sadrzi funkcije za crtanje
     */
    @Override
    public void paint(Graphics g)
    {
        int second, minute, hour;
        
        super.paint(g);
        
        currentDate = new Date();//automacki učitavnaje lokalnog vremena
        
        formatter.applyPattern("s"); //primjenjivanje formatera za sekunde
        second = Integer.parseInt(formatter.format(currentDate));
        
        formatter.applyPattern("m"); //primjenjivanje formatera za minute
        minute = Integer.parseInt(formatter.format(currentDate));
        
        formatter.applyPattern("h"); //primjenjivanje formatera za sate
        hour = Integer.parseInt(formatter.format(currentDate));
        
        Dimension d = getSize(); //uzimamo trenutne dimenzije naseg prozora od programa
        FontMetrics fm = g.getFontMetrics(); //uzimamo metriku od fonta
        String time = hour + ":" + minute + ":" + second; //formiramo string koji cemo ispisati
        
        g.setColor(new Color(0, 165, 255)); //postavljamo boju stringa
        g.drawString(time, d.width/2 - fm.stringWidth(time) / 2, d.height/2 + fm.getDescent()); //ispisujemo string na sredinu ekrana

        // Sinhronizovanje sa grafičkom kartom
        Toolkit.getDefaultToolkit().sync();

        // Optimizacija upotrebe RAM-a, 
        g.dispose();
    }
    
}
