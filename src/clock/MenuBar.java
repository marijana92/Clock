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
    
    /**
     * Konstruktor koji kreira menu sa chack boxovima za nalogni i digitalni sat.
     * 
     * @param boardAnalog analogni sat
     * @param boardDigital digitalni sat
     */
    public MenuBar(BoardAnalog boardAnalog, BoardDigital boardDigital)
    {
        /*
        Inicijalizacija privatnih parametara za satove.
        */
        this.setBoardAnalog(boardAnalog);
        this.setBoardDigital(boardDigital);

        menuBar = new JMenuBar();

        clockMenu = new JMenu("Choose clock"); //meni se naziva 'Choose clock

        jcbAnalog = new JCheckBoxMenuItem("Analog");
        jcbDigital = new JCheckBoxMenuItem("Digital");

        jcbAnalog.setSelected(true); //cekiramo analogni sat i dodajemo osluskivac
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

        /*
        DOdajemo redom u menu chack boxove.
        */
        clockMenu.add(jcbAnalog);
        clockMenu.add(jcbDigital);
        clockMenu.addSeparator(); //dodajemo separator
        clockMenu.add(jmiExit);

        this.menuBar.add(clockMenu); //dodajemo menu u menu bar
     }

    /**
     * Funkcija provjerava koji je chack box kliknut i na osnovu toga
     * postavlja analogni ili digitalni sat na glavni
     * @see #frameMain prozor.
     * 
     * @param e 
     */
    public void chackBoxClick(ActionEvent e)
    {
        if(((JCheckBoxMenuItem)e.getSource()).getText() == "Analog") //kliknut je analogni sat
        {
            getJcbAnalog().setSelected(true);
            getJcbDigital().setSelected(false);
            
            this.getBoardDigital().stop(); //stopiramo izvrsavanje niti za digitalni sat
            /*
            Sa glavnog prozora uklanjamo digitalni sat.
            */
            this.getFrameMain().remove(this.getBoardDigital());
            this.getFrameMain().invalidate(); //prozoru govorimo da je sve pobrisano nije validno
            
            this.getFrameMain().add(this.getBoardAnalog()); //na glavni prozor dodajemo analogni sat
            this.getFrameMain().validate(); //glavnom prozoru govorimo da je sve sto je dodatno iscrtano validno
            this.getBoardAnalog().start(); //startujemo nit za analogni sat
        }
        else
        {
            getJcbDigital().setSelected(true);
            getJcbAnalog().setSelected(false);
            
            this.getBoardAnalog().stop();//stopiramo izvrsavanje niti za analogni sat
            /*
            Sa glavnog prozora uklanjamo analogni sat.
            */
            this.getFrameMain().remove(this.getBoardAnalog());
            this.getFrameMain().invalidate(); //prozoru govorimo da je sve pobrisano nije validno
            
            this.getFrameMain().add(this.getBoardDigital()); //na glavni prozor dodajemo digitalni sat
            this.getFrameMain().validate(); //glavnom prozoru govorimo da je sve sto je dodato iscrtano validno
            this.getBoardDigital().start(); //startujemo nit za digitalni sat
        }
    }

    /**
     * Funkcija izlazi iz programa i stopira izvrsavanje obje niti za satove. 
     */
    public void exitClick()
    {
        this.getBoardAnalog().stop(); 
        this.getBoardDigital().stop();
        /*
        Izlazi se iz programa, kraj.
        */
        System.exit(0);
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
