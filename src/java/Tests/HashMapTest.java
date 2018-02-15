package Tests;

import Implementations.HashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {
    @Test
    public void testSetOne(){
        HashMap<String, String> map = new HashMap<>();
        map.set("Hallo", "Welt");

        assertEquals("[[], [{Hallo: Welt}], []]", map.toString());
    }

    @Test
    public void testSetThree(){
        HashMap<String, String> map = new HashMap<>();
        map.set("A", "B");
        map.set("C", "D");
        map.set("E", "F");

        assertEquals("[[{E: F}], [{C: D}], [{A: B}]]", map.toString());
    }

    @Test
    public void testSetMany(){
        HashMap<String, String> map = new HashMap<>();
        char c[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        for(int i = 0; i < c.length - 1; i++)
            map.set(Character.toString(c[i]), Character.toString(c[i + 1]));

        //Check if they are all in the map
        for(int i = 0; i < c.length - 1; i++)
            assertTrue(map.toString().contains("{" + Character.toString(c[i]) + ": " + Character.toString(c[i + 1]) + "}"),
                    "Cant find added item in toString: " + Character.toString(c[i]));
    }

    @Test
    public void testGet(){
        HashMap<String, String> map = new HashMap<>();
        char c[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

        //Adding
        for(int i = 0; i < c.length - 1; i++)
            map.set(Character.toString(c[i]), Character.toString(c[i + 1]));

        //Checking
        for(int i = 0; i < c.length - 1; i++)
            assertEquals(Character.toString(c[i + 1]), map.get(Character.toString(c[i])));
    }

    @Test
    public void testOverrideValue(){
        HashMap<String, String> map = new HashMap<>();
        map.set("A", "B");
        assertTrue(map.toString().contains("{A: B}"), "This should be ensured by testSetOne already");

        map.set("A", "C");
        assertTrue(map.toString().contains("{A: C}"), "New value should be in the array");
        assertFalse(map.toString().contains("{A: B}"), "Old value should not exist anymore");
    }

    @Test
    public void testRemove(){
        HashMap<String, String> map = new HashMap<>();
        map.set("A", "B");
        map.set("D", "E");

        map.remove("A");

        assertTrue(map.toString().contains("{D: E}"), "Unaffected value still there");
        assertFalse(map.toString().contains("{A: B}"), "Deleted value should be gone");
    }

    @Test
    public void testSizeAdding(){
        HashMap<String, String> map = new HashMap<>();
        char c[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        for(int i = 0; i < c.length - 1; i++) {
            assertEquals(i, map.size());
            map.set(Character.toString(c[i]), Character.toString(c[i + 1]));
        }
    }

    @Test
    public void testSizeRemoving(){
        HashMap<String, String> map = new HashMap<>();
        char c[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        for(int i = 0; i < c.length - 1; i++)
            map.set(Character.toString(c[i]), Character.toString(c[i + 1]));

        int size = map.size();
        for(int i = 0; i < c.length - 1; i++) {
            map.remove(Character.toString(c[i]));
            assertEquals(--size, map.size());
        }
    }

    @Test
    public void testContains(){
        HashMap<String, String> map = new HashMap<>();
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

    @Test
    public void testEntryNotFound(){
        HashMap<String, String> map = new HashMap<>();
        try {
            map.get("No entry");
            fail("Should have thrown an Entity not found exception");
        }catch(RuntimeException e){

        }

        try {
            map.remove("No entry");
            fail("Should have thrown an Entity not found exception");
        }catch(RuntimeException e){

        }
    }
}
