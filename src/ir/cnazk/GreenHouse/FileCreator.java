package ir.cnazk.GreenHouse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class FileCreator {

    private static String[] HEXS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    /**
     * @param size Desired file size in MBs
     * @param path Desired file path related to project folder
     * @return Absolute file path of created file
     * @throws FileNotFoundException File doesnt exists
     */
    public static String createFile(float size, String path) throws FileNotFoundException {
        Random random = new Random();
        File file = new File(path);
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)), false);
        while (true) {
            for (int i = 0; i < 100; i++) {
                writer.print(generateRandomHex(random));
            }
            if (file.length() >= size * 1e6) {
                writer.close();
                break;
            }
        }
        return file.getAbsolutePath();
    }


    /**
     * @param path Desired file path related to project folder
     * @return Absolute file path of created file (10 MBs)
     * @throws FileNotFoundException File doesnt exists
     */
    public static String createFile(String path) throws FileNotFoundException {
        return createFile(10, path);
    }

    public static void createOutput(String content, String path) throws FileNotFoundException {
        File file = new File(path);
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)), false);
        writer.print(content);
    }

    private static String generateRandomHex(Random random) {
        return HEXS[random.nextInt(HEXS.length)];

    }
}
