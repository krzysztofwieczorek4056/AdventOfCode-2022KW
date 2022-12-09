package day8;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/inputs/input8.txt");
        List<String> list = null;
        try {
            list = Files.readAllLines(file.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        part1(list);
        part2(list);

    }

    private static void part1(List<String> list) {
        int[][] tab = new int[list.size()][list.get(0).length()];
        int visible = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < tab[0].length; j++) {
                tab[i][j] = Integer.parseInt(String.valueOf(list.get(i).charAt(j)));
            }
        }
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (i == 0 || i == tab.length - 1 || j == 0 || j == tab[i].length - 1) {
                    visible++;
                } else {
                    boolean visibility = false;
                    int[] max = new int[4];
                    for (int k = 0; k < i; k++) {
                        if (tab[k][j] > max[0]) {
                            max[0] = tab[k][j];
                        }
                    }
                    for (int k = i + 1; k < tab.length; k++) {
                        if (tab[k][j] > max[1]) {
                            max[1] = tab[k][j];
                        }
                    }
                    for (int k = 0; k < j; k++) {
                        if (tab[i][k] > max[2]) {
                            max[2] = tab[i][k];
                        }
                    }
                    for (int k = j + 1; k < tab[i].length; k++) {
                        if (tab[i][k] > max[3]) {
                            max[3] = tab[i][k];
                        }
                    }
                    for (int a : max) {
                        if (a < tab[i][j]) {
                            visibility = true;
                            break;
                        }
                    }
                    if (visibility) {
                        visible++;
                    }
                }

            }
        }
        System.out.println(visible);
    }

    private static void part2(List<String> list) {
        int[][] tab = new int[list.size()][list.get(0).length()];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < tab[0].length; j++) {
                tab[i][j] = Integer.parseInt(String.valueOf(list.get(i).charAt(j)));
            }
        }
        List<Integer> scoreList = new ArrayList<>();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (i == 0 || i == tab.length - 1 || j == 0 || j == tab[i].length - 1) {
                } else {
                    int[] max = new int[4];
                    for (int k = i - 1; k >= 0; k--) {
                        max[0]++;
                        if (tab[k][j] >= tab[i][j]) {
                            break;
                        }
                    }
                    for (int k = i + 1; k < tab.length; k++) {
                        max[1]++;
                        if (tab[k][j] >= tab[i][j]) {
                            break;
                        }
                    }
                    for (int k = j - 1; k >= 0; k--) {
                        max[2]++;
                        if (tab[i][k] >= tab[i][j]) {
                            break;
                        }
                    }
                    for (int k = j + 1; k < tab[i].length; k++) {

                        max[3]++;
                        if (tab[i][k] >= tab[i][j]) {
                            break;
                        }
                    }
                    int score = 1;
                    int x = tab[i][j];
                    for (int a : max) {
                        if (a == 0) {
                            a = 1;
                        }
                        score = score * a;
                    }
                    scoreList.add(score);
                }
            }

        }
        System.out.println(Collections.max(scoreList));
    }

}
