package Function;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class IOFile<E> {
    public void writeDataToFile(List<E> listE, String path) {
        try (FileOutputStream fos = new FileOutputStream(path); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(listE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<E> readDataFromFile(String path) {
        List<E> listE = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(path); ObjectInputStream ois = new ObjectInputStream(fis)) {
            listE = (List<E>) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listE;
    }
}
