package day12;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


class Dikstra {

    static int pathExists(char[][] matrix) {
        Node source = getNode(matrix);
        HashSet<Node> queue = new HashSet<>();

        int numOfRows = matrix.length;
        int numOfColumns = matrix[0].length;
        queue.add(source);
        Iterator<Node> it = queue.iterator();
        while (!queue.isEmpty()) {
            Node poped = it.next();
            if (matrix[poped.x][poped.y] == 'E') {
                return poped.distanceFromSource;
            } else {
                List<Node> neighbourList = addNeighbours(poped, matrix, numOfRows, numOfColumns);
                int size = queue.size();
                queue.addAll(neighbourList);
                if (size != queue.size()) {
                    it = queue.iterator();
                }
            }
        }

        return -1;
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

    private static List<Node> addNeighbours(Node poped, char[][] matrix, final int numOfRows, final int numOfColumns) {

        List<Node> list = new LinkedList<>();

        if ((poped.x - 1 >= 0 && poped.x - 1 < numOfRows) && isGoodElevation(poped, matrix, poped.x - 1, poped.y)) {
            list.add(new Node(poped.x - 1, poped.y, poped.distanceFromSource + 1, matrix[poped.x - 1][poped.y]));
        }
        if ((poped.x + 1 >= 0 && poped.x + 1 < numOfRows) && isGoodElevation(poped, matrix, poped.x + 1, poped.y)) {
            list.add(new Node(poped.x + 1, poped.y, poped.distanceFromSource + 1, matrix[poped.x + 1][poped.y]));
        }
        if ((poped.y - 1 >= 0 && poped.y - 1 < numOfColumns) && isGoodElevation(poped, matrix, poped.x, poped.y - 1)) {
            list.add(new Node(poped.x, poped.y - 1, poped.distanceFromSource + 1, matrix[poped.x][poped.y - 1]));
        }
        if ((poped.y + 1 >= 0 && poped.y + 1 < numOfColumns) && isGoodElevation(poped, matrix, poped.x, poped.y + 1)) {
            list.add(new Node(poped.x, poped.y + 1, poped.distanceFromSource + 1, matrix[poped.x][poped.y + 1]));
        }

        return list;
    }

    private static boolean isGoodElevation(Node poped, char[][] matrix, int x, int y) {
        return matrix[x][y] - poped.value == 1 || matrix[x][y] - poped.value == 0 || (matrix[x][y] - poped.value < 0 && matrix[x][y] - poped.value > -26) || (matrix[x][y] == 'E' && poped.value == 'z');
    }
}