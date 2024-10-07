package project;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConwaysGameOfLife extends JFrame implements ActionListener {
    private static final Dimension default_size = new Dimension(800, 600);
    private final JMenuItem exit;
    private final JMenuItem save;
    private final JMenuItem load;
    private final JMenuItem play;
    private final JMenuItem stop;
    private final JMenuItem reset;
    private final Listeners listeners;
    private Thread game;

    /**
     * Összereakja a menüt, hozzáadja a Listenereket.
     * Elhelyezi az ablakot, benne a penellel amire a cellák lesznek kirajzolva
     */
    public ConwaysGameOfLife() {
        JMenuBar mb_menu = new JMenuBar();
        setJMenuBar(mb_menu);
        JMenu m_file = new JMenu("File");
        mb_menu.add(m_file);
        JMenu m_game = new JMenu("Game");
        mb_menu.add(m_game);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        load = new JMenuItem("Load");
        load.addActionListener(this);
        m_file.add(exit);
        m_file.add(save);
        m_file.add(load);
        play = new JMenuItem("Play");
        play.addActionListener(this);
        stop = new JMenuItem("Stop");
        stop.addActionListener(this);
        reset = new JMenuItem("Reset");
        reset.addActionListener(this);
        m_game.add(play);
        m_game.add(stop);
        m_game.add(reset);
        listeners = new Listeners();
        add(listeners);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game of Life");
        setSize(default_size);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        setVisible(true);
    }
    /**
     * Elindítja/Megállítja az autómatát
     * @param isBeingPlayed Az automata ki/be kapcsolója.
     */
    public void setGameBeingPlayed(boolean isBeingPlayed)  {
        if (isBeingPlayed) {
            game = new Thread(listeners);
            game.start();
        } else {
            game.interrupt();
        }
    }
    /**
     * actionPerformed Felüldefiniálás
     * A menü elemeit kapcsolja össze a mögöttük álló művelettel
     * @param ae ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(exit)) {
            System.exit(0);
        }  else if (ae.getSource().equals(reset)) {
            listeners.resetBoard();
            listeners.repaint();
        } else if (ae.getSource().equals(play)) {
                setGameBeingPlayed(true);
        } else if (ae.getSource().equals(stop)) {
                setGameBeingPlayed(false);
        } else if (ae.getSource().equals(save)) {
            try {
                listeners.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource().equals(load)) {
            try {
                listeners.load();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}