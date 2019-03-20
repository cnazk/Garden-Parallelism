package ir.cnazk.GreenHouse.Sections;

import ir.cnazk.GreenHouse.FProcessor;
import ir.cnazk.GreenHouse.FileCreator;
import ir.cnazk.GreenHouse.InputReader;
import ir.cnazk.GreenHouse.OneCounter;

import java.io.FileNotFoundException;

public class Section1 {

    public static void main(String[] args) throws FileNotFoundException {
        String path = FileCreator.createFile(15, "input");
        doTask(path);
    }

    public static void doTask(String path) throws FileNotFoundException {
        String input = InputReader.Read(path);
        System.out.println(input);
        long oneStart = System.currentTimeMillis();
        System.out.println("Ones Count: " + OneCounter.count(input) + " in " + (System.currentTimeMillis() - oneStart) + " milliseconds");
        long fStart = System.currentTimeMillis();
        String output = FProcessor.execute(input);
        System.out.println("Proceed Fs in " + (System.currentTimeMillis() - fStart) + " milliseconds");
        FileCreator.createOutput(output, "output");
    }
}