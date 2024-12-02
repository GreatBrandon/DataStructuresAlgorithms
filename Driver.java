public class Driver {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        System.out.println(s);
        System.out.println(s.isEmpty());
        s.push(10);
        s.push(20);
        s.push(30);
        System.out.println(s.peek());
        System.out.println(s);
        System.out.println(s.isEmpty());
        System.out.println(s.push(40));
        System.out.println(s.pop());
        System.out.println(s);
        System.out.println(s.getSize());

        System.out.println();

        Queue<Integer> q = new Queue<>();

        System.out.println(q.isEmpty());
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println(q.peek());
        System.out.println(q);
        System.out.println(q.isEmpty());
        System.out.println(q.enqueue(40));
        System.out.println(q.dequeue());
        System.out.println(q);
        System.out.println(q.getSize());

        System.out.println();

        Deque<Integer> dq = new Deque<>();
        System.out.println(dq);
        dq.enqueueF(10);
        dq.enqueueF(20);
        dq.enqueueF(30);
        dq.enqueueR(30);
        System.out.println(dq);
        System.out.println(dq.getSize());
        System.out.println(dq.peekF());
        System.out.println(dq.peekR());
        System.out.println(dq.dequeF());
        System.out.println(dq.dequeR());
    }
}
