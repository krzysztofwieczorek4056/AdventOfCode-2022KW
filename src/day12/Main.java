package day12;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/inputs/input12.txt");
        List<String> list = null;
        try {
            list = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        part1FindPath(list);
    }

    private static void part1FindPath(List<String> list) {
        char[][] map = new char[list.size()][list.get(0).length()];
        for (int i = 0; i < list.size(); i++) {
            map[i] = list.get(i).toCharArray();
        }
        int path = Dikstra.pathExists(map);
        System.out.println(path);
    }

}
