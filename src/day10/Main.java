package day10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/inputs/input10.txt");
        List<String> list = null;
        try {
            list = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        day10part1(list);
        //day10part2(list);

    }

    private static void day10part2(List<String> list) {
        int cyclesInLine = 0;
        int x = 1;
        char[] sprite = moveSprite(x);
        List<char[]> listAllLines = new ArrayList<>();
        char[] lineToPrint = new char[40];
        for (String a : list) {
            int value;
            String instruction = a.substring(0, 4);
            if (instruction.contains("addx")) {
                value = Integer.parseInt(a.substring(5));
                for (int i = 0; i < 2; i++) {
                    lineToPrint[cyclesInLine] = drawPixel(sprite, cyclesInLine);
                    if (cyclesInLine == 39) {
                        listAllLines.add(lineToPrint);
                        lineToPrint = new char[40];
                        cyclesInLine = -1;
                    }
                    cyclesInLine++;
                }
                x += value;
                sprite = moveSprite(x);
            } else {
                lineToPrint[cyclesInLine] = drawPixel(sprite, cyclesInLine);
                if (cyclesInLine == 39) {
                    listAllLines.add(lineToPrint);
                    lineToPrint = new char[40];
                    cyclesInLine = -1;
                }
                cyclesInLine++;
            }
        }
        for (char[] a : listAllLines) {
            System.out.println(a);
        }
    }

    private static char drawPixel(char[] sprite, int cycles) {
        return sprite[cycles];
    }

    private static char[] moveSprite(int value) {
        char[] sprite = new char[40];
        for (int i = 0; i < sprite.length; i++) {
            if (i == value || i == value + 1 || i == value - 1) {
                sprite[i] = '#';
            } else {
                sprite[i] = '.';
            }
        }
        return sprite;
    }

    private static void day10part1(List<String> list) {
        int cycles = 0;
        int x = 1;
        List<Integer> sum = new ArrayList<>();
        for (String a : list) {
            int value;
            String instruction = a.substring(0, 4);
            if (instruction.contains("addx")) {
                value = Integer.parseInt(a.substring(5));
                for (int i = 0; i < 2; i++) {
                    cycles++;
                    sum.add(checkCycle(cycles, x));
                }
                x += value;
            } else {
                sum.add(checkCycle(cycles, x));
                cycles++;
            }
        }
        System.out.println(sum.stream().mapToInt(Integer::intValue).sum());
    }


    private static int checkCycle(int cycles, int x) {
        final int[] array = new int[]{20, 60, 100, 140, 180, 220};
        if (Arrays.stream(array).anyMatch(i -> i == cycles)) {
            return x * cycles;
        }
        return 0;
    }
}
