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
    
    public Frame() 
    {
        boardAnalog = new BoardAnalog();
        add(boardAnalog);
        
        boardDigital = new BoardDigital();
        
        MenuBar myMenu = new MenuBar(boardAnalog, boardDigital);
        this.setJMenuBar(myMenu.getMenuBar());
        
        frameSettings();
        
        myMenu.setFrameMain(this);
        
        boardAnalog.start();
    }

    private void frameSettings() 
    {
        this.setTitle("Clock");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setType(Type.NORMAL);

        Dimension dimensionWindow = new Dimension(400, 400);

        this.setSize(500, 400);
        this.setMinimumSize(new Dimension(400, 400));

        Toolkit tkDimension = Toolkit.getDefaultToolkit();
        Dimension dimensionScrean = tkDimension.getScreenSize();

        this.setLocation(dimensionScrean.width / 2 - dimensionWindow.width / 2,
                dimensionScrean.height / 2 - dimensionWindow.height / 2);

        this.pack();
        this.setIconImage(new ImageIcon("src/Content/clock.png").getImage());
        this.setVisible(true);
    }

}
