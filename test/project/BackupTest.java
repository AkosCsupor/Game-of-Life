package project;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BackupTest {

    private static final String TEMP_FILE = "tempBackupFile.ser";

    private Backup backup;

    @Before
    public void setUp() {
        // Inicializáld az osztályt minden teszt előtt
        backup = new Backup("Teszt adat", TEMP_FILE);
    }

    @After
    public void tearDown() {
        // Töröld az ideiglenes fájlt minden teszt után
        File file = new File(TEMP_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveAndLoad() throws IOException, ClassNotFoundException {
        // Teszteld a mentést
        backup.save();

        // Teszteld a betöltést
        Object loadedData = backup.load();

        // Ellenőrizd, hogy a betöltött adat nem null és egyezik-e a mentett adattal
        assertNotNull(loadedData);
        assertEquals("Teszt adat", loadedData);
    }

    @Test(expected = IOException.class)
    public void testLoadNonexistentFile() throws IOException, ClassNotFoundException {
        // Teszteld, hogy hibát dob, ha nem található a fájl
        backup.load();
    }
}
