import java.util.ArrayList;

public class Deque<E>{
    private final ArrayList<E> deque = new ArrayList<>();

    public Deque() {}

    public void enqueueF(E e) { deque.addFirst(e); }

    public void enqueueR(E e) { deque.add(e); }

    public E dequeF() {
        if (!deque.isEmpty()) {
            return deque.removeFirst();
        } else {
            throw new Error("Deque is empty");
        }
    }
    public E dequeR() {
        if (!deque.isEmpty()) {
            return deque.removeLast();
        } else {
            throw new Error("Deque is empty");
        }
    }

    public E peekF() {
        if(!deque.isEmpty()) {
            return deque.getFirst();
        } else {
            throw new Error("Deque is empty");
        }
    }

    public E peekR() {
        if(!deque.isEmpty()) {
            return deque.getLast();
        } else {
            throw new Error("Deque is empty");
        }
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public int getSize() { return deque.size(); }
    @Override
    public String toString() {
        if (deque.isEmpty()) {
            return "[]";
        }
        StringBuilder out = new StringBuilder("[");
        for (int i = 0; i < deque.size()-1; i++) {
            out.append(deque.get(i)).append(", ");
        }
        return out.toString() + deque.getLast() + "]";
    }
}
