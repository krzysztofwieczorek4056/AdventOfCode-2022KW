package day12;

class Node {
    int x;
    int y;
    int distanceFromSource;
    char value;

    Node(int x, int y, int dis, char value) {
        this.x = x;
        this.y = y;
        this.distanceFromSource = dis;
        this.value = value;
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Node)) {
            return false;
        }
        if(x == ((Node) other).x &&y == ((Node) other).y){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }
}
