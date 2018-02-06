package Lists.Tests;

import Lists.LinkedList;
import Lists.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedListTest {

    /*
    void add(T item);
    T get(int index);
    void remove(int index);
    void clear();
    int getIndex(T item);
    int size();
     */

    @Test
    void addGetOne() {
        List<String> list = new LinkedList<>();
        list.add("Hallo");
        assertEquals("Hallo", list.get(0));
    }

    @Test
    void addGetMany() {
        List<Integer> list = new LinkedList<>();

        for(int i = 0; i < 30; i++)
            list.add(i);

        for(int i = 0; i < 30; i++)
            assertEquals(new Integer(i), list.get(i));
    }

    @Test
    void removeRoot() {
        List<Integer> list = new LinkedList<>();
        list.add(0);
        list.add(1);

        list.remove(0);
        assertEquals(new Integer(1), list.get(0));
    }

    @Test
    void remove() {
        List<Integer> list = new LinkedList<>();
        list.add(0);
        list.add(1);
        list.add(2);

        list.remove(1);

        assertEquals(new Integer(0), list.get(0));
        assertEquals(new Integer(2), list.get(1));
    }

    @Test
    void clear() {
        List<Integer> list = new LinkedList<>();
        for(int i = 0; i < 30; i++)
            list.add(i);

        list.clear();

        assertEquals(0, list.size());
        assertEquals("[]", list.toString());
    }

    @Test
    void getIndex() {
        List<Integer> list = new LinkedList<>();

        for(int i = 0; i < 30; i++)
            list.add(i);

        for(int i = 0; i < 30; i++)
            assertEquals(i, list.getIndex(i));
    }

    @Test
    void size() {
        List<Integer> list = new LinkedList<>();

        for(int i = 0; i < 30; i++)
            list.add(i);

        list.remove(3);
        assertEquals(30 - 1, list.size());

        list.remove(3);
        assertEquals(30 - 2, list.size());

        list.add(5);
        assertEquals(31 - 2, list.size());

        list.clear();
        assertEquals(0, list.size());

        list.add(5);
        assertEquals(1, list.size());
    }

    @Test
    void getString() {
        List<Integer> list = new LinkedList<>();

        for(int i = 0; i < 30; i++)
            list.add(i);

        String str = list.toString();
        assertEquals("[", str.substring(0,1));
        assertEquals("]", str.substring(str.length() - 1, str.length()));

        for(int i = 0; i < 30; i++)
            assertTrue(str.contains(i + ""), "Missing: " + i + " In: " + str);
    }
}
