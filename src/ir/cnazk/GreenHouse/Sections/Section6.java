package ir.cnazk.GreenHouse.Sections;

import ir.cnazk.GreenHouse.FileCreator;

import java.io.FileNotFoundException;
import java.util.Random;

public class Section6 {

    public static void main(String[] args) {
        new Thread(new ContentGenerator("input")).start();
    }

    public static void newContentAvailable() {
        Section4.doTask("input");
    }

    private static boolean isFileInUse() {
        return !Section4.done;
    }


    static class ContentGenerator implements Runnable {


        String path;

        public ContentGenerator(String path) {
            this.path = path;
        }

        @Override
        public void run() {
            Random random = new Random();
            try {
                FileCreator.createFile(random.nextInt(5) + 5, "input");
                Section6.newContentAvailable();
            } catch (FileNotFoundException ignored) {

            }
            while (true) {
                try {
                    Thread.sleep(10000);
                    while (Section6.isFileInUse()) {
                    }
                    FileCreator.createFile(random.nextInt(5) + 5, "input");
                    Section6.newContentAvailable();
                } catch (InterruptedException | FileNotFoundException ignored) {
                }

            }
        }

    }

}
