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
 *Uključujemo interface Runnable radi niti. Zato što se nešto deševa, svaki sekund imamo neku promjenu.
 * 
 * @author Marijana
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
    
    /**
     * Funkcija startuje izvrsavanje niti za klasu
     * @see BoardAnalog
     */
    public void start() 
    {
        if (thread == null) 
        {
            thread = new Thread(this); //kreiramo nit
            thread.start(); //startujemo izvrsavanje niti
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
     * Dogadja koji se desava kad je pokrenuto izvrsavanje niti.
     */
    @Override
    public void run() 
    {
        while (thread != null) 
        {
            try 
            {
                Thread.sleep(1000); //stopiramo izvrsavanje programa za 1 sekundu
                repaint(); //ponovno iscrtavanje, idemo na metodu paint
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(BoardAnalog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        thread = null;
    }
    
    /**
     * Funkcija kreira osnovnu strukturu sata.
     * Postavlja boju, crta oblik, i postvlja cetri broja, 3, 6, 9 i 12.
     * 
     * @param g objekat klase koji u sebi sadrzi funkcije za crtanje
     */
    private void drawStructure(Graphics g)
    {
        super.paint(g);
        
        g.setColor(Color.BLACK); //postavljamo boju pozadine
        g.fillOval(xcenter - 150, ycenter - 150, 300, 300); //crtamo krug na poziciji 25, 25 sa duzinom i visinom 300
        
        g.setColor(Color.RED); //postavljamo boju sloba
        g.setFont(new Font("Kristen ITC", Font.BOLD, 25)); //font slova
        g.drawString("12", xcenter - 10, ycenter - 130); //ispisujemo 12
        g.drawString("3", xcenter + 135, ycenter + 10);  //ispisujemo 3
        g.drawString("6", xcenter - 10, ycenter + 145);  //ispisujemo 6
        g.drawString("9", xcenter - 145, ycenter + 10);  //ispisujemo 9
    }
    
    /**
     * Funkcija na osnovu trenutnog vrmena racuna pozicije x i y koordinata za kazaljke 
     * i sa tim koordinatama crta liniju.
     * 
     * @param g objekat klase koji u sebi sadrzi funkcije za crtanje
     */
    @Override
    public void paint(Graphics g) 
    {
        int xhour, yhour, xminute, yminute, xsecond, ysecond, second, minute, hour;
        drawStructure(g);

        currentDate = new Date(); //automacki učitavnaje lokalnog vremena
        
        formatter.applyPattern("s"); //primjenjivanje formatera za sekunde
        second = Integer.parseInt(formatter.format(currentDate));
        
        formatter.applyPattern("m"); //primjenjivanje formatera za minute
        minute = Integer.parseInt(formatter.format(currentDate));
        
        formatter.applyPattern("h"); //primjenjivanje formatera za sate
        hour = Integer.parseInt(formatter.format(currentDate));
        
        
        /*
        Racunanje x i y koordinata za kazaljke
        */
        xsecond = (int) (Math.cos(second * 3.14f / 30 - 3.14f / 2) * 120 + xcenter);
        ysecond = (int) (Math.sin(second * 3.14f / 30 - 3.14f / 2) * 120 + ycenter);
        
        xminute = (int) (Math.cos(minute * 3.14f / 30 - 3.14f / 2) * 100 + xcenter);
        yminute = (int) (Math.sin(minute * 3.14f / 30 - 3.14f / 2) * 100 + ycenter);
        
        xhour = (int) (Math.cos((hour * 30 + minute / 2) * 3.14f / 180 - 3.14f
                / 2) * 80 + xcenter);
        yhour = (int) (Math.sin((hour * 30 + minute / 2) * 3.14f / 180 - 3.14f
                / 2) * 80 + ycenter);
        
        g.setColor(Color.magenta); //boja sekundarice
        g.drawLine(xcenter, ycenter, xsecond, ysecond); //iscrtavanje sekundarice

        g.setColor(Color.red); //boja kazaljke za minute
        g.drawLine(xcenter, ycenter - 1, xminute, yminute); //iscrtavanje kazaljke za minute

        g.setColor(Color.green); //boja kazaljke za sate
        g.drawLine(xcenter, ycenter - 1, xhour, yhour); //iscrtavanje kazaljke za sate

        // Sinhronizovanje sa grafičkom kartom
        Toolkit.getDefaultToolkit().sync();

        // Optimizacija upotrebe RAM-a
        g.dispose();
    }
}
