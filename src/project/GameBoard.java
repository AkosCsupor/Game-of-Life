package project;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

public class GameBoard extends JPanel implements Runnable {
    protected int cell_size = 6;
    public Dimension dimenzio;
    public ArrayList<Point> points = new ArrayList<>();
    private final Backup mentesek = new Backup(points,"GOfL.txt");
    Random random = new Random();

    /**
     * Betölti a mentést és ki is rajzolja
     * @throws IOException,ClassNotFoundException hiba esetén
     */
    public void load() throws IOException, ClassNotFoundException {
        points = (ArrayList<Point>) mentesek.load();
        updateArraySize();
        repaint();
    }

    /**
     * Elkezd menteni
     * @throws IOException hiba esetén
     */
    public void save() throws IOException {
       mentesek.save();
    }

    /**
     * egy setter ami beállítja a vizsonyítási alapokat
     * @param dim beállítandó méret
     */
    public void set_d_gameBoardSize(Dimension dim) {
        dimenzio = dim;
        updateArraySize();
    }

    /**
     * Frissíti a megjelenő cellákat
     */
    private void updateArraySize() {
        ArrayList<Point> removeList = new ArrayList<>(0);
        for (Point current : points) {
            if ((current.x > dimenzio.width - 1) || (current.y > dimenzio.height - 1)) {
                removeList.add(current);
            }
        }
        points.removeAll(removeList);
        repaint();
    }

    /**
     * Megváltoztatja a kapott koordinátán lévő cella állapotát
     * @param x,y a cella koordinátája
     */
    public void addPoint(int x, int y) {
        if (!points.contains(new Point(x, y))) {
            points.add(new Point(x, y));
        }
        repaint();
    }

    /**
     * Megváltoztatja a kapott MouseEventen lévő cella állapotát
     * @param me MouseEvent
     */
    public void addPoint(MouseEvent me) {
        int x = me.getPoint().x / cell_size - 1;
        int y = me.getPoint().y / cell_size - 1;
        if ((x >= 0) && (x < dimenzio.width) && (y >= 0) && (y < dimenzio.height)) {
            if (!points.contains(new Point(x, y)))
                addPoint(x, y);
            else {
                removePoint(x, y);
            }
        }
    }

    /**
     * Törli a cellát
     * @param x,y a cella koordinátája
     */
    public void removePoint(int x, int y) {
        points.remove(new Point(x, y));
        repaint();
    }

    /**
     * Letakarítja az összes cellát
     */
    public void resetBoard() {
        points.clear();
    }

    /**
     * Megrajzolja a cellákat színekkel
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.black);
        try {
            for (Point newPoint : points) {
               g.setColor(/*Color.white*/ new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            	///g.setColor(/*Color.white*/ new Color(255, 0,0));
                g.fillRect(cell_size + (cell_size * newPoint.x), cell_size + (cell_size * newPoint.y), cell_size, cell_size);
            }
        } catch (ConcurrentModificationException ignored) {}
        g.setColor(Color.darkGray);
        for (int i = 0; i <= dimenzio.width; i++) {
            g.drawLine(((i * cell_size) + cell_size), cell_size, (i * cell_size) + cell_size, cell_size + (cell_size * dimenzio.height));
        }
        for (int i = 0; i <= dimenzio.height; i++) {
            g.drawLine(cell_size, ((i * cell_size) + cell_size), cell_size * (dimenzio.width + 1), ((i * cell_size) + cell_size));
        }
    }
    /**
     * Játék futása során történteket írja le
     */
    @Override
    public void run() {
        boolean[][] gameBoard = new boolean[dimenzio.width + 2][dimenzio.height + 2];
        for (Point current : points) {
            gameBoard[current.x + 1][current.y + 1] = true;
        }

        ArrayList<Point> survivingCells = new ArrayList<>(0);
        for (int i = 1; i < gameBoard.length - 1; i++) {
            for (int j = 1; j < gameBoard[0].length - 1; j++) {
                int szomszed = 0;
                if (gameBoard[i - 1][j - 1]) {
                    szomszed++;
                }
                if (gameBoard[i - 1][j]) {
                    szomszed++;
                }
                if (gameBoard[i - 1][j + 1]) {
                    szomszed++;
                }
                if (gameBoard[i][j - 1]) {
                    szomszed++;
                }
                if (gameBoard[i][j + 1]) {
                    szomszed++;
                }
                if (gameBoard[i + 1][j - 1]) {
                    szomszed++;
                }
                if (gameBoard[i + 1][j]) {
                    szomszed++;
                }
                if (gameBoard[i + 1][j + 1]) {
                    szomszed++;
                }
                if (gameBoard[i][j]) {
                    if ((szomszed == 2) || (szomszed == 3)) {
                        survivingCells.add(new Point(i - 1, j - 1));
                    }
                } else {
                    if (szomszed == 3) {
                        survivingCells.add(new Point(i - 1, j - 1));
                    }
                }
            }
        }
        resetBoard();
        points.addAll(survivingCells);
        repaint();
        try {
            int movesPerSecond = 10;
            Thread.sleep(1000 / movesPerSecond);
            run();
        } catch (InterruptedException ignored) {}
    }
}
