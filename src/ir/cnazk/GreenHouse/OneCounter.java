package ir.cnazk.GreenHouse;

import java.util.HashMap;

public class OneCounter {

    private static final HashMap<String, Integer> DICTIONARY;

    static {
        DICTIONARY = new HashMap<>();
        DICTIONARY.put("0", 0);
        DICTIONARY.put("1", 1);
        DICTIONARY.put("2", 1);
        DICTIONARY.put("3", 2);
        DICTIONARY.put("4", 1);
        DICTIONARY.put("5", 2);
        DICTIONARY.put("6", 2);
        DICTIONARY.put("7", 3);
        DICTIONARY.put("8", 1);
        DICTIONARY.put("9", 2);
        DICTIONARY.put("A", 2);
        DICTIONARY.put("B", 3);
        DICTIONARY.put("C", 2);
        DICTIONARY.put("D", 3);
        DICTIONARY.put("E", 3);
        DICTIONARY.put("F", 4);
    }

    public static int count(String input) {
        int ret = 0;
        for (int i = 0; i < input.length(); i++) {
            ret += DICTIONARY.get(input.substring(i, i + 1).toUpperCase());
        }
        return ret;
    }

}
