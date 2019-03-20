import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class FileCreator implements Runnable {

    private static String[] hexs = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    public int size = 10;
    public String path = "";


    /**
     * @param size Desired file size in MBs
     * @param path Desired file path related to project folder
     * @return Absolute file path of created file
     * @throws FileNotFoundException File doesnt exists
     */
    private static String CreateFile(int size, String path) throws FileNotFoundException {
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
    private static String CreateFile(String path) throws FileNotFoundException {
        return CreateFile(10, path);
    }

    private static String generateRandomHex(Random random) {
        return hexs[random.nextInt(hexs.length)];
    }

    @Override
    public void run() {
        if (path.equals("")) {
            path = LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonthValue() + "-" + LocalDateTime.now().getDayOfMonth() + "-" +
                    LocalDateTime.now().toLocalTime();
            path = LocalDateTime.now().toString();
        }
        try {
            CreateFile(size, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
