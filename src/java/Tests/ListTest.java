package Tests;

import Implementations.ArrayList;
import Implementations.LinkedList;
import Implementations.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ListTest {

    /*
    void add(T item);
    T get(int index);
    void remove(int index);
    void clear();
    int getIndex(T item);
    int size();
     */

    public static Stream<Class<? extends List>> provideListClass() {
        return Stream.of(ArrayList.class, LinkedList.class);
    }

    @ParameterizedTest
    @MethodSource("provideListClass")
    void addGetOne(Class<? extends List> listClass) throws IllegalAccessException, InstantiationException {
        List<String> list = listClass.newInstance();
        list.add("Hallo");
        assertEquals("Hallo", list.get(0));
    }

    @ParameterizedTest
    @MethodSource("provideListClass")
    void addGetMany(Class<? extends List> listClass) throws IllegalAccessException, InstantiationException {
        List<Integer> list = listClass.newInstance();

        for(int i = 0; i < 30; i++)
            list.add(i);

        for(int i = 0; i < 30; i++)
            assertEquals(new Integer(i), list.get(i));
    }

    @ParameterizedTest
    @MethodSource("provideListClass")
    void removeRoot(Class<? extends List> listClass) throws IllegalAccessException, InstantiationException {
        List<Integer> list = listClass.newInstance();
        list.add(0);
        list.add(1);

        list.remove(0);
        assertEquals(new Integer(1), list.get(0));
    }

    @ParameterizedTest
    @MethodSource("provideListClass")
    void remove(Class<? extends List> listClass) throws IllegalAccessException, InstantiationException {
        List<Integer> list = listClass.newInstance();
        list.add(0);
        list.add(1);
        list.add(2);

        list.remove(1);

        assertEquals(new Integer(0), list.get(0));
        assertEquals(new Integer(2), list.get(1));
        assertEquals(2, list.size());
    }

    @ParameterizedTest
    @MethodSource("provideListClass")
    void removeLastElement(Class<? extends List> listClass) throws IllegalAccessException, InstantiationException {
        List<Integer> list = listClass.newInstance();
        list.add(0);
        list.add(1);
        list.add(2);

        list.remove(2);

        assertEquals(new Integer(0), list.get(0));
        assertEquals(new Integer(1), list.get(1));
        assertEquals(2, list.size());

        try{
            list.get(2);
            fail("Should throw IndexOutOfBoundsException");
        }catch (IndexOutOfBoundsException e){}
    }

    //Specifically targeted towards the ArrayList
    @ParameterizedTest
    @MethodSource("provideListClass")
    void provokeFullArray(Class<? extends List> listClass) throws IllegalAccessException, InstantiationException {
        List<Integer> list = listClass.newInstance();

        for(int i = 0; i < 5; i++)
            list.add(i);

        list.remove(4);

        assertEquals(4, list.size());

        try{
            list.get(5);
            fail("Should throw IndexOutOfBoundsException");
        }catch (IndexOutOfBoundsException e){}
    }

    @ParameterizedTest
    @MethodSource("provideListClass")
    void clear(Class<? extends List> listClass) throws IllegalAccessException, InstantiationException {
        List<Integer> list = listClass.newInstance();
        for(int i = 0; i < 30; i++)
            list.add(i);

        list.clear();

        assertEquals(0, list.size());
        assertEquals("[]", list.toString());
    }

    @ParameterizedTest
    @MethodSource("provideListClass")
    void getIndex(Class<? extends List> listClass) throws IllegalAccessException, InstantiationException {
        List<Integer> list = listClass.newInstance();

        for(int i = 0; i < 30; i++)
            list.add(i);

        for(int i = 0; i < 30; i++)
            assertEquals(i, list.getIndex(i));
    }

    @ParameterizedTest
    @MethodSource("provideListClass")
    void size(Class<? extends List> listClass) throws IllegalAccessException, InstantiationException {
        List<Integer> list = listClass.newInstance();

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

    @ParameterizedTest
    @MethodSource("provideListClass")
    void getString(Class<? extends List> listClass) throws IllegalAccessException, InstantiationException {
        List<Integer> list = listClass.newInstance();

        for(int i = 0; i < 30; i++)
            list.add(i);

        String str = list.toString();
        assertEquals("[", str.substring(0,1));
        assertEquals("]", str.substring(str.length() - 1, str.length()));

        for(int i = 0; i < 30; i++)
            assertTrue(str.contains(i + ""), "Missing: " + i + " In: " + str);
    }

    @ParameterizedTest
    @MethodSource("provideListClass")
    void outOfBoundsTest(Class<? extends List> listClass) throws IllegalAccessException, InstantiationException {
        List<Integer> list = listClass.newInstance();

        for(int i = 0; i < 10; i++)
            list.add(i);

        try{
            list.get(100);
            fail("Get should throw IndexOutOfBoundsException");
        }catch (IndexOutOfBoundsException e){ }
    }

    @ParameterizedTest
    @MethodSource("provideListClass")
    void doNotFindElement(Class<? extends List> listClass) throws IllegalAccessException, InstantiationException {
        List<Integer> list = listClass.newInstance();

        for(int i = 0; i < 10; i++)
            list.add(i);

        assertEquals(-1, list.getIndex(100));
    }
}
