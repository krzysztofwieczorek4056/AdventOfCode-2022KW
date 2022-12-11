package day9;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/inputs/input9.txt");
        List<String> list = null;
        try {
            list = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        snake1(list);
        snake2(list);
    }

    private static void snake1(List<String> list) {
        int x = 0;
        int y = 0;
        int tailX = 0;
        int tailY = 0;
        List<Point> visited = new ArrayList<>();
        for (String line : list) {
            String direction = line.substring(0, 1);
            int moves = Integer.parseInt(line.substring(2));
            for (int i = 0; i < moves; i++) {
                //moveHead
                List<Integer> changes = moveHead(direction, x, y);
                x = changes.get(0);
                y = changes.get(1);
                //moveTail
                int[] changesTail = moveTail(x, y, tailX, tailY);
                tailX = changesTail[0];
                tailY = changesTail[1];
                visited.add(new Point(tailX, tailY));

            }
        }
        List<Point> listDistinct = visited.stream().distinct().collect(Collectors.toList());

        System.out.println(listDistinct.size());
    }

    private static void snake2(List<String> list) {
        int x = 0;
        int y = 0;
        List<int[]> tailsList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            tailsList.add(new int[2]);
        }
        List<Point> visited = new ArrayList<>();
        for (String line : list) {
            String direction = line.substring(0, 1);
            int moves = Integer.parseInt(line.substring(2));
            for (int i = 0; i < moves; i++) {
                //moveHead
                List<Integer> changes = moveHead(direction, x, y);
                x = changes.get(0);
                y = changes.get(1);
                //moveTail
                int[] changesTail = moveTail(x, y, tailsList.get(0)[0], tailsList.get(0)[1]);
                tailsList.set(0, changesTail);

                for (int j = 1; j < tailsList.size(); j++) {
                    changesTail = moveTail(tailsList.get(j - 1)[0], tailsList.get(j - 1)[1], tailsList.get(j)[0], tailsList.get(j)[1]);
                    tailsList.set(j, changesTail);
                }

                visited.add(new Point(tailsList.get(tailsList.size() - 1)[0], tailsList.get(tailsList.size() - 1)[1]));

            }

        }
        List<Point> listDistinct = visited.stream().distinct().collect(Collectors.toList());
        System.out.println(listDistinct.size());
    }

    private static int[] moveTail(int x, int y, int tailX, int tailY) {
        if ((x != tailX || x != tailX - 1 || x != tailX + 1) && (y != tailY || y != tailY - 1 || y != tailY + 1)) {
            if (x - tailX > 1) {
                if (y - tailY > 1) {
                    tailY++;
                }else if (tailY - y > 1) {
                    tailY--;
                }else {
                    tailY = y;
                }
                tailX++;
            } else if (tailX - x > 1) {
                if (y - tailY > 1) {
                    tailY++;
                }else if (tailY - y > 1) {
                    tailY--;
                }else {
                    tailY = y;
                }
                tailX--;
            } else if (y - tailY > 1) {
                if (x - tailX > 1) {
                    tailX++;
                }else if (tailX - x > 1) {
                    tailX--;
                }else {
                    tailX = x;
                }
                tailY++;

            } else if (tailY - y > 1) {
                if (x - tailX > 1) {
                    tailX++;
                }else if (tailX - x > 1) {
                    tailX--;
                }else {
                    tailX = x;
                }
                tailY--;
            }
        } else {
            System.out.println("oderwałem sie");
        }
        int[] toReturn = new int[2];
        toReturn[0] = tailX;
        toReturn[1] = tailY;
        return toReturn;
    }


    private static List<Integer> moveHead(String direction, int x, int y) {
        switch (direction) {
            case "R" -> x++;
            case "L" -> x--;
            case "U" -> y++;
            case "D" -> y--;
        }
        List<Integer> toReturn = new ArrayList<>();
        toReturn.add(x);
        toReturn.add(y);
        return toReturn;
    }
}
