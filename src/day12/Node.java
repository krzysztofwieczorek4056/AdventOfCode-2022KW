package day12;

import java.util.Objects;

class Node {
    int x;
    int y;
    int distanceFromSource;
    char value;

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", distanceFromSource=" + distanceFromSource +
                ", value=" + value +
                '}';
    }

    Node(int x, int y, int dis, char value) {
        this.x = x;
        this.y = y;
        this.distanceFromSource = dis;
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        final Node some = (Node) other;
        if (x != some.x) return false;
        if (y != some.y) return false;
        return value == some.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, value);
    }
}
