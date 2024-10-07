package project;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class GameBoardTest {

    private static final String TEST_FILENAME = "testGOfL.txt";

    private GameBoard gameBoard;

    @Before
    public void setUp() {
        gameBoard = new GameBoard();
        gameBoard.set_d_gameBoardSize(new Dimension(10, 10));
    }
 
    @Test
    public void testAddPoint() {
        gameBoard.addPoint(5, 5);
        assertTrue(gameBoard.points.contains(new Point(5, 5)));
    }

    
    @Test
    public void testSaveWithPoints() throws IOException, ClassNotFoundException {
        // Add points to the game board
        gameBoard.addPoint(2, 2);
        gameBoard.addPoint(3, 3);

        // Save the state
        gameBoard.save();

        // Create a new GameBoard without points and load the state
        GameBoard newGameBoard = new GameBoard();
        newGameBoard.set_d_gameBoardSize(new Dimension(10, 10));
        newGameBoard.load();

        // Check if the loaded state matches the original state
        assertEquals(gameBoard.points, newGameBoard.points);
    }
    @Test
    public void testLoadWithPoints() throws IOException, ClassNotFoundException {
        // Add points to the game board
        gameBoard.addPoint(2, 2);
        gameBoard.addPoint(3, 3);

        // Save the state
        gameBoard.save();

        // Create a new GameBoard with empty points and load the state
        GameBoard newGameBoard = new GameBoard();
        newGameBoard.set_d_gameBoardSize(new Dimension(10, 10));
        newGameBoard.load();

        // Check if the loaded state matches the original state
        assertEquals(gameBoard.points, newGameBoard.points);
    }
    
    @Test
    public void testSaveWithoutPoints() throws IOException, ClassNotFoundException {
        // Save the state without adding points
        gameBoard.save();

        // Create a new GameBoard without points and load the state
        GameBoard newGameBoard = new GameBoard();
        newGameBoard.set_d_gameBoardSize(new Dimension(10, 10));
        newGameBoard.load();

        // Check if the loaded state with empty points matches the original state
        assertEquals(gameBoard.points, newGameBoard.points);
    }
    
    @Test
    public void testLoadWithoutPoints() throws IOException, ClassNotFoundException {
        // Save the state without adding points
        gameBoard.save();

        // Create a new GameBoard without points and load the state
        GameBoard newGameBoard = new GameBoard();
        newGameBoard.set_d_gameBoardSize(new Dimension(10, 10));
        newGameBoard.load();

        // Check if the loaded state with empty points matches the original state
        assertEquals(gameBoard.points, newGameBoard.points);
    }
    
    @Test
    public void testRemovePoint() {
        gameBoard.addPoint(3, 3);
        gameBoard.removePoint(3, 3);
        assertFalse(gameBoard.points.contains(new Point(3, 3)));
    }

    @Test
    public void testResetBoard() {
        gameBoard.addPoint(4, 4);
        gameBoard.resetBoard();
        assertTrue(gameBoard.points.isEmpty());
    }


}
