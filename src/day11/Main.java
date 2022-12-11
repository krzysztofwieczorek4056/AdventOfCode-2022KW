package day11;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/inputs/input11.txt");
        List<String> list = null;
        try {
            list = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        day11part1(list);
        day11part2(list);
    }

    private static void day11part1(List<String> list) {
        List<Monkey> monkeyList = makeMonkey(list);
        long[] monkeyIntrest = new long[monkeyList.size()];
        for (int j = 0; j < 20; j++) {
            for (Monkey monkey : monkeyList) {
                int itemSize = monkey.getItemsSize();
                for (int i = 0; i < itemSize; i++) {
                    monkeyIntrest[monkey.getNumber()]++;
                    BigInteger worryLevel = monkey.getWorryLevel(monkey.getFirstItem());
                    worryLevel = worryLevel.divide(BigInteger.valueOf(3));
                    long monkeyToThrow = monkey.testThrow(worryLevel);
                    monkeyList.get((int) monkeyToThrow).throwItem(worryLevel);
                }
            }

        }
        findMonkeyBusiness(monkeyIntrest);
    }

    private static void day11part2(List<String> list) {
        //BigInteger worryLevel = BigInteger.valueOf(0);
        List<Monkey> monkeyList = makeMonkey(list);
        long[] monkeyIntrest = new long[monkeyList.size()];
        long common = monkeyList.stream().mapToLong(Monkey::getTest).reduce(1, (a, b) -> a * b);
        for (int j = 0; j < 10000; j++) {
            for (Monkey monkey : monkeyList) {
                int itemSize = monkey.getItemsSize();
                for (int i = 0; i < itemSize; i++) {
                    monkeyIntrest[monkey.getNumber()]++;
                    BigInteger worryLevel = monkey.getWorryLevel(monkey.getFirstItem().remainder(BigInteger.valueOf(common)));
                    long monkeyToThrow = monkey.testThrow(worryLevel);
                    monkeyList.get((int) monkeyToThrow).throwItem(worryLevel);
                }
            }
        }
        findMonkeyBusiness(monkeyIntrest);
    }


    private static void findMonkeyBusiness(long[] monkeyIntrest) {
        long largestA = monkeyIntrest[0];
        long largestB = -1;

        for (int i = 0; i < monkeyIntrest.length; i++) {

            if (monkeyIntrest[i] > largestA) {
                largestB = largestA;
                largestA = monkeyIntrest[i];
            } else if (monkeyIntrest[i] > largestB && monkeyIntrest[i] != largestA) {
                largestB = monkeyIntrest[i];
            }
        }
        System.out.println("MonkeyBusiness - " + BigInteger.valueOf(largestA).multiply(BigInteger.valueOf(largestB)));
    }

    private static List<Monkey> makeMonkey(List<String> list) {
        int monkeyNumber = 0;
        List<String> startingItems = new ArrayList<>();
        String operation = "";
        int test = 0;
        int monkeyOption1 = 0;
        int monkeyOption2 = 0;
        int lineCount = 0;
        List<Monkey> monkeyList = new ArrayList<>();
        for (String s : list) {
            if (!s.isEmpty()) {
                switch (lineCount) {
                    case 0 -> monkeyNumber = Integer.parseInt(s.substring(7, 8));
                    case 1 -> startingItems = List.of(s.substring(18).split(", "));
                    case 2 -> operation = s.substring(19);
                    case 3 -> test = Integer.parseInt(s.substring(21));
                    case 4 -> monkeyOption1 = Integer.parseInt(s.substring(29));
                    case 5 -> monkeyOption2 = Integer.parseInt(s.substring(30));
                }
                lineCount++;
                if (lineCount > 5) {
                    monkeyList.add(new Monkey(monkeyNumber, startingItems, operation, test, monkeyOption1, monkeyOption2));
                    monkeyNumber = 0;
                    startingItems = new ArrayList<>();
                    operation = "";
                    test = 0;
                    monkeyOption1 = 0;
                    monkeyOption2 = 0;
                    lineCount = 0;
                }
            }
        }
        return monkeyList;
    }

    static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    // Function to find gcd of array of
    // numbers
    static int findGCD(List<BigInteger> arr, int n) {
        int result = arr.get(0).intValue();
        for (BigInteger element : arr) {
            result = gcd(result, element.intValue());

            if (result == 1) {
                return 1;
            }
        }

        return result;
    }
}
