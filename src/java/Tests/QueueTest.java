package Tests;

import Implementations.Queue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class QueueTest {

    @Test
    void testEnqueue(){
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals("[ 0, 1, 2 ]", queue.toString());
    }

    @Test
    void testDequeue(){
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(new Integer(0), queue.dequeue());
        assertEquals("[ 1, 2 ]", queue.toString());

        assertEquals(new Integer(1), queue.dequeue());
        assertEquals("[ 2 ]", queue.toString());

        assertEquals(new Integer(2), queue.dequeue());
        assertEquals("[  ]", queue.toString());
    }

    @Test
    void testPeek(){
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(0);
        assertEquals(new Integer(0), queue.peek());
        queue.enqueue(1);
        assertEquals(new Integer(0), queue.peek());
        queue.dequeue();
        assertEquals(new Integer(1), queue.peek());
        queue.dequeue();
        assertEquals(null, queue.peek());
    }

    @Test
    void testSize(){
        Queue<Integer> queue = new Queue<>();
        assertEquals(0, queue.getSize());
        queue.enqueue(0);
        assertEquals(1, queue.getSize());
        queue.enqueue(1);
        assertEquals(2, queue.getSize());
        queue.dequeue();
        assertEquals(1, queue.getSize());
        queue.dequeue();
        assertEquals(0, queue.getSize());
    }

    @Test
    void dequeueEmpty() {
        Queue<Integer> queue = new Queue<>();
        assertEquals(null, queue.dequeue());
    }
}
