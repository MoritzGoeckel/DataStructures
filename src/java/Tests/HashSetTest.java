package Tests;

import Implementations.HashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashSetTest {

    @Test
    void addTest(){
        HashSet<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");
        set.add("A");

        assertEquals("[B, C, A]", set.toString());
    }

    @Test
    void removeTest(){
        HashSet<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");

        set.remove("A");

        assertEquals("[B, C]", set.toString());
    }

    @Test
    void clearTest(){
        HashSet<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");

        set.clear();

        assertEquals("[]", set.toString());
    }

    @Test
    void sizeTest(){
        HashSet<String> set = new HashSet<>();
        assertEquals(0, set.size());
        set.add("A");
        assertEquals(1, set.size());
        set.add("B");
        assertEquals(2, set.size());
        set.add("C");
        assertEquals(3, set.size());
        set.remove("A");
        assertEquals(2, set.size());
        set.clear();
        assertEquals(0, set.size());
    }

    @Test
    void iteratorTest(){
        HashSet<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");

        StringBuilder sb = new StringBuilder();
        for(String s : set)
            sb.append(s + ",");

        assertNotEquals(-1, sb.indexOf("A,"));
        assertNotEquals(-1, sb.indexOf("B,"));
        assertNotEquals(-1, sb.indexOf("C,"));
        assertEquals(6, sb.length());
    }
}
