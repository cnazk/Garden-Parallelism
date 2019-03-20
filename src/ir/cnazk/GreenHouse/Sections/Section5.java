package ir.cnazk.GreenHouse.Sections;

import ir.cnazk.GreenHouse.FileCreator;

import java.io.FileNotFoundException;

public class Section5 {

    public static void main(String[] args) throws FileNotFoundException {

        String path = FileCreator.createFile(10, "input");
        long time1 = System.currentTimeMillis();
        Section1.doTask(path);
        System.out.println("Section 1 ended Task in " + (System.currentTimeMillis() - time1) + " milliseconds");

        long time2 = System.currentTimeMillis();
        Section2.doTask(path);
        System.out.println("Section 2 ended Task in " + (System.currentTimeMillis() - time2) + " milliseconds");

        long time3 = System.currentTimeMillis();
        Section3.doTask(path);
        System.out.println("Section 3 ended Task in " + (System.currentTimeMillis() - time3) + " milliseconds");

        long time4 = System.currentTimeMillis();
        Section4.doTask(path);
        System.out.println("Section 4 ended Task in " + (System.currentTimeMillis() - time4) + " milliseconds");
    }

}
