package Tests;

import Implementations.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    private static Stream<Class<? extends Map>> provideMapClass() {
        return Stream.of(HashMap.class, BinarySearchTree.class);
    }

    @ParameterizedTest
    @MethodSource("provideMapClass")
    void testSetMany(Class<? extends Map> mapClass) throws IllegalAccessException, InstantiationException{
        Map<String, String> map = mapClass.newInstance();

        char c[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        for(int i = 0; i < c.length - 1; i++)
            map.set(Character.toString(c[i]), Character.toString(c[i + 1]));

        //Check if they are all in the map
        for(int i = 0; i < c.length - 1; i++)
            assertTrue(map.toString().contains("{" + Character.toString(c[i]) + ":" + Character.toString(c[i + 1]) + "}"),
                    "Cant find added item in toString: " + Character.toString(c[i]));
    }

    @ParameterizedTest
    @MethodSource("provideMapClass")
    void testGet(Class<? extends Map> mapClass) throws IllegalAccessException, InstantiationException{
        Map<String, String> map = mapClass.newInstance();
        char c[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

        //Adding
        for(int i = 0; i < c.length - 1; i++)
            map.set(Character.toString(c[i]), Character.toString(c[i + 1]));

        //Checking
        for(int i = 0; i < c.length - 1; i++)
            assertEquals(Character.toString(c[i + 1]), map.get(Character.toString(c[i])));
    }

    @ParameterizedTest
    @MethodSource("provideMapClass")
    void testOverrideValue(Class<? extends Map> mapClass) throws IllegalAccessException, InstantiationException{
        Map<String, String> map = mapClass.newInstance();
        map.set("A", "B");
        assertTrue(map.toString().contains("{A:B}"), "This should be ensured by testSetOne already");

        map.set("A", "C");
        assertTrue(map.toString().contains("{A:C}"), "New value should be in the array");
        assertFalse(map.toString().contains("{A:B}"), "Old value should not exist anymore");
    }

    @ParameterizedTest
    @MethodSource("provideMapClass")
    void testRemove(Class<? extends Map> mapClass) throws IllegalAccessException, InstantiationException{
        Map<String, String> map = mapClass.newInstance();
        map.set("A", "B");
        map.set("D", "E");

        map.remove("A");

        assertTrue(map.toString().contains("{D:E}"), "Unaffected value still there");
        assertFalse(map.toString().contains("{A:B}"), "Deleted value should be gone");
    }

    @ParameterizedTest
    @MethodSource("provideMapClass")
    void testSizeAdding(Class<? extends Map> mapClass) throws IllegalAccessException, InstantiationException{
        Map<String, String> map = mapClass.newInstance();
        char c[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        for(int i = 0; i < c.length - 1; i++) {
            assertEquals(i, map.size());
            map.set(Character.toString(c[i]), Character.toString(c[i + 1]));
        }
    }

    @ParameterizedTest
    @MethodSource("provideMapClass")
    void testSizeRemoving(Class<? extends Map> mapClass) throws IllegalAccessException, InstantiationException{
        Map<String, String> map = mapClass.newInstance();
        char c[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        for(int i = 0; i < c.length - 1; i++)
            map.set(Character.toString(c[i]), Character.toString(c[i + 1]));

        int size = map.size();
        for(int i = 0; i < c.length - 1; i++) {
            map.remove(Character.toString(c[i]));
            assertEquals(--size, map.size());
        }
    }

    @ParameterizedTest
    @MethodSource("provideMapClass")
    void testContains(Class<? extends Map> mapClass) throws IllegalAccessException, InstantiationException{
        Map<String, String> map = mapClass.newInstance();
        char c[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

        //Adding
        for(int i = 0; i < c.length - 1; i++) {
            assertFalse(map.contains(Character.toString(c[i])));
            map.set(Character.toString(c[i]), Character.toString(c[i + 1]));
            assertTrue(map.contains(Character.toString(c[i])));
        }

        //Removing
        for(int i = 0; i < c.length - 1; i++) {
            assertTrue(map.contains(Character.toString(c[i])));
            map.remove(Character.toString(c[i]));
            assertFalse(map.contains(Character.toString(c[i])));
        }
    }

    @ParameterizedTest
    @MethodSource("provideMapClass")
    void testEntryNotFound(Class<? extends Map> mapClass) throws IllegalAccessException, InstantiationException{
        Map<String, String> map = mapClass.newInstance();
        try {
            map.get("No entry");
            fail("Should have thrown an Entity not found exception");
        }catch(RuntimeException ignored){

        }

        try {
            map.remove("No entry");
            fail("Should have thrown an Entity not found exception");
        }catch(RuntimeException ignored){

        }
    }

    @ParameterizedTest
    @MethodSource("provideMapClass")
    void iteratorTest(Class<? extends Map> mapClass) throws IllegalAccessException, InstantiationException{
        Map<String, String> map = mapClass.newInstance();
        char c[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        for(int i = 0; i < c.length; i++)
            map.set(Character.toString(c[i]), Character.toString(c[Math.min(i + 1, c.length - 1)]));

        StringBuilder sb = new StringBuilder();
        for (Iterator<String> it = map.keys(); it.hasNext(); ) {
            sb.append(it.next());
        }
        char c2[] = sb.toString().toCharArray();
        Arrays.sort(c2);
        Arrays.sort(c);

        for(int i = 0; i < c.length; i++)
            assertEquals(c[i], c2[i]);
    }
}
