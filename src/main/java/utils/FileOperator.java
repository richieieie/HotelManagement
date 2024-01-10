package utils;

import model.Hotel;
import model.HotelList;

import java.io.*;
import java.util.List;

public class FileOperator {
    public static boolean writeToFile(String path, HotelList list) {
        try {
            File file = new File(path);
            if (file.createNewFile())
                System.out.println("New file is created " + list.getClass().getSimpleName());
            FileOutputStream fos = new FileOutputStream(file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(list);

            oos.close();
            fos.close();

            return true;
        } catch (NullPointerException | IOException e) {
            System.out.println("Failed to write");
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static boolean readFromFile(String path, HotelList list) {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<Hotel> obs = (List<Hotel>) ois.readObject();
            if (obs.isEmpty()) {
                System.out.println("No objects were found");
                return true;
            }

            list.addAll(obs);

            ois.close();
            fis.close();

            return true;
        } catch (EOFException e) {
            System.out.println("File is empty");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Be sure that your file's path is correct!!!");
            return false;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to read, be sure that you haven't change any class " +
                    "implementation!!!");
            System.out.println("If you did it, please delete all dat files or texts inside it!!!");
            return false;
        }
    }
}