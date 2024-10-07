package project;

import java.io.*;

public class Backup {
    //Mentés fájl
    private String filename ;
    //Mentenivaló
    private Object adatok;
    /**
     * Beállítja melyik Objectet akarjuk majd menteni
     * @param mentenivalo Menteni kívánt Object
     * @param backupfile mentés helye
     */
    public Backup(Object mentenivalo,String backupfile){
        adatok=mentenivalo;
        filename=backupfile;
    }

    /**
     * Fájlba menti szerializálva a beállított Objectet
     * @throws IOException hiba esetén
     */
    public void save() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(adatok);
        out.close();
    }

    /**
     * Betölti a mentett Objectet
     * @return mentett Object
     * @throws IOException,ClassNotFoundException hiba esetén
     */
    public Object load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        adatok = in.readObject();
        in.close();
        return adatok;
    }
}


