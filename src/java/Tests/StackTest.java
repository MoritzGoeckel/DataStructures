package Tests;

import Implementations.Stack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StackTest {

    @Test
    void testPush(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals("[ 3, 2, 1 ]", stack.toString());
    }

    @Test
    void testPop(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.pop();
        assertEquals("[ 2, 1 ]", stack.toString());

        stack.pop();
        assertEquals("[ 1 ]", stack.toString());

        stack.pop();
        assertEquals("[  ]", stack.toString());
    }

    @Test
    void testPeek(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);

        assertEquals(new Integer(2), stack.peek());
    }

    @Test
    void testSize(){
        Stack<Integer> stack = new Stack<>();
        assertEquals(0, stack.getSize());

        stack.push(1);
        assertEquals(1, stack.getSize());

        stack.peek();

        stack.push(2);
        assertEquals(2, stack.getSize());

        stack.pop();
        assertEquals(1, stack.getSize());

        stack.pop();
        assertEquals(0, stack.getSize());
    }

}
