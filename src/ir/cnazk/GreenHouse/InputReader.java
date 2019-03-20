package ir.cnazk.GreenHouse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputReader {

    public static String Read(String path) {
        try {
            return Files.readAllLines(Paths.get(path)).get(0);
        } catch (IOException e) {
            return "";
        }
    }

}
