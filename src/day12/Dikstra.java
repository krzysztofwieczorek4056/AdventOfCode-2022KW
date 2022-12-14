package day12;

import java.util.*;


class Dikstra {

    static int pathExists(char[][] matrix, Node node) {
        Node source = node;
        Set<Node> queue = new LinkedHashSet<>();

        int numOfRows = matrix.length;
        int numOfColumns = matrix[0].length;
        queue.add(source);
        Iterator<Node> it = queue.iterator();
        while (!queue.isEmpty()) {
            if(!it.hasNext()){
                return 1000;
            }
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



    private static List<Node> addNeighbours(Node poped, char[][] matrix, final int numOfRows, final int numOfColumns) {

        List<Node> list = new LinkedList<>();

        if ((poped.x - 1 >= 0 && poped.x - 1 < numOfRows) && isGoodElevation(poped.value, matrix, poped.x - 1, poped.y)) {
            list.add(new Node(poped.x - 1, poped.y, poped.distanceFromSource + 1, matrix[poped.x - 1][poped.y]));
        }
        if ((poped.x + 1 >= 0 && poped.x + 1 < numOfRows) && isGoodElevation(poped.value, matrix, poped.x + 1, poped.y)) {
            list.add(new Node(poped.x + 1, poped.y, poped.distanceFromSource + 1, matrix[poped.x + 1][poped.y]));
        }
        if ((poped.y - 1 >= 0 && poped.y - 1 < numOfColumns) && isGoodElevation(poped.value, matrix, poped.x, poped.y - 1)) {
            list.add(new Node(poped.x, poped.y - 1, poped.distanceFromSource + 1, matrix[poped.x][poped.y - 1]));
        }
        if ((poped.y + 1 >= 0 && poped.y + 1 < numOfColumns) && isGoodElevation(poped.value, matrix, poped.x, poped.y + 1)) {
            list.add(new Node(poped.x, poped.y + 1, poped.distanceFromSource + 1, matrix[poped.x][poped.y + 1]));
        }

        return list;
    }

    private static boolean isGoodElevation(char value, char[][] matrix, int x, int y) {
        return matrix[x][y] - value == 1 || matrix[x][y] - value == 0 || (matrix[x][y] - value < 0 && matrix[x][y] - value > -26) || (matrix[x][y] == 'E' && value == 'z');
    }
}