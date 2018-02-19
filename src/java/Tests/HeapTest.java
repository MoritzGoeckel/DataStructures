package Tests;

import Implementations.Heap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeapTest {

    @Test
    void max(){
        Heap h = new Heap();

        for(int i = 0; i < 100; i++)
            h.insert(i);

        for(int i = 200; i > 100; i--)
            h.insert(i);

        assertEquals(200, h.getMax());
    }

    @Test
    void size(){
        Heap h = new Heap();

        for(int i = 0; i < 100; i++)
            h.insert(i);

        assertEquals(100, h.getSize());
    }

    @Test
    void popMax(){
        Heap h = new Heap();

        for(int i = 0; i < 100; i++)
            h.insert(i);

        for(int i = 99; i >= 0; i--)
            assertEquals(i, h.popMax());

        assertEquals("[]", h.toString());
    }

    @Test
    void tostring(){
        Heap h = new Heap();

        for(int i = 0; i < 100; i++)
            h.insert(i);

        String s = h.toString();
        for(int i = 0; i < 100; i++)
        assertTrue(s.contains(i + ", ") || s.contains(", " + i));
    }

}
