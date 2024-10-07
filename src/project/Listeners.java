package project;

import java.awt.*;
import java.awt.event.*;

public class Listeners extends GameBoard implements ComponentListener, MouseListener, MouseMotionListener {
    /**
     * Konstruktor ami integrálja a Listenereket
     */
    public Listeners() {
        addComponentListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    /**
     * Ablak átméretezés esetén frissíti a mentett méreteket
     * @param e ComponentEvent
     */
    @Override
    public void componentResized(ComponentEvent e) {
        set_d_gameBoardSize(new Dimension(getWidth() / cell_size - 2 , getHeight() / cell_size - 2 ));
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
    /**
     * Egérrel való rajzolást teszi lehetővé kattintással
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        addPoint(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    /**
     * Egérrel való rajzolást teszi lehetővé egér húzásával
     * @param e MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        addPoint(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
