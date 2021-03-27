package main.utilites;

import java.io.*;

/**
 * Write and Read Object
 */
public class ObjectFileUtility {

    /**
     * The object to be written should be implement Serializable
     */
    public static void writeObjectIntoFile(String fileName, Serializable object) throws IOException {
        try(
                FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(object);
        }
    }

    public static Object readObjectFromFile(String fileName) throws Exception{
        Object obj = null;
        try(
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            obj = ois.readObject();
        }
        return obj;
    }
}
