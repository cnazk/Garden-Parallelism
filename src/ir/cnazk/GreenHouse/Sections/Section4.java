package ir.cnazk.GreenHouse.Sections;

import ir.cnazk.GreenHouse.FProcessor;
import ir.cnazk.GreenHouse.FileCreator;
import ir.cnazk.GreenHouse.InputReader;
import ir.cnazk.GreenHouse.OneCounter;

import java.io.FileNotFoundException;
import java.util.concurrent.*;

public class Section4 {

    public static boolean done = false;

    public static void main(String[] args) throws FileNotFoundException {

        String path = FileCreator.createFile(1, "input");
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
                String input = onesQueue.take();
                StringBuilder output = new StringBuilder();
                int threadCount = Runtime.getRuntime().availableProcessors();
                ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
                Future[] futures = new Future[threadCount];
                for (int i = 0; i < threadCount; i++) {
                    FProccessCallable callable = new FProccessCallable();
                    callable.input = input.substring(i * (input.length() / threadCount), (i + 1) * (input.length() / threadCount));
                    futures[i] = threadPool.submit(callable);
                }
                for (Future future : futures) {
                    output.append(future.get());
                }
                threadPool.shutdown();
                outputQueue.offer(output.toString());
            } catch (InterruptedException | ExecutionException e) {
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
            done = true;
        });

        reader.start();
        onesCounter.start();
        fThread.start();
        outputThread.start();
    }

    static class FProccessCallable implements Callable<String> {

        String input = "";

        @Override
        public String call() throws Exception {
            return FProcessor.execute(input);
        }
    }

}
