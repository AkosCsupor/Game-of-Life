package project;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.awt.*;
import java.io.IOException;
public class GameTest {
	 ConwaysGameOfLife Gameplay;
    Listeners listener;
    Backup mentesek;
    Main main;
   @Before
   public void setup() {
        Gameplay = new ConwaysGameOfLife();
        listener =new Listeners();
        mentesek =new Backup( "kacsa","testsave.txt");
        main =new Main();
   }

   @Test
   public void testbackupload() throws IOException, ClassNotFoundException {
       mentesek.save();
       assertEquals("kacsa",mentesek.load());
   }
   @Test
   public void testbackupload3() throws IOException, ClassNotFoundException {
       mentesek.save();
       assertNotSame("duck", mentesek.load());
   }
   @Test
   public void testbackdimension_set() throws IOException, ClassNotFoundException {
       listener.set_d_gameBoardSize(new Dimension(5,5) );
       assertEquals(5, listener.dimenzio.height);
       assertNotEquals(4, listener.dimenzio.width);
   }
   @Test
   public void testresetboard() throws IOException, ClassNotFoundException {
       listener.addPoint(1,1);
       listener.resetBoard();
       assertTrue(listener.points.isEmpty());
   }

   @Test
   public void testbackupload4() throws IOException, ClassNotFoundException {
       mentesek.save();
       assertNotSame("duck", mentesek.load());
   }
   @Test
   public void testaddpoint() throws IOException, ClassNotFoundException {
       listener.addPoint(1,1);
       assertFalse(listener.points.isEmpty());
   }
   @Test
   public void testmain()  {
       main.main(new String[0]);
       assertNotNull(main);
   }
   @Test
   public void testremovepoint() throws IOException, ClassNotFoundException {
       listener.addPoint(1,1);
       listener.removePoint(1,1);
       assertTrue(listener.points.isEmpty());
   }
}
