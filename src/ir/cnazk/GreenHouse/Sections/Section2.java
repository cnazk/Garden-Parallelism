package ir.cnazk.GreenHouse.Sections;

import ir.cnazk.GreenHouse.FProcessor;
import ir.cnazk.GreenHouse.FileCreator;
import ir.cnazk.GreenHouse.InputReader;
import ir.cnazk.GreenHouse.OneCounter;

import java.io.FileNotFoundException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Section2 {

    public static void main(String[] args) throws FileNotFoundException {

        String path = FileCreator.createFile(0.2f, "input");
        doTask(path);
    }

    @SuppressWarnings("Duplicates")
    public static void doTask(String path) {
        BlockingQueue<String> inputQueue = new LinkedBlockingDeque<>(5);
        Thread reader = new Thread(() -> {
            long time = System.currentTimeMillis();
            inputQueue.offer(InputReader.Read(path));
            System.out.println("reader thread ended in " + (System.currentTimeMillis() - time) + " milliseconds");
        });
        BlockingQueue<String> onesQueue = new LinkedBlockingDeque<>(5);
        Thread onesCounter = new Thread(() -> {
            long time = System.currentTimeMillis();
            try {
                String input = inputQueue.take();
                OneCounter.count(input);
                onesQueue.offer(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("One Counter thread ended in " + (System.currentTimeMillis() - time) + " milliseconds");
        });
        BlockingQueue<String> outputQueue = new LinkedBlockingDeque<>(5);
        Thread fThread = new Thread(() -> {
            long time = System.currentTimeMillis();
            try {
                String output = FProcessor.execute(onesQueue.take());
                outputQueue.offer(output);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("F Processor thread ended in " + (System.currentTimeMillis() - time) + " milliseconds");
        });
        Thread outputThread = new Thread(() -> {
            long time = System.currentTimeMillis();
            try {
                FileCreator.createOutput(outputQueue.take(), "output");
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Output thread ended in " + (System.currentTimeMillis() - time) + " milliseconds");
        });
        reader.start();
        onesCounter.start();
        fThread.start();
        outputThread.start();
    }

}
