package clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Marijana
 */


public class MenuBar 
{
    private JMenuBar menuBar;
     
    private JCheckBoxMenuItem jcbAnalog;
    private JCheckBoxMenuItem jcbDigital;
    private JMenuItem jmiExit;
    
    private JMenu clockMenu;
    
    private BoardAnalog boardAnalog;
    private BoardDigital boardDigital;
    
    private Frame frameMain;
    
    public MenuBar(BoardAnalog boardAnalog, BoardDigital boardDigital)
    {
        this.setBoardAnalog(boardAnalog);
        this.setBoardDigital(boardDigital);

        menuBar = new JMenuBar();

        clockMenu = new JMenu("Choose clock");

        jcbAnalog = new JCheckBoxMenuItem("Analog");
        jcbDigital = new JCheckBoxMenuItem("Digital");

        jcbAnalog.setSelected(true);
        jcbAnalog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chackBoxClick(ae);
            }
        });

        jcbDigital.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chackBoxClick(ae);
            }
        });

        jmiExit = new JMenuItem("Exit");
        jmiExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                exitClick();
            }
        });

        clockMenu.add(jcbAnalog);
        clockMenu.add(jcbDigital);
        clockMenu.addSeparator();
        clockMenu.add(jmiExit);

        this.menuBar.add(clockMenu);
     }

    public void chackBoxClick(ActionEvent e)
    {
        
    }

    public void exitClick()
    {
        
    }
    
    /**
     * @return the menuBar
     */
    public JMenuBar getMenuBar() 
    {
        return menuBar;
    }

    /**
     * @param menuBar the menuBar to set
     */
    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    /**
     * @return the jcbAnalog
     */
    public JCheckBoxMenuItem getJcbAnalog() {
        return jcbAnalog;
    }

    /**
     * @param jcbAnalog the jcbAnalog to set
     */
    public void setJcbAnalog(JCheckBoxMenuItem jcbAnalog) {
        this.jcbAnalog = jcbAnalog;
    }

    /**
     * @return the jcbDigital
     */
    public JCheckBoxMenuItem getJcbDigital() {
        return jcbDigital;
    }

    /**
     * @param jcbDigital the jcbDigital to set
     */
    public void setJcbDigital(JCheckBoxMenuItem jcbDigital) {
        this.jcbDigital = jcbDigital;
    }

    /**
     * @return the frameMain
     */
    public Frame getFrameMain() {
        return frameMain;
    }

    /**
     * @param frameMain the frameMain to set
     */
    public void setFrameMain(Frame frameMain) {
        this.frameMain = frameMain;
    }

    /**
     * @return the boardAnalog
     */
    public BoardAnalog getBoardAnalog() {
        return boardAnalog;
    }

    /**
     * @param boardAnalog the boardAnalog to set
     */
    public void setBoardAnalog(BoardAnalog boardAnalog) {
        this.boardAnalog = boardAnalog;
    }

    /**
     * @return the boardDigital
     */
    public BoardDigital getBoardDigital() {
        return boardDigital;
    }

    /**
     * @param boardDigital the boardDigital to set
     */
    public void setBoardDigital(BoardDigital boardDigital) {
        this.boardDigital = boardDigital;
    }
}