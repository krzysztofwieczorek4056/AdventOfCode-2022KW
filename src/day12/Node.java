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
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (getClass() != other.getClass())
            return false;
        final Node some = (Node) other;
        if (x != some.x)
            return false;
        return y == some.y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
}
