import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack<E>{
    private final ArrayList<E> stack = new ArrayList<>();

    public Stack() {}

    public boolean push(E e) {
        return stack.add(e);
    }

    public E pop() {
        if (!stack.isEmpty()) {
            return stack.removeLast();
        } else {
            throw new EmptyStackException();
        }
    } 

    public E peek() {
        if (!stack.isEmpty()) {
            return stack.getLast();
        } else {
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int getSize() { return stack.size(); }
    @Override
    public String toString() {
        if (stack.isEmpty()) {
            return "[]";
        }
        StringBuilder out = new StringBuilder("[");
        
        for (int i = 0; i < stack.size()-1; i++) {
            out.append(stack.get(i)).append(", ");
        }
        return out.toString() +stack.getLast()+"]";
    }
}
