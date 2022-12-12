package day12;

import java.util.LinkedHashSet;
import java.util.Stack;
import java.util.stream.Stream;

public class UniqueStack<E> extends LinkedHashSet<E> {
    Stack<E> stack = new Stack<>();
    private int size = 0;


    public Stream<E> stream() {
        return stack.stream();
    }

    public boolean push(E e) {
        if (!contains(e)) {
            ++size;
            stack.push(e);
            return add(e);
        }
        return false;
    }

    public E pop() {
        E val = null;
        if (!stack.isEmpty()) {
            --size;
            val = stack.pop();
            remove(val);
        }
        return val;
    }
    public E peek(){
        E val = null;
        if (!stack.isEmpty()) {
            val = stack.peek();
            //remove(val);
        }
        return val;
    }
}
