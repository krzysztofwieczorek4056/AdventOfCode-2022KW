package day12;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
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

        int path = Dikstra.pathExists(map ,getNode(map));
        System.out.println(path);
        int path2 = 999;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 'a'){
                    int test = Dikstra.pathExists(map ,new Node(i, j, 0, 'a'));
                    if(test< path2){
                        path2 = test;
                    }
                }
            }
        }
        System.out.println(path2);
    }
    private static Node getNode(char[][] matrix) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'S') {
                    x = i;
                    y = j;
                }
            }
        }
        return new Node(x, y, 0, 'a');
    }

}
