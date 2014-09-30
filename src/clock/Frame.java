package clock;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Marijana
 */
public class Frame extends JFrame 
{
    private BoardAnalog boardAnalog;
    private BoardDigital boardDigital;
    
    /**
     * Osnovni konstruktor za kreiranje glavnog prozora.
     * Kreira satove
     * @see #boardAnalog analogni sat
     * @see #boardDigital digitalni sat.
     * 
     * Postavlja se odmah na pocetnu analogni sat.
     */
    public Frame() 
    {
        /*
        Kreiranje i dodavanje analognog sata na frame.
        */
        boardAnalog = new BoardAnalog();
        add(boardAnalog);
        
        boardDigital = new BoardDigital();
        
        MenuBar myMenu = new MenuBar(boardAnalog, boardDigital);
        /*
        Postavljanje meni na frame-u.
        */
        this.setJMenuBar(myMenu.getMenuBar());
        
        frameSettings();
        
        myMenu.setFrameMain(this);
        
        boardAnalog.start();
    }

    /**
     * Funkcija vrsi inicijalizaciju osnovnih parametara prozora.
     */
    private void frameSettings() 
    {
        this.setTitle("Clock");
        /*
        Program se uobičajno zatvara na dugme X.
        */
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true); //korisnik moze prozoru da mjenja velicinu
        this.setType(Type.NORMAL);

        Dimension dimensionWindow = new Dimension(400, 400);

        this.setSize(400, 400); //postavljamo osnovnu velicinu prozora
        this.setMinimumSize(new Dimension(400, 400)); //postavljamo minimalnu velicinu prozora

        /*
        Pristupa se nekim osnovnim svojstvima same mašine na kome izvršava program.
        */
        Toolkit tkDimension = Toolkit.getDefaultToolkit();
        Dimension dimensionScrean = tkDimension.getScreenSize(); //uzimaju se dužina i širina ekrana radi postavljanja prozora na sredinu ekrana

        this.setLocation(dimensionScrean.width / 2 - dimensionWindow.width / 2,
                dimensionScrean.height / 2 - dimensionWindow.height / 2);

        this.pack();
        /*
        Postavljamo ikonicu prozora.
        */
        this.setIconImage(new ImageIcon("src/Content/clock.png").getImage());
        this.setVisible(true);
    }

}
