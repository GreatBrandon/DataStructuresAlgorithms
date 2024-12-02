import java.util.ArrayList;

public class Queue<E> {
    private final ArrayList<E> queue = new ArrayList<>();

    public Queue() {}

    public boolean enqueue(E e) {
        return queue.add(e);
    }

    public E dequeue() {
        if (!queue.isEmpty()) {
            return queue.removeFirst();
        } else {
            throw new Error("Queue is empty");
        }
    }

    public E peek() {
        if (!queue.isEmpty()) {
            return queue.getFirst();
        } else {
            throw new Error("Queue is empty");
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getSize() { return queue.size(); }
    @Override
    public String toString() {
        if (queue.isEmpty()) {
            return "[]";
        }
        StringBuilder out = new StringBuilder("[");

        for (int i = 0; i < queue.size()-1; i++) {
            out.append(queue.get(i)).append(", ");
        }
        return out.toString() + queue.getLast() + "]";
    }
}
